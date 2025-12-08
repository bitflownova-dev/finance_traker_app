package com.bitflow.finance.domain.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class ValidateStatementUseCase_Factory implements Factory<ValidateStatementUseCase> {
  @Override
  public ValidateStatementUseCase get() {
    return newInstance();
  }

  public static ValidateStatementUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ValidateStatementUseCase newInstance() {
    return new ValidateStatementUseCase();
  }

  private static final class InstanceHolder {
    private static final ValidateStatementUseCase_Factory INSTANCE = new ValidateStatementUseCase_Factory();
  }
}
