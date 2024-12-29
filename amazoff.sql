-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Dic 29, 2024 alle 14:14
-- Versione del server: 10.4.28-MariaDB
-- Versione PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `amazoff`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `acquisti`
--

CREATE TABLE `acquisti` (
  `idAcquisto` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `idProdotto` varchar(255) DEFAULT NULL,
  `data` date DEFAULT NULL,
  `costo` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dump dei dati per la tabella `acquisti`
--

INSERT INTO `acquisti` (`idAcquisto`, `username`, `idProdotto`, `data`, `costo`) VALUES
(1, 'marco', 'S09', '2024-03-28', 150),
(2, 'luca', 'I12', '2024-03-25', 200),
(3, 'marco', 'HPs15', '2024-02-12', 800),
(4, 'paolo', 'S09', '2024-10-25', 200),
(5, 'federico', 'S09', '2024-10-26', 200),
(6, 'marco', 'HPS15', '2024-10-27', 800),
(7, 'matteo', 'I15', '2024-10-31', 1500),
(8, 'piero', 'djimini4', '2024-11-08', 1116);

-- --------------------------------------------------------

--
-- Struttura della tabella `carrello`
--

CREATE TABLE `carrello` (
  `idAcquisto` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `idProdotto` varchar(255) DEFAULT NULL,
  `data` date DEFAULT NULL,
  `costo` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dump dei dati per la tabella `carrello`
--

INSERT INTO `carrello` (`idAcquisto`, `username`, `idProdotto`, `data`, `costo`) VALUES
(1, 'marco', 'I12', '2024-10-25', 200),
(2, 'luca', 'HPs15', '2024-10-26', 790),
(3, 'marco', 'I12', '2024-10-27', 175),
(14, 'marco', 'I15', '2024-10-31', 1500),
(15, 'marco', 'I15', '2024-10-31', 1500),
(18, 'piero', 'djiair3s', '2024-11-08', 1410),
(19, 'piero', 'I12', '2024-11-08', 400),
(20, 'matteo', 'djiair3s', '2024-12-19', 1410),
(21, 'matteo', 'hpSpectre', '2024-12-19', 1399);

-- --------------------------------------------------------

--
-- Struttura della tabella `prodotto`
--

CREATE TABLE `prodotto` (
  `id` varchar(255) NOT NULL,
  `descrizione` varchar(255) DEFAULT NULL,
  `prezzo` double DEFAULT NULL,
  `categoria` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dump dei dati per la tabella `prodotto`
--

INSERT INTO `prodotto` (`id`, `descrizione`, `prezzo`, `categoria`) VALUES
('djiair3s', 'DJI Air 3S Combo Fly More (RC-N3)', 1410, 'Droni'),
('djimini4', 'DJI Mini 4 Pro Fly More Combo con DJI RC 2', 1116, 'Droni'),
('HPS15', 'HP Pavillon Ryzen 7', 820, 'Portatili'),
('hpSpectre', 'HP pc portatile', 1399, 'Portatili'),
('I12', 'IPhone X', 240, 'Telefonia'),
('I15', 'iPhone 15 Pro Max', 750, 'Telefonia'),
('MacA15M3', 'MacBook Air 15', 1700, 'Portatili'),
('S09', 'Samsung Galaxy S9', 260, 'Telefonia');

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `cognome` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `citta` varchar(255) DEFAULT NULL,
  `datanascita` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `admin` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`username`, `password`, `cognome`, `nome`, `citta`, `datanascita`, `email`, `admin`) VALUES
('federico', 'pallina', 'Neri', 'Federico', 'Troia', '2009-04-17', 'test@tes.t', 0),
('Gv098', 'segreta', 'Rossi', 'Giovanni', 'Troia', '1998-07-26', 'GiovanniRossi98@mail.com', 1),
('luca', 'segreta', 'Rossi', 'Luca', 'Milano', '1995-12-05', 'test@tes.t', 0),
('marco', 'segreta', 'D\'Amato', 'marco', 'Lecco', '2010-04-01', 'test@tes.t', 0),
('matteo', 'segreta', 'Bertoldini', 'Matteo', 'Premana', '2006-06-07', 'matteobertoldini06@gmail.com', 1),
('paolo', 'segreta', 'Bianchi', 'Paolo', 'Lecco', '2019-04-04', 'test@tes.t', 0),
('piero', 'aaaaa', 'Gianola', 'piero', 'premana', '2024-11-06', 'piero@gmail.com', 0);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `acquisti`
--
ALTER TABLE `acquisti`
  ADD PRIMARY KEY (`idAcquisto`),
  ADD KEY `FKcfrp9w2yk0rl6m3vu45jneev7` (`idProdotto`),
  ADD KEY `FKkgac06ffk6v0sq66phf7hu51y` (`username`);

--
-- Indici per le tabelle `carrello`
--
ALTER TABLE `carrello`
  ADD PRIMARY KEY (`idAcquisto`),
  ADD KEY `FKf7eu2a286km5kg95amd881jgs` (`idProdotto`),
  ADD KEY `FKdtfjoxg4gw94e4lkq2y1pj87e` (`username`);

--
-- Indici per le tabelle `prodotto`
--
ALTER TABLE `prodotto`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `acquisti`
--
ALTER TABLE `acquisti`
  MODIFY `idAcquisto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT per la tabella `carrello`
--
ALTER TABLE `carrello`
  MODIFY `idAcquisto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `acquisti`
--
ALTER TABLE `acquisti`
  ADD CONSTRAINT `FKcfrp9w2yk0rl6m3vu45jneev7` FOREIGN KEY (`idProdotto`) REFERENCES `prodotto` (`id`),
  ADD CONSTRAINT `FKkgac06ffk6v0sq66phf7hu51y` FOREIGN KEY (`username`) REFERENCES `utenti` (`username`);

--
-- Limiti per la tabella `carrello`
--
ALTER TABLE `carrello`
  ADD CONSTRAINT `FKdtfjoxg4gw94e4lkq2y1pj87e` FOREIGN KEY (`username`) REFERENCES `utenti` (`username`),
  ADD CONSTRAINT `FKf7eu2a286km5kg95amd881jgs` FOREIGN KEY (`idProdotto`) REFERENCES `prodotto` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
