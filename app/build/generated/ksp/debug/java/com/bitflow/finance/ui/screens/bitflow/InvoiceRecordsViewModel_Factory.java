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
public final class InvoiceRecordsViewModel_Factory implements Factory<InvoiceRecordsViewModel> {
  private final Provider<InvoiceRepository> repositoryProvider;

  public InvoiceRecordsViewModel_Factory(Provider<InvoiceRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public InvoiceRecordsViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static InvoiceRecordsViewModel_Factory create(
      Provider<InvoiceRepository> repositoryProvider) {
    return new InvoiceRecordsViewModel_Factory(repositoryProvider);
  }

  public static InvoiceRecordsViewModel newInstance(InvoiceRepository repository) {
    return new InvoiceRecordsViewModel(repository);
  }
}
