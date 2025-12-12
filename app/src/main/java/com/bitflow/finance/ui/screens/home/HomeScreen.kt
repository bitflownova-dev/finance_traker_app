package com.bitflow.finance.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bitflow.finance.domain.model.Account
import com.bitflow.finance.domain.model.Transaction
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onAnalyticsClick: () -> Unit,
    onTransactionClick: (Long) -> Unit,
    onAddTransactionClick: () -> Unit,
    onSeeAllTransactionsClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val greeting = getGreeting()
    val userName = uiState.userName

    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.background,
                tonalElevation = 0.dp
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp)
                ) {
                    Text(
                        text = greeting,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        letterSpacing = 0.5.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (userName.isNotBlank()) userName else "Your Finances",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        letterSpacing = (-0.5).sp
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Net Worth Card
            item {
                NetWorthCard(
                    netWorth = uiState.totalNetWorth,
                    currencySymbol = uiState.currencySymbol,
                    isPrivacyMode = uiState.isPrivacyMode,
                    isAccountSelected = uiState.selectedAccountId != null
                )
            }

            // Quick Actions
            item {
                QuickActions(
                    onAnalyticsClick = onAnalyticsClick,
                    onAddTransactionClick = onAddTransactionClick
                )
            }

            // Accounts Section
            if (uiState.accounts.isNotEmpty()) {
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        SectionHeader(
                            title = "Accounts",
                            subtitle = "${uiState.accounts.size} active",
                            onSeeAllClick = null
                        )
                        AccountsCarousel(
                            accounts = uiState.accounts,
                            currencySymbol = uiState.currencySymbol,
                            isPrivacyMode = uiState.isPrivacyMode,
                            selectedAccountId = uiState.selectedAccountId,
                            onAccountClick = { viewModel.selectAccount(it) }
                        )
                    }
                }
            }

            // Recent Transactions Section
            if (uiState.recentTransactions.isNotEmpty()) {
                item {
                    SectionHeader(
                        title = "Recent Transactions",
                        subtitle = "Last ${uiState.recentTransactions.size} activities",
                        onSeeAllClick = onSeeAllTransactionsClick
                    )
                }

                items(uiState.recentTransactions) { transaction ->
                    TransactionItem(
                        transaction = transaction,
                        currencySymbol = uiState.currencySymbol,
                        isPrivacyMode = uiState.isPrivacyMode,
                        onClick = { onTransactionClick(transaction.id) }
                    )
                }
            } else {
                item {
                    EmptyState(
                        message = "No transactions yet",
                        icon = Icons.Outlined.Receipt,
                        actionText = "Add Transaction",
                        onActionClick = onAddTransactionClick
                    )
                }
            }
            
            // Bottom spacing for FAB
            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}

private fun getGreeting(): String {
    val hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
    return when (hour) {
        in 0..11 -> "Good Morning"
        in 12..16 -> "Good Afternoon"
        in 17..20 -> "Good Evening"
        else -> "Good Night"
    }
}

@Composable
private fun SectionHeader(
    title: String,
    subtitle: String? = null,
    onSeeAllClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                letterSpacing = (-0.3).sp
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        if (onSeeAllClick != null) {
            TextButton(
                onClick = onSeeAllClick,
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    "See All",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun EmptyState(
    message: String,
    icon: ImageVector,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Medium
        )
        if (actionText != null && onActionClick != null) {
            Button(
                onClick = onActionClick,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(actionText)
            }
        }
    }
}

@Composable
fun NetWorthCard(
    netWorth: Double,
    currencySymbol: String,
    isPrivacyMode: Boolean,
    isAccountSelected: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isAccountSelected) 
                MaterialTheme.colorScheme.secondaryContainer
            else 
                MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Subtle decorative elements
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = 40.dp, y = (-40).dp)
                    .background(
                        Color.White.copy(alpha = 0.05f),
                        CircleShape
                    )
            )
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.BottomStart)
                    .offset(x = (-20).dp, y = 20.dp)
                    .background(
                        Color.White.copy(alpha = 0.05f),
                        CircleShape
                    )
            )
            
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AccountBalance,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = if (isAccountSelected) "Account Balance" else "Total Balance",
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = if (isPrivacyMode) "$currencySymbol ••••••" else formatCurrency(netWorth, currencySymbol),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = (-0.5).sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Updated ${getCurrentDate()}",
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

private fun formatCurrency(amount: Double, symbol: String): String {
    return when {
        amount >= 10_000_000 -> "$symbol %.2fCr".format(amount / 10_000_000)
        amount >= 100_000 -> "$symbol %.2fL".format(amount / 100_000)
        amount >= 1_000 -> "$symbol %.2fK".format(amount / 1_000)
        else -> "$symbol %.2f".format(amount)
    }
}

private fun getCurrentDate(): String {
    val today = LocalDate.now()
    return "${today.dayOfMonth} ${today.month.getDisplayName(TextStyle.SHORT, Locale.getDefault())}"
}

@Composable
fun QuickActions(
    onAnalyticsClick: () -> Unit,
    onAddTransactionClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        QuickActionCard(
            icon = Icons.Outlined.TrendingUp,
            label = "Analytics",
            onClick = onAnalyticsClick,
            modifier = Modifier.weight(1f)
        )
        QuickActionCard(
            icon = Icons.Filled.Add,
            label = "Add Transaction",
            onClick = onAddTransactionClick,
            modifier = Modifier.weight(1f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickActionCard(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(110.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Text(
                    text = label,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Composable
fun AccountsCarousel(
    accounts: List<Account>,
    currencySymbol: String,
    isPrivacyMode: Boolean,
    selectedAccountId: Long?,
    onAccountClick: (Long) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 0.dp)
    ) {
        items(accounts) { account ->
            AccountCard(
                account = account,
                currencySymbol = currencySymbol,
                isPrivacyMode = isPrivacyMode,
                isSelected = account.id == selectedAccountId,
                onClick = { onAccountClick(account.id) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountCard(
    account: Account,
    currencySymbol: String,
    isPrivacyMode: Boolean,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    val accountColor = getAccountColor(account.name)
    
    Card(
        onClick = onClick,
        modifier = Modifier
            .width(200.dp)
            .height(140.dp)
            .then(if (isSelected) Modifier.border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp)) else Modifier),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Color accent bar (smaller, more subtle)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .background(accountColor)
            )
            
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = account.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(accountColor.copy(alpha = 0.12f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AccountBalanceWallet,
                            contentDescription = null,
                            tint = accountColor,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
                
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Balance",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = if (isPrivacyMode) "$currencySymbol ••••" else formatCurrency(account.currentBalance, currencySymbol),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

private fun getAccountColor(accountName: String): Color {
    val colors = listOf(
        Color(0xFF3B82F6), // PrimaryBlue
        Color(0xFF14B8A6), // AccentTeal
        Color(0xFF8B5CF6), // AccentPurple
        Color(0xFFF59E0B), // AccentAmber
        Color(0xFFEC4899), // AccentRose
        Color(0xFF10B981)  // SuccessGreen
    )
    return colors[accountName.hashCode().mod(colors.size)]
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionItem(
    transaction: Transaction,
    currencySymbol: String,
    isPrivacyMode: Boolean,
    onClick: () -> Unit
) {
    val isExpense = transaction.type == com.bitflow.finance.domain.model.ActivityType.EXPENSE
    val transactionColor = if (isExpense) Color(0xFFEF4444) else Color(0xFF10B981) // ErrorRed and SuccessGreen from new palette
    
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(transactionColor.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isExpense) Icons.Outlined.TrendingDown else Icons.Outlined.TrendingUp,
                    contentDescription = null,
                    tint = transactionColor,
                    modifier = Modifier.size(24.dp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = transaction.description,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = formatTransactionDate(transaction.activityDate),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = if (isPrivacyMode) {
                        "$currencySymbol ••••"
                    } else {
                        "${if (isExpense) "-" else "+"}$currencySymbol${String.format("%.2f", transaction.amount)}"
                    },
                    color = transactionColor,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

private fun formatTransactionDate(date: LocalDate): String {
    val today = LocalDate.now()
    val yesterday = today.minusDays(1)
    
    return when (date) {
        today -> "Today"
        yesterday -> "Yesterday"
        else -> {
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
            date.format(formatter)
        }
    }
}
