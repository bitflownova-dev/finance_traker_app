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
public final class ParseStatementSmartUseCase_Factory implements Factory<ParseStatementSmartUseCase> {
  @Override
  public ParseStatementSmartUseCase get() {
    return newInstance();
  }

  public static ParseStatementSmartUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ParseStatementSmartUseCase newInstance() {
    return new ParseStatementSmartUseCase();
  }

  private static final class InstanceHolder {
    private static final ParseStatementSmartUseCase_Factory INSTANCE = new ParseStatementSmartUseCase_Factory();
  }
}
