package com.bitflow.finance.domain.usecase;

import com.bitflow.finance.domain.repository.AccountRepository;
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
public final class ImportStatementBackgroundUseCase_Factory implements Factory<ImportStatementBackgroundUseCase> {
  private final Provider<TransactionRepository> transactionRepositoryProvider;

  private final Provider<AccountRepository> accountRepositoryProvider;

  private final Provider<DetectDuplicatesUseCase> detectDuplicatesProvider;

  public ImportStatementBackgroundUseCase_Factory(
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<AccountRepository> accountRepositoryProvider,
      Provider<DetectDuplicatesUseCase> detectDuplicatesProvider) {
    this.transactionRepositoryProvider = transactionRepositoryProvider;
    this.accountRepositoryProvider = accountRepositoryProvider;
    this.detectDuplicatesProvider = detectDuplicatesProvider;
  }

  @Override
  public ImportStatementBackgroundUseCase get() {
    return newInstance(transactionRepositoryProvider.get(), accountRepositoryProvider.get(), detectDuplicatesProvider.get());
  }

  public static ImportStatementBackgroundUseCase_Factory create(
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<AccountRepository> accountRepositoryProvider,
      Provider<DetectDuplicatesUseCase> detectDuplicatesProvider) {
    return new ImportStatementBackgroundUseCase_Factory(transactionRepositoryProvider, accountRepositoryProvider, detectDuplicatesProvider);
  }

  public static ImportStatementBackgroundUseCase newInstance(
      TransactionRepository transactionRepository, AccountRepository accountRepository,
      DetectDuplicatesUseCase detectDuplicates) {
    return new ImportStatementBackgroundUseCase(transactionRepository, accountRepository, detectDuplicates);
  }
}
