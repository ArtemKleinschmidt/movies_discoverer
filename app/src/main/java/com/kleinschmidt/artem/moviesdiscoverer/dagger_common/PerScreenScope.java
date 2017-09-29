package com.kleinschmidt.artem.moviesdiscoverer.dagger_common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Artem Kleinschmidt on 29.09.2017.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerScreenScope {
}
