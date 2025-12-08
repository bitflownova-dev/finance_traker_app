package com.bitflow.finance.di;

import com.bitflow.finance.data.local.AppDatabase;
import com.bitflow.finance.data.local.dao.AccountDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideAccountDaoFactory implements Factory<AccountDao> {
  private final Provider<AppDatabase> databaseProvider;

  public AppModule_ProvideAccountDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public AccountDao get() {
    return provideAccountDao(databaseProvider.get());
  }

  public static AppModule_ProvideAccountDaoFactory create(Provider<AppDatabase> databaseProvider) {
    return new AppModule_ProvideAccountDaoFactory(databaseProvider);
  }

  public static AccountDao provideAccountDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideAccountDao(database));
  }
}
