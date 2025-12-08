package com.bitflow.finance.di;

import com.bitflow.finance.data.local.AppDatabase;
import com.bitflow.finance.data.local.dao.LearningRuleDao;
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
public final class AppModule_ProvideLearningRuleDaoFactory implements Factory<LearningRuleDao> {
  private final Provider<AppDatabase> databaseProvider;

  public AppModule_ProvideLearningRuleDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public LearningRuleDao get() {
    return provideLearningRuleDao(databaseProvider.get());
  }

  public static AppModule_ProvideLearningRuleDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new AppModule_ProvideLearningRuleDaoFactory(databaseProvider);
  }

  public static LearningRuleDao provideLearningRuleDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideLearningRuleDao(database));
  }
}
