
INSERT INTO kunde (id, nachname, firmenname, email, adresse_fk) VALUES (100,'Admin','Adminfirma','admin@hs-karlsruhe.de',200);
INSERT INTO kunde (id, nachname, vorname, email, adresse_fk) VALUES (101,'Alpha','Adriana','101@hs-karlsruhe.de',201);
INSERT INTO kunde (id, nachname, vorname, email, adresse_fk) VALUES (102,'Alpha','Alfred','102@hs-karlsruhe.de',202);
INSERT INTO kunde (id, nachname, firmenname, email, adresse_fk) VALUES (103,'Alpha','AntonGeEmBeHa','103@hs-karlsruhe.de',203);
INSERT INTO kunde (id, nachname, vorname, email, adresse_fk) VALUES (104,'Delta','Dirk','104@hs-karlsruhe.de',204);
INSERT INTO kunde (id, nachname, vorname, email, adresse_fk) VALUES (105,'Epsilon','Emil','105@hs-karlsruhe.de',205);

INSERT INTO adresse (id, plz, ort, strasse, hausnr) VALUES (200,'76133','Karlsruhe','Moltkestraße','30');
INSERT INTO adresse (id, plz, ort, strasse, hausnr) VALUES (201,'76133','Karlsruhe','Moltkestraße','31');
INSERT INTO adresse (id, plz, ort, strasse, hausnr) VALUES (202,'76133','Karlsruhe','Moltkestraße','32');
INSERT INTO adresse (id, plz, ort, strasse, hausnr) VALUES (203,'76133','Karlsruhe','Moltkestraße','33');
INSERT INTO adresse (id, plz, ort, strasse, hausnr) VALUES (204,'76133','Karlsruhe','Moltkestraße','34');
INSERT INTO adresse (id, plz, ort, strasse, hausnr) VALUES (205,'76133','Karlsruhe','Moltkestraße','35');

INSERT INTO artikel (artikelnr, bezeichnung, preis, typ, rahmen) VALUES (300,'Karls Kruecke',99,'Trekkingbike','w');
INSERT INTO artikel (artikelnr, preis, typ) VALUES (301,20,'Sattel');
INSERT INTO artikel (artikelnr, preis, typ) VALUES (302,9,'Klingel');
INSERT INTO artikel (artikelnr, bezeichnung, preis, typ, rahmen) VALUES (303,'Powerwurst',300,'Mountainbike','m');
INSERT INTO artikel (artikelnr, preis, typ) VALUES (304,15,'Puftlumpe');
INSERT INTO artikel (artikelnr, preis, typ) VALUES (305,5,'Schlauch');
INSERT INTO artikel (artikelnr, bezeichnung, preis, typ, rahmen) VALUES (306,'Berni Bizeps',150,'Dreirad', 'u');

INSERT INTO bestellung (bestellnr, kunde_fk, bestelldatum, istausgeliefert, mitverpackung) VALUES (400,100,'01.08.2013', false, true);
INSERT INTO bestellung (bestellnr, kunde_fk, bestelldatum, istausgeliefert, mitverpackung) VALUES (401,101,'02.08.2013', false, false);
INSERT INTO bestellung (bestellnr, kunde_fk, bestelldatum, istausgeliefert, mitverpackung) VALUES (402,102,'03.08.2013', false, true);
INSERT INTO bestellung (bestellnr, kunde_fk, bestelldatum, istausgeliefert, mitverpackung) VALUES (403,102,'04.08.2013', false, false);
INSERT INTO bestellung (bestellnr, kunde_fk, bestelldatum, istausgeliefert, mitverpackung) VALUES (404,104,'05.08.2013', false, true);

INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl) VALUES (500,400,300,1);
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl) VALUES (501,400,301,4);
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl) VALUES (502,401,302,5);
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl) VALUES (503,402,303,3);
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl) VALUES (504,402,304,2);
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl) VALUES (505,403,305,1);
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl) VALUES (506,404,300,5);
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl) VALUES (507,404,301,2);
INSERT INTO position (id, bestellung_fk, artikel_fk, anzahl) VALUES (508,404,302,8);

