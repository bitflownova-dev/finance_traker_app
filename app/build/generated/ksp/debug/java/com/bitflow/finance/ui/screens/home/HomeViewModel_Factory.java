package com.bitflow.finance.ui.screens.home;

import com.bitflow.finance.domain.repository.AccountRepository;
import com.bitflow.finance.domain.repository.SettingsRepository;
import com.bitflow.finance.domain.repository.TransactionRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<AccountRepository> accountRepositoryProvider;

  private final Provider<TransactionRepository> transactionRepositoryProvider;

  private final Provider<SettingsRepository> settingsRepositoryProvider;

  public HomeViewModel_Factory(Provider<AccountRepository> accountRepositoryProvider,
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<SettingsRepository> settingsRepositoryProvider) {
    this.accountRepositoryProvider = accountRepositoryProvider;
    this.transactionRepositoryProvider = transactionRepositoryProvider;
    this.settingsRepositoryProvider = settingsRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(accountRepositoryProvider.get(), transactionRepositoryProvider.get(), settingsRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<AccountRepository> accountRepositoryProvider,
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<SettingsRepository> settingsRepositoryProvider) {
    return new HomeViewModel_Factory(accountRepositoryProvider, transactionRepositoryProvider, settingsRepositoryProvider);
  }

  public static HomeViewModel newInstance(AccountRepository accountRepository,
      TransactionRepository transactionRepository, SettingsRepository settingsRepository) {
    return new HomeViewModel(accountRepository, transactionRepository, settingsRepository);
  }
}
