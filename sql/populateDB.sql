INSERT INTO Utente (N_Reg, Nome, Mail, Pass, Regione, Provincia, Foto, CAP, Telefono, Città, Via)
VALUES (1, 'Mario Rossi', 'm.rossi@gmail.com', 'B133A0C0E9BEE3BE20163D2AD31D6248DB292AA6DCB1EE087A2AA50E0FC75AE2', 'Lombardia', 'MI', 'foto_rossi.jpeg', '20100', '+39 02 1234567', 'Milano', 'Via Garibaldi');

INSERT INTO Utente (N_Reg, Nome, Mail, Pass, Regione, Provincia, Foto, CAP, Telefono, Città, Via)
VALUES (2, 'Luisa Bianchi', 'l.bianchi@gmail.com', 'a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6q7r8s9t0u1v2w3x4y5z6', 'Lombardia', 'MI', 'foto_bianchi.jpeg', '20100', '+39 02 9876543', 'Milano', 'Via dei Missaglia');

INSERT INTO Azienda (Utente, P_IVA, Rag_Soc, Link, ADI, N_Dip, Sett_Comp)
VALUES (1, '12345678901', 'Azienda S.r.l.', 'www.azienda.it', 'Direttore', 10, 'Informatica');

INSERT INTO Persona (Utente, Cognome, CF, DDN, F_Macroarea, Pos_Des)
VALUES (2, 'Bianchi', 'BNCMLS98A12L219E', '1998-12-21', 'Sud Italia', 'Sviluppatore');

INSERT INTO Sede (ID, Azienda, Citta, Provincia, CAP, Via, Regione, Telefono, Mail)
VALUES (1, 1, 'Milano', 'MI', '20100', 'Via Garibaldi', 'Lombardia', '+39 1234567926', 'infomail@info.mail');

INSERT INTO Annuncio (ID, Azienda, Attivo, Sede, N_Persone, Descrizione, Scadenza, Requisiti, Keyword, Preferenze, Ruolo)
VALUES (1, 1, 1, 1, 2, 'Cercasi sviluppatore web con esperienza in PHP e MySQL', '2023-03-01', 'Conoscenza avanzata di PHP e MySQL', 'sviluppatore, web, PHP, MySQL', 'Esperienza in progetti complessi', 'Sviluppatore Web');

INSERT INTO Sede (ID, Azienda, Citta, Provincia, CAP, Via, Regione, Telefono, Mail)
VALUES (2, 1, 'Roma', 'RM', '00100', 'Via dei Fori Imperiali', 'Lazio', '+39 1234567926', 'infomail@info.mail');

INSERT INTO Annuncio (ID, Azienda, Attivo, Sede, N_Persone, Descrizione, Scadenza, Requisiti, Keyword, Preferenze, Ruolo)
VALUES (2, 1, 1, 2, 5, 'Cercasi sviluppatore software con esperienza in Java e C++', '2023-03-15', 'Conoscenza avanzata di Java e C++', 'sviluppatore, software, Java, C++', 'Esperienza in sviluppo di applicazioni mobile', 'Sviluppatore Software');

INSERT INTO Utente (N_Reg, Nome, Mail, Pass, Regione, Provincia, Foto, CAP, Telefono, Città, Via)
VALUES (3, 'Mark Green', 'm.green@gmail.com', 'x1y2z3a4b5c6d7e8f9g0h1i2j3k4l5m6n7o8p9q0', 'Piemonte', 'TO', 'foto_green.jpeg', '10100', '+39 0101234567', 'Torino', 'Via Roma');

INSERT INTO Azienda (Utente, P_IVA, Rag_Soc, Link, ADI, N_Dip, Sett_Comp)
VALUES (3, '23456789012', 'Azienda 2 S.p.A.', 'www.azienàda2.it', 'Amministratore Delegato', 20, 'Marketing');

INSERT INTO Utente (N_Reg, Nome, Mail, Pass, Regione, Provincia, Foto, CAP, Telefono, Città, Via)
VALUES (4, 'Laura Blu', 'l.blu@gmail.com', '0a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6q7r8s9', 'Veneto', 'VE', 'foto_blu.jpeg', '30100', '+39 0491234567', 'Venezia', 'Riva degli Schiavoni');

INSERT INTO Persona (Utente, Cognome, CF, DDN, F_Macroarea, Pos_Des)
VALUES (4, 'Verdi', 'VRDMRK98L01T196Z', '1998-01-19', 'Nord Italia', 'Responsabile Marketing');





