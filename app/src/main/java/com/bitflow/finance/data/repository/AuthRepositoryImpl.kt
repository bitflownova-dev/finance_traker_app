package com.bitflow.finance.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.bitflow.finance.data.local.dao.UserAccountDao
import com.bitflow.finance.data.local.entity.UserAccountEntity
import com.bitflow.finance.domain.repository.AuthRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton
import android.util.Log

val Context.authDataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

@Singleton
class AuthRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val userAccountDao: UserAccountDao
) : AuthRepository {

    private val USER_ID_KEY = stringPreferencesKey("auth_current_user_id") // Current logged in userId
    private val DISPLAY_NAME_KEY = stringPreferencesKey("auth_display_name") // Display name for UI
    private val IS_LOGGED_IN_KEY = booleanPreferencesKey("auth_is_logged_in")
    private val IS_ADMIN_KEY = booleanPreferencesKey("auth_is_admin")

    // Hardcoded Bitflow Admin Credentials
    private val ADMIN_ID = "713116"
    private val ADMIN_PASS_PLAIN = "g!M@A#P$2025?."
    private val ADMIN_PASS_HASH = "84820998db407209e3ba9821eb59f24be14687fff7575920bfacc8455d3dd2b7"
    private val ADMIN_USER_ID = "bitflow_admin_713116"

    override val currentUser: Flow<String?> = context.authDataStore.data
        .map { preferences ->
            if (preferences[IS_LOGGED_IN_KEY] == true) {
                preferences[DISPLAY_NAME_KEY]
            } else {
                null
            }
        }

    override val isBitflowAdmin: Flow<Boolean> = context.authDataStore.data
        .map { preferences ->
            preferences[IS_ADMIN_KEY] ?: false
        }

    override val currentUserId: Flow<String> = context.authDataStore.data
        .map { preferences ->
            preferences[USER_ID_KEY] ?: "default_user"
        }

    override suspend fun checkUsernameAvailable(username: String): Boolean {
        val user = userAccountDao.getUserByUsername(username)
        return user == null
    }

    override suspend fun signup(
        username: String,
        displayName: String,
        password: String,
        securityQuestion: String,
        securityAnswer: String
    ): Result<UserAccountEntity> {
        val cleanUsername = username.trim().lowercase()
        val cleanDisplayName = displayName.trim()
        val cleanPassword = password.trim()
        val cleanAnswer = securityAnswer.trim().lowercase()
        
        return try {
            if (cleanUsername == ADMIN_ID) {
                return Result.failure(Exception("Cannot use restricted username"))
            }

            // Check if username already exists
            val existingUser = userAccountDao.getUserByUsername(cleanUsername)
            if (existingUser != null) {
                return Result.failure(Exception("Username already taken. Please choose another."))
            }

            val passwordHash = hashPassword(cleanPassword)
            val answerHash = hashPassword(cleanAnswer)
            val uniqueUserId = java.util.UUID.randomUUID().toString()
            
            val newUser = UserAccountEntity(
                userId = uniqueUserId,
                username = cleanUsername,
                displayName = cleanDisplayName,
                passwordHash = passwordHash,
                securityQuestion = securityQuestion,
                securityAnswerHash = answerHash,
                createdAt = System.currentTimeMillis(),
                lastLoginAt = System.currentTimeMillis(),
                isActive = true
            )
            
            userAccountDao.insertUser(newUser)
            Log.d("AuthRepo", "User created: $cleanUsername (display: $cleanDisplayName) with UUID: $uniqueUserId")
            
            Result.success(newUser)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun login(username: String, password: String): Result<UserAccountEntity?> {
        val cleanUsername = username.trim().lowercase()
        val cleanPassword = password.trim()
        val normalizedPassword = normalizePassword(cleanPassword)
        val inputHash = hashPassword(normalizedPassword)

        // Check for Admin Login
        if (cleanUsername == ADMIN_ID && (normalizedPassword == ADMIN_PASS_PLAIN || inputHash == ADMIN_PASS_HASH)) {
            context.authDataStore.edit { preferences ->
                preferences[IS_LOGGED_IN_KEY] = true
                preferences[IS_ADMIN_KEY] = true
                preferences[DISPLAY_NAME_KEY] = "Bitflow Admin"
                preferences[USER_ID_KEY] = ADMIN_USER_ID
            }
            Log.d("AuthRepo", "Admin login success")
            
            val adminAccount = UserAccountEntity(
                userId = ADMIN_USER_ID,
                username = ADMIN_ID,
                displayName = "Bitflow Admin",
                passwordHash = ADMIN_PASS_HASH,
                securityQuestion = "",
                securityAnswerHash = "",
                createdAt = 0,
                lastLoginAt = System.currentTimeMillis(),
                isActive = true
            )
            return Result.success(adminAccount)
        }

        // Check for User Login
        return try {
            val user = userAccountDao.authenticateUser(cleanUsername, inputHash)
            
            if (user != null) {
                Log.d("AuthRepo", "User found: ${user.username}")
                Result.success(user)
            } else {
                Result.success(null) // User not found or wrong password
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun verifySecurityQuestion(
        username: String,
        securityAnswer: String
    ): Result<UserAccountEntity> {
        val cleanUsername = username.trim().lowercase()
        val cleanAnswer = securityAnswer.trim().lowercase()
        val answerHash = hashPassword(cleanAnswer)
        
        return try {
            val user = userAccountDao.verifySecurityAnswer(cleanUsername, answerHash)
            
            if (user != null) {
                Result.success(user)
            } else {
                Result.failure(Exception("Incorrect security answer"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun loginWithAccount(account: UserAccountEntity): Result<Unit> {
        return try {
            // Update last login time
            userAccountDao.updateLastLogin(account.userId, System.currentTimeMillis())
            
            // Save to DataStore
            context.authDataStore.edit { preferences ->
                preferences[IS_LOGGED_IN_KEY] = true
                preferences[IS_ADMIN_KEY] = account.userId == ADMIN_USER_ID
                preferences[DISPLAY_NAME_KEY] = account.displayName
                preferences[USER_ID_KEY] = account.userId
            }
            
            Log.d("AuthRepo", "Logged in as: ${account.displayName} (${account.userId.take(8)})")
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout() {
        context.authDataStore.edit { preferences ->
            preferences[IS_LOGGED_IN_KEY] = false
            preferences[IS_ADMIN_KEY] = false
        }
    }

    override suspend fun checkAuth(): Boolean {
        val preferences = context.authDataStore.data.first()
        return preferences[IS_LOGGED_IN_KEY] ?: false
    }

    private fun hashPassword(password: String): String {
        val bytes = password.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it.toInt() and 0xff) }
    }

    private fun normalizePassword(input: String): String {
        return input
            .trim()
            .map { ch ->
                when (ch) {
                    '！' -> '!'
                    '＠' -> '@'
                    '＃' -> '#'
                    '＄' -> '$'
                    '？' -> '?'
                    '．', '。' -> '.'
                    'Ｐ', 'ℙ' -> 'P'
                    'Ａ' -> 'A'
                    'Ｍ' -> 'M'
                    '０' -> '0'
                    '１' -> '1'
                    '２' -> '2'
                    '３' -> '3'
                    '４' -> '4'
                    '５' -> '5'
                    '６' -> '6'
                    '７' -> '7'
                    '８' -> '8'
                    '９' -> '9'
                    else -> ch
                }
            }
            .joinToString("")
    }
}
