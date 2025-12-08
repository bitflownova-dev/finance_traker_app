package com.bitflow.finance.ui.screens.home_v2;

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
public final class HomeViewModelV2_Factory implements Factory<HomeViewModelV2> {
  private final Provider<TransactionRepository> transactionRepositoryProvider;

  public HomeViewModelV2_Factory(Provider<TransactionRepository> transactionRepositoryProvider) {
    this.transactionRepositoryProvider = transactionRepositoryProvider;
  }

  @Override
  public HomeViewModelV2 get() {
    return newInstance(transactionRepositoryProvider.get());
  }

  public static HomeViewModelV2_Factory create(
      Provider<TransactionRepository> transactionRepositoryProvider) {
    return new HomeViewModelV2_Factory(transactionRepositoryProvider);
  }

  public static HomeViewModelV2 newInstance(TransactionRepository transactionRepository) {
    return new HomeViewModelV2(transactionRepository);
  }
}
