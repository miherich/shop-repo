INSERT INTO adresse (id, plz, ort, strasse, hausnummer) VALUES (200,'76133','Karlsruhe','Moltkestrassüe','30');
INSERT INTO adresse (id, plz, ort, strasse, hausnummer) VALUES (201,'76133','Karlsruhe','Moltkestrasse','31');
INSERT INTO adresse (id, plz, ort, strasse, hausnummer) VALUES (202,'76133','Karlsruhe','Moltkestrasse','32');
INSERT INTO adresse (id, plz, ort, strasse, hausnummer) VALUES (203,'76133','Karlsruhe','Moltkestrasse','33');
INSERT INTO adresse (id, plz, ort, strasse, hausnummer) VALUES (204,'76133','Karlsruhe','Moltkestrasse','34');
INSERT INTO adresse (id, plz, ort, strasse, hausnummer) VALUES (205,'76133','Karlsruhe','Moltkestrasse','35');

INSERT INTO kunde (kundennr, nachname, firmenname, email, adresse_fk, art) VALUES (100,'Admin','Adminfirma','admin@hs-karlsruhe.de', 200, 'G');
INSERT INTO kunde (kundennr, nachname, vorname, email, adresse_fk, art) VALUES (101,'Alpha','Adriana','101@hs-karlsruhe.de',201, 'P');
INSERT INTO kunde (kundennr, nachname, vorname, email, adresse_fk, art) VALUES (102,'Alpha','Alfred','102@hs-karlsruhe.de',202, 'P');
INSERT INTO kunde (kundennr, nachname, firmenname, email, adresse_fk, art) VALUES (103,'Alpha','AntonGeEmBeHa','103@hs-karlsruhe.de',203, 'G');
INSERT INTO kunde (kundennr, nachname, vorname, email, adresse_fk, art) VALUES (104,'Delta','Dirk','104@hs-karlsruhe.de',204, 'P');
INSERT INTO kunde (kundennr, nachname, vorname, email, adresse_fk, art) VALUES (105,'Epsilon','Emil','105@hs-karlsruhe.de',205, 'P');

INSERT INTO artikel (artikelnr, bezeichnung, preis, typ, rahmen, art) VALUES (300,'Karls Kruecke',99,'Trekkingbike','W', 'F');
INSERT INTO artikel (artikelnr, preis, typ, art) VALUES (301,20,'Sattel', 'Z');
INSERT INTO artikel (artikelnr, preis, typ, art) VALUES (302,9,'Klingele', 'Z');
INSERT INTO artikel (artikelnr, bezeichnung, preis, typ, rahmen, art) VALUES (303,'Powerwurst',300,'Mountainbike','M', 'F');
INSERT INTO artikel (artikelnr, preis, typ, art) VALUES (304,15,'Puftlumpe', 'Z');
INSERT INTO artikel (artikelnr, preis, typ, art) VALUES (305,5,'Schlauch', 'Z');
INSERT INTO artikel (artikelnr, bezeichnung, preis, typ, rahmen, art) VALUES (306,'Berni Bizeps',150,'Dreirad', 'U', 'F');

INSERT INTO bestellung (bestellnr, kunde_fk, bestelldatum, istAusgeliefert, mitVerpackung) VALUES (400,100,'01.08.2013 00:00:00', 0, 1);
INSERT INTO bestellung (bestellnr, kunde_fk, bestelldatum, istausgeliefert, mitverpackung) VALUES (401,101,'02.08.2013', 0, 0);
INSERT INTO bestellung (bestellnr, kunde_fk, bestelldatum, istausgeliefert, mitverpackung) VALUES (402,102,'03.08.2013', 1, 1);
INSERT INTO bestellung (bestellnr, kunde_fk, bestelldatum, istausgeliefert, mitverpackung) VALUES (403,102,'04.08.2013', 0, 0);
INSERT INTO bestellung (bestellnr, kunde_fk, bestelldatum, istausgeliefert, mitverpackung) VALUES (404,104,'05.08.2013', 0, 1);

INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl) VALUES (500,400,300,1);
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl) VALUES (501,400,301,4);
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl) VALUES (502,401,302,5);
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl) VALUES (503,402,303,3);
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl) VALUES (504,402,304,2);
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl) VALUES (505,403,305,1);
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl) VALUES (506,404,300,5);
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl) VALUES (507,404,301,2);
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl) VALUES (508,404,302,8);

