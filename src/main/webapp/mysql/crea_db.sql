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
                        password VARCHAR(255) NOT NULL
);

CREATE TABLE carrello (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          id_utente INT NOT NULL,
                          quantita INT NOT NULL,
                          totale DECIMAL(10,2) NOT NULL,
                          FOREIGN KEY (id_utente) REFERENCES utente(id)

);

CREATE table Ordine(
                       id int not null AUTO_INCREMENT,
                       dataset text not null,
                       indirizzo text not null,
                       idUtente int not null,
                       totale double not null,

                       PRIMARY KEY(id),
                       FOREIGN KEY (idUtente)  references Utente(id) ON UPDATE CASCADE ON DELETE CASCADE
);

create table ORDINECOMPRENDEPRODOTTO(
                                       id_ordine int not null,
                                       id_prodotto int not null,
                                       primary key(id_ordine,id_prodotto),
                                       foreign key(id_ordine) references Ordine(id)ON UPDATE CASCADE ON DELETE CASCADE,
                                       foreign key(id_prodotto) references Prodotto(id)ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE CARRELLOCONTIENEPRODOTTO (
                                   id_prodotto_carrello INT PRIMARY KEY AUTO_INCREMENT,
                                   id_carrello INT,
                                   id_prodotto INT,
                                   quantità INT,
                                   FOREIGN KEY (id_carrello) REFERENCES carrello(id),
                                   FOREIGN KEY (id_prodotto) REFERENCES prodotto(id)
);


create table  Recensione(
                            idRecensione int not null auto_increment,
                            valutazione int not null,
                            testo text not null,
                            id_utente int not null,
                            id_prodotto int not null,

                            primary key(idRecensione),
                            foreign key(id_utente) references Utente(id),
                            foreign key (id_prodotto) references Prodotto(id)
);
