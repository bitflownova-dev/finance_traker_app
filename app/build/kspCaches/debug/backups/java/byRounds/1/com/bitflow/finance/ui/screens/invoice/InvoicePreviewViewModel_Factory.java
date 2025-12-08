package com.bitflow.finance.ui.screens.invoice;

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
public final class InvoicePreviewViewModel_Factory implements Factory<InvoicePreviewViewModel> {
  private final Provider<InvoiceRepository> repositoryProvider;

  public InvoicePreviewViewModel_Factory(Provider<InvoiceRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public InvoicePreviewViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static InvoicePreviewViewModel_Factory create(
      Provider<InvoiceRepository> repositoryProvider) {
    return new InvoicePreviewViewModel_Factory(repositoryProvider);
  }

  public static InvoicePreviewViewModel newInstance(InvoiceRepository repository) {
    return new InvoicePreviewViewModel(repository);
  }
}
