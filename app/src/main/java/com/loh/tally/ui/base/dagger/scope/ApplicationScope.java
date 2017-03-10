package com.loh.tally.ui.base.dagger.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * File: ApplicationScope.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: Scope constrained to lifecycle of application.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationScope {
}
