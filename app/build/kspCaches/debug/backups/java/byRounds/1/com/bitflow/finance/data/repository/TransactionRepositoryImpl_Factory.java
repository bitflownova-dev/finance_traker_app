package com.bitflow.finance.data.repository;

import com.bitflow.finance.data.local.dao.CategoryDao;
import com.bitflow.finance.data.local.dao.LearningRuleDao;
import com.bitflow.finance.data.local.dao.TransactionDao;
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
public final class TransactionRepositoryImpl_Factory implements Factory<TransactionRepositoryImpl> {
  private final Provider<TransactionDao> transactionDaoProvider;

  private final Provider<CategoryDao> categoryDaoProvider;

  private final Provider<LearningRuleDao> learningRuleDaoProvider;

  public TransactionRepositoryImpl_Factory(Provider<TransactionDao> transactionDaoProvider,
      Provider<CategoryDao> categoryDaoProvider,
      Provider<LearningRuleDao> learningRuleDaoProvider) {
    this.transactionDaoProvider = transactionDaoProvider;
    this.categoryDaoProvider = categoryDaoProvider;
    this.learningRuleDaoProvider = learningRuleDaoProvider;
  }

  @Override
  public TransactionRepositoryImpl get() {
    return newInstance(transactionDaoProvider.get(), categoryDaoProvider.get(), learningRuleDaoProvider.get());
  }

  public static TransactionRepositoryImpl_Factory create(
      Provider<TransactionDao> transactionDaoProvider, Provider<CategoryDao> categoryDaoProvider,
      Provider<LearningRuleDao> learningRuleDaoProvider) {
    return new TransactionRepositoryImpl_Factory(transactionDaoProvider, categoryDaoProvider, learningRuleDaoProvider);
  }

  public static TransactionRepositoryImpl newInstance(TransactionDao transactionDao,
      CategoryDao categoryDao, LearningRuleDao learningRuleDao) {
    return new TransactionRepositoryImpl(transactionDao, categoryDao, learningRuleDao);
  }
}
