package com.bitflow.finance.ui.screens.transaction_detail;

import androidx.lifecycle.SavedStateHandle;
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
public final class TransactionDetailViewModel_Factory implements Factory<TransactionDetailViewModel> {
  private final Provider<TransactionRepository> repositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public TransactionDetailViewModel_Factory(Provider<TransactionRepository> repositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.repositoryProvider = repositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public TransactionDetailViewModel get() {
    return newInstance(repositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static TransactionDetailViewModel_Factory create(
      Provider<TransactionRepository> repositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new TransactionDetailViewModel_Factory(repositoryProvider, savedStateHandleProvider);
  }

  public static TransactionDetailViewModel newInstance(TransactionRepository repository,
      SavedStateHandle savedStateHandle) {
    return new TransactionDetailViewModel(repository, savedStateHandle);
  }
}
