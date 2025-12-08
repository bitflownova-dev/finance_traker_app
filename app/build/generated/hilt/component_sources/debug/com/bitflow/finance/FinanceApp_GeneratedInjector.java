package com.bitflow.finance;

import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedEntryPoint;

@OriginatingElement(
    topLevelClass = FinanceApp.class
)
@GeneratedEntryPoint
@InstallIn(SingletonComponent.class)
public interface FinanceApp_GeneratedInjector {
  void injectFinanceApp(FinanceApp financeApp);
}
