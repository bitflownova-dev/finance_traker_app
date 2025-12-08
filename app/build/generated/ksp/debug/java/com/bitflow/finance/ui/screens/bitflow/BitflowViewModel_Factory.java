package com.bitflow.finance.ui.screens.bitflow;

import com.bitflow.finance.data.repository.InvoiceRepository;
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
public final class BitflowViewModel_Factory implements Factory<BitflowViewModel> {
  private final Provider<InvoiceRepository> repositoryProvider;

  public BitflowViewModel_Factory(Provider<InvoiceRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public BitflowViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static BitflowViewModel_Factory create(Provider<InvoiceRepository> repositoryProvider) {
    return new BitflowViewModel_Factory(repositoryProvider);
  }

  public static BitflowViewModel newInstance(InvoiceRepository repository) {
    return new BitflowViewModel(repository);
  }
}
