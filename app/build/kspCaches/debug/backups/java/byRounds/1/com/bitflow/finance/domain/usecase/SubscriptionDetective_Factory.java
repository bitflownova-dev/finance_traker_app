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
public final class SubscriptionDetective_Factory implements Factory<SubscriptionDetective> {
  @Override
  public SubscriptionDetective get() {
    return newInstance();
  }

  public static SubscriptionDetective_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SubscriptionDetective newInstance() {
    return new SubscriptionDetective();
  }

  private static final class InstanceHolder {
    private static final SubscriptionDetective_Factory INSTANCE = new SubscriptionDetective_Factory();
  }
}
