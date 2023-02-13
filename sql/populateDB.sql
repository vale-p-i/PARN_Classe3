
#########################AZIENDE#########################
INSERT INTO Utente (N_Reg, Nome, Mail, Pass, Regione, Provincia, Foto, CAP, Telefono, Città, Via)
VALUES (1, 'Mario Rossi', 'm.rossi@gmail.com', '0b9f97b01f95c6c993901d2059b4972d94e2b6eb0b6d73fb9afd181386a5b501', 'Lombardia', 'MI', 'foto_rossi.jpeg', '20100', '+39 02 1234567', 'Milano', 'Via Garibaldi');
INSERT INTO Azienda (Utente, P_IVA, Rag_Soc, Link, ADI, N_Dip, Sett_Comp)
VALUES (1, '12345678901', 'Azienda S.r.l.', 'www.azienda.it', 'Direttore', 10, 'Informatica');
INSERT INTO Sede (ID, Azienda, Citta, Provincia, CAP, Via, Regione, Telefono, Mail)
VALUES (3, 1, 'Bologna', 'BO', '40100', 'Via della Costituzione', 'Emilia Romagna', '+39 1234567926', 'infomail@info.mail');
INSERT INTO Sede (ID, Azienda, Citta, Provincia, CAP, Via, Regione, Telefono, Mail)
VALUES (4, 1, 'Napoli', 'NA', '80123', 'Via dei Tribunali', 'Campania', '+39 1234567926', 'infomail@info.mail');
INSERT INTO Sede (ID, Azienda, Citta, Provincia, CAP, Via, Regione, Telefono, Mail)
VALUES (1, 1, 'Milano', 'MI', '20100', 'Via Garibaldi', 'Lombardia', '+39 1234567926', 'infomail@info.mail');
INSERT INTO Sede (ID, Azienda, Citta, Provincia, CAP, Via, Regione, Telefono, Mail)
VALUES (2, 1, 'Roma', 'RM', '00100', 'Via dei Fori Imperiali', 'Lazio', '+39 1234567926', 'infomail@info.mail');

INSERT INTO Utente (N_Reg, Nome, Mail, Pass, Regione, Provincia, Foto, CAP, Telefono, Città, Via)
VALUES (5, 'Mark Green', 'm.green@gmail.com', 'x1y2z3a4b5c6d7e8f9g0h1i2j3k4l5m6n7o8p9q0', 'Piemonte', 'TO', 'foto_green.jpeg', '10100', '+39 0101234567', 'Torino', 'Via Roma');
INSERT INTO Azienda (Utente, P_IVA, Rag_Soc, Link, ADI, N_Dip, Sett_Comp)
VALUES (5, '23456789012', 'Azienda 2 S.p.A.', 'www.azienàda2.it', 'Amministratore Delegato', 20, 'Marketing');
INSERT INTO Sede (ID, Azienda, Citta, Provincia, CAP, Via, Regione, Telefono, Mail)
VALUES (5, 5, 'Firenze', 'FI', '50100', 'Via della Stazione', 'Toscana', '+39 1234567926', 'infomail@info.mail');
INSERT INTO Sede (ID, Azienda, Citta, Provincia, CAP, Via, Regione, Telefono, Mail)
VALUES (6, 5, 'Genova', 'GE', '20100', 'Via della Lanterna', 'Liguria', '+39 1234567926', 'infomail@info.mail');

#########################Persone#########################
INSERT INTO Utente (N_Reg, Nome, Mail, Pass, Regione, Provincia, Foto, CAP, Telefono, Città, Via)
VALUES (2, 'Ugo', 'l.bianchi@gmail.com', 'a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6q7r8s9t0u1v2w3x4y5z6', 'Lombardia', 'MI', 'foto_bianchi.jpeg', '20100', '+39 02 9876543', 'Milano', 'Via dei Missaglia');
INSERT INTO Persona (Utente, Cognome, CF, DDN, F_Macroarea, Pos_Des)
VALUES (2, 'Fantocci', 'BNCMLS98A12L219E', '1998-12-21', 'Sud Italia', 'Sviluppatore');

INSERT INTO Utente (N_Reg, Nome, Mail, Pass, Regione, Provincia, Foto, CAP, Telefono, Città, Via)
VALUES (3, 'Mauro', 'l.mariomaild@gmail.com', 'a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6q7r8s9t0u1v2w3x4y5z6', 'Lombardia', 'MI', 'rossi.jpeg', '20100', '+39 02 9876543', 'Milano', 'Via dei Missaglia');
INSERT INTO Persona (Utente, Cognome, CF, DDN, F_Macroarea, Pos_Des)
VALUES (3, 'Rorsri', 'BNCMLS98B12L219E', '1998-12-21', 'Sud Italia', 'Ingegnere del Software');

INSERT INTO Utente (N_Reg, Nome, Mail, Pass, Regione, Provincia, Foto, CAP, Telefono, Città, Via)
VALUES (4, 'Laura', 'l.blu@gmail.com', '0a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6q7r8s9', 'Veneto', 'VE', 'foto_blu.jpeg', '30100', '+39 0491234567', 'Venezia', 'Riva degli Schiavoni');
INSERT INTO Persona (Utente, Cognome, CF, DDN, F_Macroarea, Pos_Des)
VALUES (4, 'Verdi', 'VRDMRK98L11T196Z', '1998-01-19', 'Nord Italia', 'Responsabile Marketing');

#########################CURRICULUM#########################
INSERT INTO Curriculum (Persona, Soft_Skills)
VALUES (2, "LOREM IPSUM DOLOR SIT AMET");


INSERT INTO Curriculum (Persona, Soft_Skills)
VALUES (3, "LOREM IPSUM DOLOR SIT AMET");

INSERT INTO Curriculum (Persona, Soft_Skills)
VALUES (4, "LOREM IPSUM DOLOR SIT AMET");


#########################ESPERIENZA#########################
INSERT INTO Esperienza (Curriculum, Nome_Azienda, Tipo_Impiego, Mansioni, Datore, Contatto, Tipo_Azienda, DDI, DDF)
VALUES (2, "Megaditta", "Ragioniere", "Mai Capito", "Megadirettore naturale", "megadirettoregalattic@aruba.it", "Azienda Malvagia", "1975-03-27", "1999-12-24");

INSERT INTO Esperienza (Curriculum, Nome_Azienda, Tipo_Impiego, Mansioni, Datore, Contatto, Tipo_Azienda, DDI, DDF)
VALUES (3, "SupermercatiTheRock", "Commesso", "Cassa", "Dwaine Jhonson", "supermercatidipietra@rock.it", "Catena di suoermercati", "2013-04-17", "2016-01-01");

INSERT INTO Esperienza (Curriculum, Nome_Azienda, Tipo_Impiego, Mansioni, Datore, Contatto, Tipo_Azienda, DDI)
VALUES (4, "AziendaNormale", "Addetta Vendite", "Accoglienza degli alimenti", "Mario Rossi", "mariorossi@mario.it", "Catena di suoermercati", "2018-02-04");

#########################ISTRUZIONE#########################
INSERT INTO Istruzione (Curriculum, Tipo, Istituto, DDI, DDF, Qualifica)
VALUE (2, "Secondaria di primo grado", "Istituto di ragioneria", "1950-09-10", "1960-04-25", "Ragioniere");

INSERT INTO Istruzione (Curriculum, Tipo, Istituto, DDI, DDF, Qualifica)
VALUE (3, "Secondaria di secondo grado", "UNIBO", "1970-09-01", "1975-04-25", "Linguista");

INSERT INTO Istruzione (Curriculum, Tipo, Istituto, DDI, DDF, Qualifica)
VALUE (4, "Primaria", "Scuola", "1995-09-10", "2001-04-25", "Diploma elementare");


#########################LINGUA#########################
INSERT INTO Lingua (Curriculum, Nome, Livello)
VALUE (2, "Italiano", "Madrelingua");

INSERT INTO Lingua (Curriculum, Nome, Livello)
VALUE (3, "Italiano", "Medio");
INSERT INTO Lingua (Curriculum, Nome, Livello)
VALUE (3, "Klingoniano", "Madrelingua");

INSERT INTO Lingua (Curriculum, Nome, Livello)
VALUE (4, "Italiano", "Madrelingua");
INSERT INTO Lingua (Curriculum, Nome, Livello)
VALUE (4, "Inglese", "Buono");


#########################ANNUNCI#########################
INSERT INTO Annuncio (ID, Azienda, Attivo, Sede, N_Persone, Descrizione, Scadenza, Requisiti, Keyword, Preferenze, Ruolo)
VALUES (1, 1, 1, 1, 2, 'Cercasi sviluppatore web con esperienza in PHP e MySQL', '2023-03-01', 'Conoscenza avanzata di PHP e MySQL', 'sviluppatore, web, PHP, MySQL', 'Esperienza in progetti complessi', 'Sviluppatore Web');

INSERT INTO Annuncio (ID, Azienda, Attivo, Sede, N_Persone, Descrizione, Scadenza, Requisiti, Keyword, Preferenze, Ruolo)
VALUES (2, 5, 1, 5, 5, 'Cercasi sviluppatore software con esperienza in Java e C++', '2023-03-15', 'Conoscenza avanzata di Java e C++', 'sviluppatore, software, Java, C++', 'Esperienza in sviluppo di applicazioni mobile', 'Sviluppatore Software');

#########################CANDIDATURA#########################
INSERT INTO Candidatura (Annuncio, Persona, Data_Pub)
VALUES (1, 2, "2022-12-10");
INSERT INTO Candidatura (Annuncio, Persona, Data_Pub)
VALUES (1, 3, "2022-12-12");

INSERT INTO Candidatura (Annuncio, Persona, Data_Pub)
VALUES (2, 4, "2023-01-04");
INSERT INTO Candidatura (Annuncio, Persona, Data_Pub)
VALUES (2, 3, "2023-01-14");





