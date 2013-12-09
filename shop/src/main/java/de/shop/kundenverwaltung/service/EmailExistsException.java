package de.shop.kundenverwaltung.service;

import javax.ejb.ApplicationException;
import javax.enterprise.context.Dependent;

import de.shop.util.interceptor.Log;

@Log
@ApplicationException(rollback = true)
@Dependent
public class EmailExistsException extends AbstractKundeServiceException {
	private static final long serialVersionUID = 4867667611097919943L;
	
	private static final String MESSAGE_KEY = "{kundenverwaltung.kunde.emailExists}";
	private final String email;
	
	public EmailExistsException(String email) {
		super("Die Email-Adresse " + email + " existiert bereits");
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getMessageKey() {
		return MESSAGE_KEY;
	}
}
