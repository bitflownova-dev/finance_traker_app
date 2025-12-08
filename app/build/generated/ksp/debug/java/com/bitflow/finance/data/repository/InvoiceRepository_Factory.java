package com.bitflow.finance.data.repository;

import com.bitflow.finance.data.local.dao.InvoiceDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class InvoiceRepository_Factory implements Factory<InvoiceRepository> {
  private final Provider<InvoiceDao> invoiceDaoProvider;

  public InvoiceRepository_Factory(Provider<InvoiceDao> invoiceDaoProvider) {
    this.invoiceDaoProvider = invoiceDaoProvider;
  }

  @Override
  public InvoiceRepository get() {
    return newInstance(invoiceDaoProvider.get());
  }

  public static InvoiceRepository_Factory create(Provider<InvoiceDao> invoiceDaoProvider) {
    return new InvoiceRepository_Factory(invoiceDaoProvider);
  }

  public static InvoiceRepository newInstance(InvoiceDao invoiceDao) {
    return new InvoiceRepository(invoiceDao);
  }
}
