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
public final class AutoLearnCategoryUseCase_Factory implements Factory<AutoLearnCategoryUseCase> {
  private final Provider<TransactionRepository> transactionRepositoryProvider;

  public AutoLearnCategoryUseCase_Factory(
      Provider<TransactionRepository> transactionRepositoryProvider) {
    this.transactionRepositoryProvider = transactionRepositoryProvider;
  }

  @Override
  public AutoLearnCategoryUseCase get() {
    return newInstance(transactionRepositoryProvider.get());
  }

  public static AutoLearnCategoryUseCase_Factory create(
      Provider<TransactionRepository> transactionRepositoryProvider) {
    return new AutoLearnCategoryUseCase_Factory(transactionRepositoryProvider);
  }

  public static AutoLearnCategoryUseCase newInstance(TransactionRepository transactionRepository) {
    return new AutoLearnCategoryUseCase(transactionRepository);
  }
}
