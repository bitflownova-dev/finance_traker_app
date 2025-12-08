package com.bitflow.finance.ui.screens.add_transaction;

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
public final class AddTransactionViewModel_Factory implements Factory<AddTransactionViewModel> {
  private final Provider<TransactionRepository> transactionRepositoryProvider;

  public AddTransactionViewModel_Factory(
      Provider<TransactionRepository> transactionRepositoryProvider) {
    this.transactionRepositoryProvider = transactionRepositoryProvider;
  }

  @Override
  public AddTransactionViewModel get() {
    return newInstance(transactionRepositoryProvider.get());
  }

  public static AddTransactionViewModel_Factory create(
      Provider<TransactionRepository> transactionRepositoryProvider) {
    return new AddTransactionViewModel_Factory(transactionRepositoryProvider);
  }

  public static AddTransactionViewModel newInstance(TransactionRepository transactionRepository) {
    return new AddTransactionViewModel(transactionRepository);
  }
}
