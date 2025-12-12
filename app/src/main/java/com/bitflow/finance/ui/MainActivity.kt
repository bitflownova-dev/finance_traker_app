package com.bitflow.finance.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.bitflow.finance.core.theme.FinanceAppTheme
import com.bitflow.finance.domain.repository.SettingsRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

import com.bitflow.finance.domain.repository.AuthRepository
import com.bitflow.finance.ui.screens.login.LoginScreen

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    @Inject
    lateinit var settingsRepository: SettingsRepository
    
    @Inject
    lateinit var authRepository: AuthRepository

    private var isAuthenticated by mutableStateOf(false) // Biometric auth state
    private var isUserLoggedIn by mutableStateOf(false) // Session auth state
    private var isBitflowAdmin by mutableStateOf(false)
    private var isBiometricEnabled by mutableStateOf(false)
    private var isLoading by mutableStateOf(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Observe Auth State
        lifecycleScope.launch {
            authRepository.currentUser.collect { user ->
                isUserLoggedIn = user != null
                if (!isUserLoggedIn) {
                    isAuthenticated = false
                }
            }
        }

        lifecycleScope.launch {
            isBiometricEnabled = settingsRepository.isBiometricEnabled.first()
            isBitflowAdmin = authRepository.isBitflowAdmin.first()
            
            if (!isUserLoggedIn) {
                // Need to login first
                isAuthenticated = false
                isLoading = false
            } else if (!isBiometricEnabled) {
                isAuthenticated = true
                isLoading = false
            } else {
                showBiometricPrompt()
                isLoading = false
            }
        }

        setContent {
            FinanceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (isLoading) {
                        // Show splash or loading
                    } else if (!isUserLoggedIn) {
                        LoginScreen(
                            onLoginSuccess = {
                                lifecycleScope.launch {
                                    isUserLoggedIn = true
                                    isBitflowAdmin = authRepository.isBitflowAdmin.first()
                                    // If biometrics enabled, we might want to prompt, but usually login is enough for this session
                                    isAuthenticated = true 
                                }
                            }
                        )
                    } else if (isAuthenticated) {
                        FinanceAppNavigation(isBitflowAdmin = isBitflowAdmin)
                    } else {
                        LockScreen(onUnlockClick = { showBiometricPrompt() })
                    }
                }
            }
        }
    }

    private fun showBiometricPrompt() {
        val executor = ContextCompat.getMainExecutor(this)
        val biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    isAuthenticated = true
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Login")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Cancel")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}

@Composable
fun LockScreen(onUnlockClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Lock,
            contentDescription = "Locked",
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("App Locked", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onUnlockClick) {
            Text("Unlock")
        }
    }
}
