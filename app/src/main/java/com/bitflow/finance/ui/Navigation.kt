package com.bitflow.finance.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bitflow.finance.ui.screens.accounts.AccountsScreen
import com.bitflow.finance.ui.screens.add_transaction.AddTransactionScreen
import com.bitflow.finance.ui.screens.analysis.AnalysisScreen
import com.bitflow.finance.ui.screens.categories.CategoryManagementScreen
import com.bitflow.finance.ui.screens.home.HomeScreen
import com.bitflow.finance.ui.screens.import_statement.ImportStatementScreen
import com.bitflow.finance.ui.screens.invoice.InvoiceGeneratorScreen
import com.bitflow.finance.ui.screens.settings.SettingsScreen
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.bitflow.finance.ui.screens.transaction_detail.TransactionDetailScreen
import com.bitflow.finance.ui.screens.transactions.TransactionsScreen
import com.bitflow.finance.ui.screens.bitflow.BitflowScreen
import com.bitflow.finance.ui.screens.bitflow.InvoiceRecordsScreen
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Receipt

import com.bitflow.finance.ui.screens.invoice.InvoicePreviewScreen
import com.bitflow.finance.ui.screens.profile.ProfileScreen
import com.bitflow.finance.ui.screens.bitflow.BitflowInsightsScreen

@Composable
fun FinanceAppNavigation(isBitflowAdmin: Boolean) {
    val navController = rememberNavController()

    val items = if (isBitflowAdmin) {
        listOf(
            Screen.Home,
            Screen.Transactions,
            Screen.Bitflow,
            Screen.Insights,
            Screen.Profile
        )
    } else {
        listOf(
            Screen.Home,
            Screen.Transactions,
            Screen.Insights,
            Screen.Profile
        )
    }

    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val currentRoute = currentDestination?.route
            
            // Hide bottom bar on specific screens
            if (currentRoute != "import" && 
                currentRoute != "categories" &&
                currentRoute != "add_transaction" &&
                currentRoute != "invoice" &&
                currentRoute != "invoice_records" &&
                currentRoute?.startsWith("transaction_detail") != true) {
                NavigationBar(
                    tonalElevation = 0.dp,
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    items.forEach { screen ->
                        NavigationBarItem(
                            icon = { 
                                Icon(
                                    imageVector = screen.icon,
                                    contentDescription = screen.label,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            label = { 
                                Text(
                                    text = screen.label,
                                    style = MaterialTheme.typography.labelSmall,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            alwaysShowLabel = true
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onAddTransactionClick = { navController.navigate("add_transaction") },
                    onTransactionClick = { activityId ->
                        navController.navigate("transaction_detail/$activityId")
                    },
                    onAnalyticsClick = { navController.navigate(Screen.Insights.route) },
                    onSeeAllTransactionsClick = { navController.navigate(Screen.Transactions.route) },
                    onProfileClick = { navController.navigate(Screen.Profile.route) }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    onBackClick = { navController.popBackStack() },
                    onSettingsClick = { navController.navigate(Screen.Settings.route) },
                    onAccountsClick = { navController.navigate(Screen.Accounts.route) },
                    onInsightsClick = { navController.navigate(Screen.Insights.route) },
                    onImportClick = { navController.navigate("import") }
                )
            }
            composable(Screen.Accounts.route) {
                AccountsScreen()
            }
            
            if (isBitflowAdmin) {
                composable(Screen.Bitflow.route) {
                    BitflowScreen(
                        onInvoiceGeneratorClick = { navController.navigate("invoice") },
                        onInvoiceRecordsClick = { navController.navigate("invoice_records") },
                        onInsightsClick = { navController.navigate("bitflow_insights") }
                    )
                }
                composable("bitflow_insights") {
                    BitflowInsightsScreen(
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }

            composable(Screen.Insights.route) {
                AnalysisScreen()
            }
            composable(Screen.Settings.route) {
                SettingsScreen(
                    onNavigateToCategories = { navController.navigate("categories") }
                )
            }
            composable("import") {
                ImportStatementScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }
            
            if (isBitflowAdmin) {
                composable("invoice") {
                    InvoiceGeneratorScreen(
                        onBackClick = { navController.popBackStack() }
                    )
                }
                composable("invoice_records") {
                    InvoiceRecordsScreen(
                        onBackClick = { navController.popBackStack() },
                        onInvoiceClick = { invoiceId ->
                            navController.navigate("invoice_preview/$invoiceId")
                        }
                    )
                }
                composable(
                    route = "invoice_preview/{invoiceId}",
                    arguments = listOf(navArgument("invoiceId") { type = NavType.LongType })
                ) { backStackEntry ->
                    val invoiceId = backStackEntry.arguments?.getLong("invoiceId") ?: 0L
                    InvoicePreviewScreen(
                        invoiceId = invoiceId,
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }

            composable(Screen.Transactions.route) {
                TransactionsScreen(
                    onBackClick = { navController.popBackStack() },
                    onTransactionClick = { id -> navController.navigate("transaction_detail/$id") }
                )
            }
            composable("add_transaction") {
                AddTransactionScreen(
                    onBackClick = { navController.popBackStack() },
                    onSuccess = { navController.popBackStack() }
                )
            }
            composable("categories") {
                CategoryManagementScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable(
                route = "transaction_detail/{transactionId}",
                arguments = listOf(navArgument("transactionId") { type = NavType.LongType })
            ) {
                TransactionDetailScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}

sealed class Screen(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Transactions : Screen("transactions", "Transactions", Icons.Default.Receipt)
    object Bitflow : Screen("bitflow", "Bitflow", Icons.Default.Star)
    object Insights : Screen("insights", "Insights", Icons.Default.Analytics)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
    object Accounts : Screen("accounts", "Accounts", Icons.Default.AccountBalance)
    object Settings : Screen("settings", "Settings", Icons.Default.Settings)
}
