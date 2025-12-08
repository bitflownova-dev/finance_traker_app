package com.bitflow.finance.ui.screens.home;

import com.bitflow.finance.domain.repository.SettingsRepository;
import com.bitflow.finance.domain.repository.TransactionRepository;
import com.bitflow.finance.domain.usecase.SubscriptionDetective;
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
public final class DailyPulseViewModel_Factory implements Factory<DailyPulseViewModel> {
  private final Provider<TransactionRepository> transactionRepositoryProvider;

  private final Provider<SubscriptionDetective> subscriptionDetectiveProvider;

  private final Provider<SettingsRepository> settingsRepositoryProvider;

  public DailyPulseViewModel_Factory(Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<SubscriptionDetective> subscriptionDetectiveProvider,
      Provider<SettingsRepository> settingsRepositoryProvider) {
    this.transactionRepositoryProvider = transactionRepositoryProvider;
    this.subscriptionDetectiveProvider = subscriptionDetectiveProvider;
    this.settingsRepositoryProvider = settingsRepositoryProvider;
  }

  @Override
  public DailyPulseViewModel get() {
    return newInstance(transactionRepositoryProvider.get(), subscriptionDetectiveProvider.get(), settingsRepositoryProvider.get());
  }

  public static DailyPulseViewModel_Factory create(
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<SubscriptionDetective> subscriptionDetectiveProvider,
      Provider<SettingsRepository> settingsRepositoryProvider) {
    return new DailyPulseViewModel_Factory(transactionRepositoryProvider, subscriptionDetectiveProvider, settingsRepositoryProvider);
  }

  public static DailyPulseViewModel newInstance(TransactionRepository transactionRepository,
      SubscriptionDetective subscriptionDetective, SettingsRepository settingsRepository) {
    return new DailyPulseViewModel(transactionRepository, subscriptionDetective, settingsRepository);
  }
}
