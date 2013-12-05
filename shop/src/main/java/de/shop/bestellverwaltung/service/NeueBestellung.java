package de.shop.bestellverwaltung.service;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import de.shop.util.interceptor.Log;

@Qualifier
@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
@Documented
@Log
public @interface NeueBestellung {
}
