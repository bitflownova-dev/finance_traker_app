package com.bitflow.finance.domain.usecase;

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
public final class DetectSubscriptionsUseCase_Factory implements Factory<DetectSubscriptionsUseCase> {
  private final Provider<TransactionRepository> transactionRepositoryProvider;

  public DetectSubscriptionsUseCase_Factory(
      Provider<TransactionRepository> transactionRepositoryProvider) {
    this.transactionRepositoryProvider = transactionRepositoryProvider;
  }

  @Override
  public DetectSubscriptionsUseCase get() {
    return newInstance(transactionRepositoryProvider.get());
  }

  public static DetectSubscriptionsUseCase_Factory create(
      Provider<TransactionRepository> transactionRepositoryProvider) {
    return new DetectSubscriptionsUseCase_Factory(transactionRepositoryProvider);
  }

  public static DetectSubscriptionsUseCase newInstance(
      TransactionRepository transactionRepository) {
    return new DetectSubscriptionsUseCase(transactionRepository);
  }
}
