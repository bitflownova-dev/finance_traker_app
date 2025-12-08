package com.bitflow.finance.domain.usecase;

import com.bitflow.finance.data.parser.StatementParser;
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
public final class ImportStatementUseCase_Factory implements Factory<ImportStatementUseCase> {
  private final Provider<StatementParser> parserProvider;

  private final Provider<TransactionRepository> transactionRepositoryProvider;

  private final Provider<AccountRepository> accountRepositoryProvider;

  public ImportStatementUseCase_Factory(Provider<StatementParser> parserProvider,
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<AccountRepository> accountRepositoryProvider) {
    this.parserProvider = parserProvider;
    this.transactionRepositoryProvider = transactionRepositoryProvider;
    this.accountRepositoryProvider = accountRepositoryProvider;
  }

  @Override
  public ImportStatementUseCase get() {
    return newInstance(parserProvider.get(), transactionRepositoryProvider.get(), accountRepositoryProvider.get());
  }

  public static ImportStatementUseCase_Factory create(Provider<StatementParser> parserProvider,
      Provider<TransactionRepository> transactionRepositoryProvider,
      Provider<AccountRepository> accountRepositoryProvider) {
    return new ImportStatementUseCase_Factory(parserProvider, transactionRepositoryProvider, accountRepositoryProvider);
  }

  public static ImportStatementUseCase newInstance(StatementParser parser,
      TransactionRepository transactionRepository, AccountRepository accountRepository) {
    return new ImportStatementUseCase(parser, transactionRepository, accountRepository);
  }
}
