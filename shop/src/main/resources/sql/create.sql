--DROP SEQUENCE hibernate_sequence;
CREATE SEQUENCE hibernate_sequence START WITH 5000;

CREATE TABLE Artikel   (artikelnr number primary key,
						typ varchar2(30) not null,
						preis number(8,2) not null,
						rahmen varchar2(30),
						bezeichnung varchar2(30));
						
CREATE TABLE Adresse   (id number primary key,
						strasse VARCHAR2(30) not null,
						hausnr VARCHAR2(4) not null,
						plz VARCHAR2(5) not null,
						ort VARCHAR2(30) not null);
						
CREATE TABLE Kunde	    (kundennr number primary key,
						 nachname VARCHAR2(30) not null,
						 email VARCHAR2(30) not null,
						 adresse_fk number REFERENCES adresse(id),
						 firmenname VARCHAR2(30),
						 vorname VARCHAR2(30));
						
CREATE TABLE Bestellung (bestellnr number primary key,
						 bestelldatum DATE not null,
						 istAusgeliefert boolean,
						 mitVerpackung boolean,
						 kunde_fk number REFERENCES kunde(kundennr));
						
CREATE TABLE Position  (id number primary key,
						artikel_fk number REFERENCES artikel(artikelnr),
						anzahl number,
						bestellung_fk number REFERENCES bestellung(bestellnr));
						
COMMIT;
