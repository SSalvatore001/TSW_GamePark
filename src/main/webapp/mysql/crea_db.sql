drop database if exists ecommerce_gamepark;

CREATE DATABASE ecommerce_gamepark;

USE ecommerce_gamepark;

CREATE TABLE prodotto (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          nome VARCHAR(255) NOT NULL,
                          descrizione TEXT NOT NULL,
                          prezzo DECIMAL(10,2) NOT NULL,
                          immagine VARCHAR(255) NOT NULL,
                          quantità INT NOT NULL
);

CREATE TABLE utente (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        nome VARCHAR(255) NOT NULL,
                        cognome VARCHAR(255) NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        indirizzo TEXT NOT NULL
);

CREATE TABLE carrello (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          id_utente INT NOT NULL,
                          id_prodotto INT NOT NULL,
                          quantita INT NOT NULL,
                          prezzo_totale DECIMAL(10,2) NOT NULL,
                          FOREIGN KEY (id_utente) REFERENCES utente(id),
                          FOREIGN KEY (id_prodotto) REFERENCES prodotto(id)
);

CREATE TABLE ordine (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        data DATE NOT NULL,
                        importo_totale DECIMAL(10,2) NOT NULL,
                        id_utente INT NOT NULL,
                        FOREIGN KEY (id_utente) REFERENCES utente(id)
);

CREATE TABLE recensione (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            testo TEXT NOT NULL,
                            voto INT NOT NULL,
                            data DATE NOT NULL,
                            id_utente INT NOT NULL,
                            id_prodotto INT NOT NULL,
                            FOREIGN KEY (id_utente) REFERENCES utente(id),
                            FOREIGN KEY (id_prodotto) REFERENCES prodotto(id)
);
