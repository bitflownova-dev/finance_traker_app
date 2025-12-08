package com.bitflow.finance.ui.screens.categories;

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
public final class CategoryManagementViewModel_Factory implements Factory<CategoryManagementViewModel> {
  private final Provider<TransactionRepository> transactionRepositoryProvider;

  public CategoryManagementViewModel_Factory(
      Provider<TransactionRepository> transactionRepositoryProvider) {
    this.transactionRepositoryProvider = transactionRepositoryProvider;
  }

  @Override
  public CategoryManagementViewModel get() {
    return newInstance(transactionRepositoryProvider.get());
  }

  public static CategoryManagementViewModel_Factory create(
      Provider<TransactionRepository> transactionRepositoryProvider) {
    return new CategoryManagementViewModel_Factory(transactionRepositoryProvider);
  }

  public static CategoryManagementViewModel newInstance(
      TransactionRepository transactionRepository) {
    return new CategoryManagementViewModel(transactionRepository);
  }
}
