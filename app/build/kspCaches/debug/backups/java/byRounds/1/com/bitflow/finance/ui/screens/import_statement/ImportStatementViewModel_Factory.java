package com.bitflow.finance.ui.screens.import_statement;

import android.content.Context;
import com.bitflow.finance.domain.repository.AccountRepository;
import com.bitflow.finance.domain.usecase.ImportStatementBackgroundUseCase;
import com.bitflow.finance.domain.usecase.ImportStatementUseCase;
import com.bitflow.finance.domain.usecase.ValidateStatementUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class ImportStatementViewModel_Factory implements Factory<ImportStatementViewModel> {
  private final Provider<ImportStatementUseCase> importStatementUseCaseProvider;

  private final Provider<ImportStatementBackgroundUseCase> backgroundImportUseCaseProvider;

  private final Provider<ValidateStatementUseCase> validateStatementUseCaseProvider;

  private final Provider<AccountRepository> accountRepositoryProvider;

  private final Provider<Context> contextProvider;

  public ImportStatementViewModel_Factory(
      Provider<ImportStatementUseCase> importStatementUseCaseProvider,
      Provider<ImportStatementBackgroundUseCase> backgroundImportUseCaseProvider,
      Provider<ValidateStatementUseCase> validateStatementUseCaseProvider,
      Provider<AccountRepository> accountRepositoryProvider, Provider<Context> contextProvider) {
    this.importStatementUseCaseProvider = importStatementUseCaseProvider;
    this.backgroundImportUseCaseProvider = backgroundImportUseCaseProvider;
    this.validateStatementUseCaseProvider = validateStatementUseCaseProvider;
    this.accountRepositoryProvider = accountRepositoryProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public ImportStatementViewModel get() {
    return newInstance(importStatementUseCaseProvider.get(), backgroundImportUseCaseProvider.get(), validateStatementUseCaseProvider.get(), accountRepositoryProvider.get(), contextProvider.get());
  }

  public static ImportStatementViewModel_Factory create(
      Provider<ImportStatementUseCase> importStatementUseCaseProvider,
      Provider<ImportStatementBackgroundUseCase> backgroundImportUseCaseProvider,
      Provider<ValidateStatementUseCase> validateStatementUseCaseProvider,
      Provider<AccountRepository> accountRepositoryProvider, Provider<Context> contextProvider) {
    return new ImportStatementViewModel_Factory(importStatementUseCaseProvider, backgroundImportUseCaseProvider, validateStatementUseCaseProvider, accountRepositoryProvider, contextProvider);
  }

  public static ImportStatementViewModel newInstance(ImportStatementUseCase importStatementUseCase,
      ImportStatementBackgroundUseCase backgroundImportUseCase,
      ValidateStatementUseCase validateStatementUseCase, AccountRepository accountRepository,
      Context context) {
    return new ImportStatementViewModel(importStatementUseCase, backgroundImportUseCase, validateStatementUseCase, accountRepository, context);
  }
}
