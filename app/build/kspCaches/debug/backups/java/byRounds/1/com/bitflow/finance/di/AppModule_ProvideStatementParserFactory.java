package com.bitflow.finance.di;

import android.content.Context;
import com.bitflow.finance.data.parser.StatementParser;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideStatementParserFactory implements Factory<StatementParser> {
  private final Provider<Context> contextProvider;

  public AppModule_ProvideStatementParserFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public StatementParser get() {
    return provideStatementParser(contextProvider.get());
  }

  public static AppModule_ProvideStatementParserFactory create(Provider<Context> contextProvider) {
    return new AppModule_ProvideStatementParserFactory(contextProvider);
  }

  public static StatementParser provideStatementParser(Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideStatementParser(context));
  }
}
