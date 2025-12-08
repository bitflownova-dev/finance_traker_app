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
public final class InvoiceViewModel_Factory implements Factory<InvoiceViewModel> {
  private final Provider<InvoiceRepository> repositoryProvider;

  public InvoiceViewModel_Factory(Provider<InvoiceRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public InvoiceViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static InvoiceViewModel_Factory create(Provider<InvoiceRepository> repositoryProvider) {
    return new InvoiceViewModel_Factory(repositoryProvider);
  }

  public static InvoiceViewModel newInstance(InvoiceRepository repository) {
    return new InvoiceViewModel(repository);
  }
}
