package de.shop.kundenverwaltung.service;

import javax.enterprise.context.Dependent;

import de.shop.util.AbstractShopException;
import de.shop.util.interceptor.Log;

@Log
@Dependent
public abstract class AbstractKundeServiceException extends AbstractShopException {
	private static final long serialVersionUID = -2849585609393128387L;

	public AbstractKundeServiceException(String msg) {
		super(msg);
	}
	
	public AbstractKundeServiceException(String msg, Throwable t) {
		super(msg, t);
	}
}
