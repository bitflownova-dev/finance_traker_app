package com.bitflow.finance.ui.screens.analysis;

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
public final class AnalysisViewModel_Factory implements Factory<AnalysisViewModel> {
  private final Provider<TransactionRepository> transactionRepositoryProvider;

  private final Provider<SettingsRepository> settingsRepositoryProvider;

  private final Provider<AccountRepository> accountRepositoryProvider;

  public AnalysisViewModel_Factory(Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<AccountRepository> accountRepositoryProvider) {
    this.transactionRepositoryProvider = transactionRepositoryProvider;
    this.settingsRepositoryProvider = settingsRepositoryProvider;
    this.accountRepositoryProvider = accountRepositoryProvider;
  }

  @Override
  public AnalysisViewModel get() {
    return newInstance(transactionRepositoryProvider.get(), settingsRepositoryProvider.get(), accountRepositoryProvider.get());
  }

  public static AnalysisViewModel_Factory create(
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<AccountRepository> accountRepositoryProvider) {
    return new AnalysisViewModel_Factory(transactionRepositoryProvider, settingsRepositoryProvider, accountRepositoryProvider);
  }

  public static AnalysisViewModel newInstance(TransactionRepository transactionRepository,
      SettingsRepository settingsRepository, AccountRepository accountRepository) {
    return new AnalysisViewModel(transactionRepository, settingsRepository, accountRepository);
  }
}
