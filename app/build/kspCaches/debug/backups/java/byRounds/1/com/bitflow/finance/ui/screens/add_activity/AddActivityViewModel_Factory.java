package com.bitflow.finance.ui.screens.add_activity;

import com.bitflow.finance.domain.repository.TransactionRepository;
import com.bitflow.finance.domain.usecase.AutoLearnCategoryUseCase;
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
public final class AddActivityViewModel_Factory implements Factory<AddActivityViewModel> {
  private final Provider<TransactionRepository> transactionRepositoryProvider;

  private final Provider<AutoLearnCategoryUseCase> autoLearnUseCaseProvider;

  public AddActivityViewModel_Factory(Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<AutoLearnCategoryUseCase> autoLearnUseCaseProvider) {
    this.transactionRepositoryProvider = transactionRepositoryProvider;
    this.autoLearnUseCaseProvider = autoLearnUseCaseProvider;
  }

  @Override
  public AddActivityViewModel get() {
    return newInstance(transactionRepositoryProvider.get(), autoLearnUseCaseProvider.get());
  }

  public static AddActivityViewModel_Factory create(
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<AutoLearnCategoryUseCase> autoLearnUseCaseProvider) {
    return new AddActivityViewModel_Factory(transactionRepositoryProvider, autoLearnUseCaseProvider);
  }

  public static AddActivityViewModel newInstance(TransactionRepository transactionRepository,
      AutoLearnCategoryUseCase autoLearnUseCase) {
    return new AddActivityViewModel(transactionRepository, autoLearnUseCase);
  }
}
