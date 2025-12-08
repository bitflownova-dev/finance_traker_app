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
public final class DetectDuplicatesUseCase_Factory implements Factory<DetectDuplicatesUseCase> {
  private final Provider<TransactionRepository> transactionRepositoryProvider;

  public DetectDuplicatesUseCase_Factory(
      Provider<TransactionRepository> transactionRepositoryProvider) {
    this.transactionRepositoryProvider = transactionRepositoryProvider;
  }

  @Override
  public DetectDuplicatesUseCase get() {
    return newInstance(transactionRepositoryProvider.get());
  }

  public static DetectDuplicatesUseCase_Factory create(
      Provider<TransactionRepository> transactionRepositoryProvider) {
    return new DetectDuplicatesUseCase_Factory(transactionRepositoryProvider);
  }

  public static DetectDuplicatesUseCase newInstance(TransactionRepository transactionRepository) {
    return new DetectDuplicatesUseCase(transactionRepository);
  }
}
