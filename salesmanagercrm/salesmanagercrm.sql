-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : jeu. 07 oct. 2021 à 13:33
-- Version du serveur :  5.7.24
-- Version de PHP : 7.2.19

SET
SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET
time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `salesmanagercrm`
--

-- --------------------------------------------------------

--
-- Structure de la table `addresses`
--

CREATE TABLE `addresses`
(
    `ID`           int(11) UNSIGNED NOT NULL,
    `ID_Cities`    int(11) UNSIGNED NOT NULL,
    `ID_Contacts`  int(11) UNSIGNED DEFAULT NULL,
    `ID_Companies` int(11) UNSIGNED DEFAULT NULL,
    `Street`       varchar(255) COLLATE utf8_bin NOT NULL,
    `Number`       varchar(255) COLLATE utf8_bin NOT NULL,
    `Box`          varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `addresses`
--

INSERT INTO `addresses` (`ID`, `ID_Cities`, `ID_Contacts`, `ID_Companies`, `Street`, `Number`, `Box`)
VALUES (1, 5, NULL, 3, 'de la loi', '20', ''),
       (2, 750, NULL, 2, 'Rue Louvrex', '95', 'B-4000'),
       (3, 5, NULL, 2, 'Rue de Naples ', '29', 'B-1050');

-- --------------------------------------------------------

--
-- Structure de la table `branch_activities`
--

CREATE TABLE `branch_activities`
(
    `ID`    int(11) UNSIGNED NOT NULL,
    `Label` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `branch_activities`
--

INSERT INTO `branch_activities` (`ID`, `Label`)
VALUES (1, 'Informatique'),
       (2, 'Soin'),
       (3, 'Construction'),
       (4, 'Télécommunication');

-- --------------------------------------------------------

--
-- Structure de la table `cities`
--

CREATE TABLE `cities`
(
    `ID`           int(11) UNSIGNED NOT NULL,
    `ID_Countries` int(11) UNSIGNED NOT NULL,
    `Region`       varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `Postal_Code`  varchar(255) COLLATE utf8_bin NOT NULL,
    `Label`        varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `cities`
--

INSERT INTO `cities` (`ID`, `ID_Countries`, `Region`, `Postal_Code`, `Label`)
VALUES (1, 1, 'Hainaut', '6200', 'Châtelineau'),
       (2, 1, 'Bruxelles-Capitale', '1020', 'Laeken'),
       (3, 1, 'Bruxelles-Capitale', '1030', 'Schaerbeek'),
       (4, 1, 'Bruxelles-Capitale', '1040', 'Etterbeek'),
       (5, 1, 'Bruxelles-Capitale', '1050', 'Ixelles'),
       (6, 1, 'Bruxelles-Capitale', '1060', 'Saint-Gilles'),
       (7, 1, 'Bruxelles-Capitale', '1070', 'Anderlecht'),
       (8, 1, 'Bruxelles-Capitale', '1080', 'Molenbeek-Saint-Jean'),
       (9, 1, 'Bruxelles-Capitale', '1081', 'Koekelberg'),
       (10, 1, 'Bruxelles-Capitale', '1082', 'Berchem-Sainte-Agathe'),
       (11, 1, 'Bruxelles-Capitale', '1083', 'Ganshoren'),
       (12, 1, 'Bruxelles-Capitale', '1090', 'Jette'),
       (13, 1, 'Bruxelles-Capitale', '1120', 'Neder-Over-Heembeek'),
       (14, 1, 'Bruxelles-Capitale', '1130', 'Haren'),
       (15, 1, 'Bruxelles-Capitale', '1140', 'Evere'),
       (16, 1, 'Bruxelles-Capitale', '1150', 'Woluwe-Saint-Pierre'),
       (17, 1, 'Bruxelles-Capitale', '1160', 'Auderghem'),
       (18, 1, 'Bruxelles-Capitale', '1170', 'Watermael-Boitsfort'),
       (19, 1, 'Bruxelles-Capitale', '1180', 'Uccle'),
       (20, 1, 'Bruxelles-Capitale', '1190', 'Forest'),
       (21, 1, 'Bruxelles-Capitale', '1200', 'Woluwe-Saint-Lambert'),
       (22, 1, 'Bruxelles-Capitale', '1210', 'Saint-Josse-Ten-Noode'),
       (23, 1, 'Brabant wallon', '1300', 'Limal'),
       (24, 1, 'Brabant wallon', '1300', 'Wavre'),
       (25, 1, 'Brabant wallon', '1301', 'Bierges'),
       (26, 1, 'Brabant wallon', '1310', 'La Hulpe'),
       (27, 1, 'Brabant wallon', '1315', 'Glimes'),
       (28, 1, 'Brabant wallon', '1315', 'Incourt'),
       (29, 1, 'Brabant wallon', '1315', 'Opprebais'),
       (30, 1, 'Brabant wallon', '1315', 'Piétrebais'),
       (31, 1, 'Brabant wallon', '1315', 'Roux-Miroir'),
       (32, 1, 'Brabant wallon', '1320', 'Beauvechain'),
       (33, 1, 'Brabant wallon', '1320', 'Hamme-Mille'),
       (34, 1, 'Brabant wallon', '1320', 'L\'Ecluse'),
(35, 1, 'Brabant wallon', '1320', 'Nodebais'),
(36, 1, 'Brabant wallon', '1320', 'Tourinnes-La-Grosse'),
(37, 1, 'Brabant wallon', '1325', 'Bonlez'),
(38, 1, 'Brabant wallon', '1325', 'Chaumont-Gistoux'),
(39, 1, 'Brabant wallon', '1325', 'Corroy-Le-Grand'),
(40, 1, 'Brabant wallon', '1325', 'Dion-Valmont'),
(41, 1, 'Brabant wallon', '1325', 'Longueville'),
(42, 1, 'Brabant wallon', '1330', 'Rixensart'),
(43, 1, 'Brabant wallon', '1331', 'Rosières'),
(44, 1, 'Brabant wallon', '1332', 'Genval'),
(45, 1, 'Brabant wallon', '1340', 'Ottignies'),
(46, 1, 'Brabant wallon', '1341', 'Céroux-Mousty'),
(47, 1, 'Brabant wallon', '1342', 'Limelette'),
(48, 1, 'Brabant wallon', '1348', 'Louvain-La-Neuve'),
(49, 1, 'Brabant wallon', '1350', 'Enines'),
(50, 1, 'Brabant wallon', '1350', 'Folx-Les-Caves'),
(51, 1, 'Brabant wallon', '1350', 'Jandrain-Jandrenouille'),
(52, 1, 'Brabant wallon', '1350', 'Jauche'),
(53, 1, 'Brabant wallon', '1350', 'Marilles'),
(54, 1, 'Brabant wallon', '1350', 'Noduwez'),
(55, 1, 'Brabant wallon', '1350', 'Orp-Le-Grand'),
(56, 1, 'Brabant wallon', '1357', 'Linsmeau'),
(57, 1, 'Brabant wallon', '1357', 'Neerheylissem'),
(58, 1, 'Brabant wallon', '1357', 'Opheylissem'),
(59, 1, 'Brabant wallon', '1360', 'Malèves-Sainte-Marie-Wastinnes'),
(60, 1, 'Brabant wallon', '1360', 'Orbais'),
(61, 1, 'Brabant wallon', '1360', 'Perwez'),
(62, 1, 'Brabant wallon', '1360', 'Thorembais-Les-Béguines'),
(63, 1, 'Brabant wallon', '1360', 'Thorembais-Saint-Trond'),
(64, 1, 'Brabant wallon', '1367', 'Autre-Eglise'),
(65, 1, 'Brabant wallon', '1367', 'Bomal'),
(66, 1, 'Brabant wallon', '1367', 'Geest-Gérompont-Petit-Rosière'),
(67, 1, 'Brabant wallon', '1367', 'Gérompont'),
(68, 1, 'Brabant wallon', '1367', 'Grand-Rosicre-Hottomont'),
(69, 1, 'Brabant wallon', '1367', 'Huppaye'),
(70, 1, 'Brabant wallon', '1367', 'Mont-Saint-André'),
(71, 1, 'Brabant wallon', '1367', 'Ramillies'),
(72, 1, 'Brabant wallon', '1370', 'Dongelberg'),
(73, 1, 'Brabant wallon', '1370', 'Jauchelette'),
(74, 1, 'Brabant wallon', '1370', 'Jodoigne'),
(75, 1, 'Brabant wallon', '1370', 'Jodoigne-Souveraine'),
(76, 1, 'Brabant wallon', '1370', 'Lathuy'),
(77, 1, 'Brabant wallon', '1370', 'Mélin'),
(78, 1, 'Brabant wallon', '1370', 'Piétrain'),
(79, 1, 'Brabant wallon', '1370', 'Saint-Jean-Geest'),
(80, 1, 'Brabant wallon', '1370', 'Saint-Remy-Geest'),
(81, 1, 'Brabant wallon', '1370', 'Zétrud-Lumay'),
(82, 1, 'Brabant wallon', '1380', 'Couture-Saint-Germain'),
(83, 1, 'Brabant wallon', '1380', 'Lasne-Chapelle-Saint-Lambert'),
(84, 1, 'Brabant wallon', '1380', 'Maransart'),
(85, 1, 'Brabant wallon', '1380', 'Ohain'),
(86, 1, 'Brabant wallon', '1380', 'Plancenoit'),
(87, 1, 'Brabant wallon', '1390', 'Archennes'),
(88, 1, 'Brabant wallon', '1390', 'Biez'),
(89, 1, 'Brabant wallon', '1390', 'Bossut-Gottechain'),
(90, 1, 'Brabant wallon', '1390', 'Grez-Doiceau'),
(91, 1, 'Brabant wallon', '1390', 'Nethen'),
(92, 1, 'Brabant wallon', '1400', 'Monstreux'),
(93, 1, 'Brabant wallon', '1400', 'Nivelles'),
(94, 1, 'Brabant wallon', '1401', 'Baulers'),
(95, 1, 'Brabant wallon', '1402', 'Thines'),
(96, 1, 'Brabant wallon', '1404', 'Bornival'),
(97, 1, 'Brabant wallon', '1410', 'Waterloo'),
(98, 1, 'Brabant wallon', '1420', 'Braine-L\'Alleud'),
       (99, 1, 'Brabant wallon', '1421', 'Ophain-Bois-Seigneur-Isaac'),
       (100, 1, 'Brabant wallon', '1428', 'Lillois-Witterzée'),
       (101, 1, 'Brabant wallon', '1430', 'Bierghes'),
       (102, 1, 'Brabant wallon', '1430', 'Quenast'),
       (103, 1, 'Brabant wallon', '1430', 'Rebecq-Rognon'),
       (104, 1, 'Brabant wallon', '1435', 'Corbais'),
       (105, 1, 'Brabant wallon', '1435', 'Hévillers'),
       (106, 1, 'Brabant wallon', '1435', 'Mont-Saint-Guibert'),
       (107, 1, 'Brabant wallon', '1440', 'Braine-Le-Château'),
       (108, 1, 'Brabant wallon', '1440', 'Wauthier-Braine'),
       (109, 1, 'Brabant wallon', '1450', 'Chastre-Villeroux-Blanmont'),
       (110, 1, 'Brabant wallon', '1450', 'Cortil-Noirmont'),
       (111, 1, 'Brabant wallon', '1450', 'Gentinnes'),
       (112, 1, 'Brabant wallon', '1450', 'Saint-Géry'),
       (113, 1, 'Brabant wallon', '1457', 'Nil-Saint-Vincent-Saint-Martin'),
       (114, 1, 'Brabant wallon', '1457', 'Tourinnes-Saint-Lambert'),
       (115, 1, 'Brabant wallon', '1457', 'Walhain-Saint-Paul'),
       (116, 1, 'Brabant wallon', '1460', 'Ittre'),
       (117, 1, 'Brabant wallon', '1460', 'Virginal-Samme'),
       (118, 1, 'Brabant wallon', '1461', 'Haut-Ittre'),
       (119, 1, 'Brabant wallon', '1470', 'Baisy-Thy'),
       (120, 1, 'Brabant wallon', '1470', 'Bousval'),
       (121, 1, 'Brabant wallon', '1470', 'Genappe'),
       (122, 1, 'Brabant wallon', '1471', 'Loupoigne'),
       (123, 1, 'Brabant wallon', '1472', 'Vieux-Genappe'),
       (124, 1, 'Brabant wallon', '1473', 'Glabais'),
       (125, 1, 'Brabant wallon', '1474', 'Ways'),
       (126, 1, 'Brabant wallon', '1476', 'Houtain-Le-Val'),
       (127, 1, 'Brabant wallon', '1480', 'Clabecq'),
       (128, 1, 'Brabant wallon', '1480', 'Oisquercq'),
       (129, 1, 'Brabant wallon', '1480', 'Saintes'),
       (130, 1, 'Brabant wallon', '1480', 'Tubize'),
       (131, 1, 'Brabant wallon', '1490', 'Court-Saint-Etienne'),
       (132, 1, 'Brabant wallon', '1495', 'Marbais'),
       (133, 1, 'Brabant wallon', '1495', 'Mellery'),
       (134, 1, 'Brabant wallon', '1495', 'Sart-Dames-Avelines'),
       (135, 1, 'Brabant wallon', '1495', 'Tilly'),
       (136, 1, 'Brabant wallon', '1495', 'Villers-La-Ville'),
       (137, 1, 'Brabant flamand', '1500', 'Halle'),
       (138, 1, 'Brabant flamand', '1501', 'Buizingen'),
       (139, 1, 'Brabant flamand', '1502', 'Lembeek'),
       (140, 1, 'Brabant flamand', '1540', 'Herfelingen'),
       (141, 1, 'Brabant flamand', '1540', 'Herne'),
       (142, 1, 'Brabant flamand', '1541', 'Sint-Pieters-Kapelle'),
       (143, 1, 'Brabant flamand', '1547', 'Biévène'),
       (144, 1, 'Brabant flamand', '1560', 'Hoeilaart'),
       (145, 1, 'Brabant flamand', '1570', 'Galmaarden'),
       (146, 1, 'Brabant flamand', '1570', 'Tollembeek'),
       (147, 1, 'Brabant flamand', '1570', 'Vollezele'),
       (148, 1, 'Brabant flamand', '1600', 'Oudenaken'),
       (149, 1, 'Brabant flamand', '1600', 'Sint-Laureins-Berchem'),
       (150, 1, 'Brabant flamand', '1600', 'Sint-Pieters-Leeuw'),
       (151, 1, 'Brabant flamand', '1601', 'Ruisbroek'),
       (152, 1, 'Brabant flamand', '1602', 'Vlezenbeek'),
       (153, 1, 'Brabant flamand', '1620', 'Drogenbos'),
       (154, 1, 'Brabant flamand', '1630', 'Linkebeek'),
       (155, 1, 'Brabant flamand', '1640', 'Rhode-Saint-Gencse'),
       (156, 1, 'Brabant flamand', '1650', 'Beersel'),
       (157, 1, 'Brabant flamand', '1651', 'Lot'),
       (158, 1, 'Brabant flamand', '1652', 'Alsemberg'),
       (159, 1, 'Brabant flamand', '1653', 'Dworp'),
       (160, 1, 'Brabant flamand', '1654', 'Huizingen'),
       (161, 1, 'Brabant flamand', '1670', 'Bogaarden'),
       (162, 1, 'Brabant flamand', '1670', 'Heikruis'),
       (163, 1, 'Brabant flamand', '1670', 'Pepingen'),
       (164, 1, 'Brabant flamand', '1671', 'Elingen'),
       (165, 1, 'Brabant flamand', '1673', 'Beert'),
       (166, 1, 'Brabant flamand', '1674', 'Bellingen'),
       (167, 1, 'Brabant flamand', '1700', 'Dilbeek'),
       (168, 1, 'Brabant flamand', '1700', 'Sint-Martens-Bodegem'),
       (169, 1, 'Brabant flamand', '1700', 'Sint-Ulriks-Kapelle'),
       (170, 1, 'Brabant flamand', '1701', 'Itterbeek'),
       (171, 1, 'Brabant flamand', '1702', 'Groot-Bijgaarden'),
       (172, 1, 'Brabant flamand', '1703', 'Schepdaal'),
       (173, 1, 'Brabant flamand', '1730', 'Asse'),
       (174, 1, 'Brabant flamand', '1730', 'Bekkerzeel'),
       (175, 1, 'Brabant flamand', '1730', 'Kobbegem'),
       (176, 1, 'Brabant flamand', '1730', 'Mollem'),
       (177, 1, 'Brabant flamand', '1731', 'Relegem'),
       (178, 1, 'Brabant flamand', '1731', 'Zellik'),
       (179, 1, 'Brabant flamand', '1733', 'HighCo DATA'),
       (180, 1, 'Brabant flamand', '1740', 'Ternat'),
       (181, 1, 'Brabant flamand', '1741', 'Wambeek'),
       (182, 1, 'Brabant flamand', '1742', 'Sint-Katherina-Lombeek'),
       (183, 1, 'Brabant flamand', '1745', 'Mazenzele'),
       (184, 1, 'Brabant flamand', '1745', 'Opwijk'),
       (185, 1, 'Brabant flamand', '1750', 'Gaasbeek'),
       (186, 1, 'Brabant flamand', '1750', 'Sint-Kwintens-Lennik'),
       (187, 1, 'Brabant flamand', '1750', 'Sint-Martens-Lennik'),
       (188, 1, 'Brabant flamand', '1755', 'Gooik'),
       (189, 1, 'Brabant flamand', '1755', 'Kester'),
       (190, 1, 'Brabant flamand', '1755', 'Leerbeek'),
       (191, 1, 'Brabant flamand', '1755', 'Oetingen'),
       (192, 1, 'Brabant flamand', '1760', 'Onze-Lieve-Vrouw-Lombeek'),
       (193, 1, 'Brabant flamand', '1760', 'Pamel'),
       (194, 1, 'Brabant flamand', '1760', 'Roosdaal'),
       (195, 1, 'Brabant flamand', '1760', 'Strijtem'),
       (196, 1, 'Brabant flamand', '1761', 'Borchtlombeek'),
       (197, 1, 'Brabant flamand', '1770', 'Liedekerke'),
       (198, 1, 'Brabant flamand', '1780', 'Wemmel'),
       (199, 1, 'Brabant flamand', '1785', 'Brussegem'),
       (200, 1, 'Brabant flamand', '1785', 'Hamme'),
       (201, 1, 'Brabant flamand', '1785', 'Merchtem'),
       (202, 1, 'Brabant flamand', '1790', 'Affligem'),
       (203, 1, 'Brabant flamand', '1790', 'Essene'),
       (204, 1, 'Brabant flamand', '1790', 'Hekelgem'),
       (205, 1, 'Brabant flamand', '1790', 'Teralfene'),
       (206, 1, 'Brabant flamand', '1800', 'Peutie'),
       (207, 1, 'Brabant flamand', '1800', 'Vilvoorde'),
       (208, 1, 'Brabant flamand', '1804', 'Cargovil'),
       (209, 1, 'Brabant flamand', '1818', 'VTM'),
       (210, 1, 'Brabant flamand', '1820', 'Melsbroek'),
       (211, 1, 'Brabant flamand', '1820', 'Perk'),
       (212, 1, 'Brabant flamand', '1820', 'Steenokkerzeel'),
       (213, 1, 'Brabant flamand', '1830', 'Machelen'),
       (214, 1, 'Brabant flamand', '1831', 'Diegem'),
       (215, 1, 'Brabant flamand', '1840', 'Londerzeel'),
       (216, 1, 'Brabant flamand', '1840', 'Malderen'),
       (217, 1, 'Brabant flamand', '1840', 'Steenhuffel'),
       (218, 1, 'Brabant flamand', '1850', 'Grimbergen'),
       (219, 1, 'Brabant flamand', '1851', 'Humbeek'),
       (220, 1, 'Brabant flamand', '1852', 'Beigem'),
       (221, 1, 'Brabant flamand', '1853', 'Strombeek-Bever'),
       (222, 1, 'Brabant flamand', '1860', 'Meise'),
       (223, 1, 'Brabant flamand', '1861', 'Wolvertem'),
       (224, 1, 'Brabant flamand', '1880', 'Kapelle-Op-Den-Bos'),
       (225, 1, 'Brabant flamand', '1880', 'Nieuwenrode'),
       (226, 1, 'Brabant flamand', '1880', 'Ramsdonk'),
       (227, 1, 'Brabant flamand', '1910', 'Berg'),
       (228, 1, 'Brabant flamand', '1910', 'Buken'),
       (229, 1, 'Brabant flamand', '1910', 'Kampenhout'),
       (230, 1, 'Brabant flamand', '1910', 'Nederokkerzeel'),
       (231, 1, 'Brabant flamand', '1930', 'Nossegem'),
       (232, 1, 'Brabant flamand', '1930', 'Zaventem'),
       (233, 1, 'Brabant flamand', '1932', 'Sint-Stevens-Woluwe'),
       (234, 1, 'Brabant flamand', '1933', 'Sterrebeek'),
       (235, 1, 'Brabant flamand', '1950', 'Kraainem'),
       (236, 1, 'Brabant flamand', '1970', 'Wezembeek-Oppem'),
       (237, 1, 'Brabant flamand', '1980', 'Eppegem'),
       (238, 1, 'Brabant flamand', '1980', 'Zemst'),
       (239, 1, 'Brabant flamand', '1981', 'Hofstade'),
       (240, 1, 'Brabant flamand', '1982', 'Elewijt'),
       (241, 1, 'Brabant flamand', '1982', 'Weerde'),
       (242, 1, 'Anvers', '2000', 'Antwerpen'),
       (243, 1, 'Anvers', '2018', 'Antwerpen'),
       (244, 1, 'Anvers', '2020', 'Antwerpen'),
       (245, 1, 'Anvers', '2030', 'Antwerpen'),
       (246, 1, 'Anvers', '2040', 'Antwerpen'),
       (247, 1, 'Anvers', '2040', 'Berendrecht'),
       (248, 1, 'Anvers', '2040', 'Lillo'),
       (249, 1, 'Anvers', '2040', 'Zandvliet'),
       (250, 1, 'Anvers', '2050', 'Antwerpen'),
       (251, 1, 'Anvers', '2060', 'Antwerpen'),
       (252, 1, 'Anvers', '2070', 'Burcht'),
       (253, 1, 'Anvers', '2070', 'Zwijndrecht'),
       (254, 1, 'Anvers', '2099', 'Antwerpen x'),
       (255, 1, 'Anvers', '2100', 'Deurne'),
       (256, 1, 'Anvers', '2110', 'Wijnegem'),
       (257, 1, 'Anvers', '2140', 'Borgerhout'),
       (258, 1, 'Anvers', '2150', 'Borsbeek'),
       (259, 1, 'Anvers', '2160', 'Wommelgem'),
       (260, 1, 'Anvers', '2170', 'Merksem'),
       (261, 1, 'Anvers', '2180', 'Ekeren'),
       (262, 1, 'Anvers', '2200', 'Herentals'),
       (263, 1, 'Anvers', '2200', 'Morkhoven'),
       (264, 1, 'Anvers', '2200', 'Noorderwijk'),
       (265, 1, 'Anvers', '2220', 'Hallaar'),
       (266, 1, 'Anvers', '2220', 'Heist-Op-Den-Berg'),
       (267, 1, 'Anvers', '2221', 'Booischot'),
       (268, 1, 'Anvers', '2222', 'Itegem'),
       (269, 1, 'Anvers', '2222', 'Wiekevorst'),
       (270, 1, 'Anvers', '2223', 'Schriek'),
       (271, 1, 'Anvers', '2230', 'Herselt'),
       (272, 1, 'Anvers', '2230', 'Ramsel'),
       (273, 1, 'Anvers', '2235', 'Houtvenne'),
       (274, 1, 'Anvers', '2235', 'Hulshout'),
       (275, 1, 'Anvers', '2235', 'Westmeerbeek'),
       (276, 1, 'Anvers', '2240', 'Massenhoven'),
       (277, 1, 'Anvers', '2240', 'Viersel'),
       (278, 1, 'Anvers', '2240', 'Zandhoven'),
       (279, 1, 'Anvers', '2242', 'Pulderbos'),
       (280, 1, 'Anvers', '2243', 'Pulle'),
       (281, 1, 'Anvers', '2250', 'Olen'),
       (282, 1, 'Anvers', '2260', 'Oevel'),
       (283, 1, 'Anvers', '2260', 'Tongerlo'),
       (284, 1, 'Anvers', '2260', 'Westerlo'),
       (285, 1, 'Anvers', '2260', 'Zoerle-Parwijs'),
       (286, 1, 'Anvers', '2270', 'Herenthout'),
       (287, 1, 'Anvers', '2275', 'Gierle'),
       (288, 1, 'Anvers', '2275', 'Lille'),
       (289, 1, 'Anvers', '2275', 'Poederlee'),
       (290, 1, 'Anvers', '2275', 'Wechelderzande'),
       (291, 1, 'Anvers', '2280', 'Grobbendonk'),
       (292, 1, 'Anvers', '2288', 'Bouwel'),
       (293, 1, 'Anvers', '2290', 'Vorselaar'),
       (294, 1, 'Anvers', '2300', 'Turnhout'),
       (295, 1, 'Anvers', '2310', 'Rijkevorsel'),
       (296, 1, 'Anvers', '2320', 'Hoogstraten'),
       (297, 1, 'Anvers', '2321', 'Meer'),
       (298, 1, 'Anvers', '2322', 'Minderhout'),
       (299, 1, 'Anvers', '2323', 'Wortel'),
       (300, 1, 'Anvers', '2328', 'Meerle'),
       (301, 1, 'Anvers', '2330', 'Merksplas'),
       (302, 1, 'Anvers', '2340', 'Beerse'),
       (303, 1, 'Anvers', '2340', 'Vlimmeren'),
       (304, 1, 'Anvers', '2350', 'Vosselaar'),
       (305, 1, 'Anvers', '2360', 'Oud-Turnhout'),
       (306, 1, 'Anvers', '2370', 'Arendonk'),
       (307, 1, 'Anvers', '2380', 'Ravels'),
       (308, 1, 'Anvers', '2381', 'Weelde'),
       (309, 1, 'Anvers', '2382', 'Poppel'),
       (310, 1, 'Anvers', '2387', 'Baarle-Hertog'),
       (311, 1, 'Anvers', '2390', 'Malle'),
       (312, 1, 'Anvers', '2390', 'Oostmalle'),
       (313, 1, 'Anvers', '2390', 'Westmalle'),
       (314, 1, 'Anvers', '2400', 'Mol'),
       (315, 1, 'Anvers', '2430', 'Eindhout'),
       (316, 1, 'Anvers', '2430', 'Vorst'),
       (317, 1, 'Anvers', '2431', 'Varendonk'),
       (318, 1, 'Anvers', '2431', 'Veerle'),
       (319, 1, 'Anvers', '2440', 'Geel'),
       (320, 1, 'Anvers', '2450', 'Meerhout'),
       (321, 1, 'Anvers', '2460', 'Kasterlee'),
       (322, 1, 'Anvers', '2460', 'Lichtaart'),
       (323, 1, 'Anvers', '2460', 'Tielen'),
       (324, 1, 'Anvers', '2470', 'Retie'),
       (325, 1, 'Anvers', '2480', 'Dessel'),
       (326, 1, 'Anvers', '2490', 'Balen'),
       (327, 1, 'Anvers', '2491', 'Olmen'),
       (328, 1, 'Anvers', '2500', 'Koningshooikt'),
       (329, 1, 'Anvers', '2500', 'Lier'),
       (330, 1, 'Anvers', '2520', 'Broechem'),
       (331, 1, 'Anvers', '2520', 'Emblem'),
       (332, 1, 'Anvers', '2520', 'Oelegem'),
       (333, 1, 'Anvers', '2520', 'Ranst'),
       (334, 1, 'Anvers', '2530', 'Boechout'),
       (335, 1, 'Anvers', '2531', 'Vremde'),
       (336, 1, 'Anvers', '2540', 'Hove'),
       (337, 1, 'Anvers', '2547', 'Lint'),
       (338, 1, 'Anvers', '2550', 'Kontich'),
       (339, 1, 'Anvers', '2550', 'Waarloos'),
       (340, 1, 'Anvers', '2560', 'Bevel'),
       (341, 1, 'Anvers', '2560', 'Kessel'),
       (342, 1, 'Anvers', '2560', 'Nijlen'),
       (343, 1, 'Anvers', '2570', 'Duffel'),
       (344, 1, 'Anvers', '2580', 'Beerzel'),
       (345, 1, 'Anvers', '2580', 'Putte'),
       (346, 1, 'Anvers', '2590', 'Berlaar'),
       (347, 1, 'Anvers', '2590', 'Gestel'),
       (348, 1, 'Anvers', '2600', 'Berchem'),
       (349, 1, 'Anvers', '2610', 'Wilrijk'),
       (350, 1, 'Anvers', '2620', 'Hemiksem'),
       (351, 1, 'Anvers', '2627', 'Schelle'),
       (352, 1, 'Anvers', '2630', 'Aartselaar'),
       (353, 1, 'Anvers', '2640', 'Mortsel'),
       (354, 1, 'Anvers', '2650', 'Edegem'),
       (355, 1, 'Anvers', '2660', 'Hoboken'),
       (356, 1, 'Anvers', '2800', 'Mechelen'),
       (357, 1, 'Anvers', '2800', 'Walem'),
       (358, 1, 'Anvers', '2801', 'Heffen'),
       (359, 1, 'Anvers', '2811', 'Hombeek'),
       (360, 1, 'Anvers', '2811', 'Leest'),
       (361, 1, 'Anvers', '2812', 'Muizen'),
       (362, 1, 'Anvers', '2820', 'Bonheiden'),
       (363, 1, 'Anvers', '2820', 'Rijmenam'),
       (364, 1, 'Anvers', '2830', 'Blaasveld'),
       (365, 1, 'Anvers', '2830', 'Heindonk'),
       (366, 1, 'Anvers', '2830', 'Tisselt'),
       (367, 1, 'Anvers', '2830', 'Willebroek'),
       (368, 1, 'Anvers', '2840', 'Reet'),
       (369, 1, 'Anvers', '2840', 'Rumst'),
       (370, 1, 'Anvers', '2840', 'Terhagen'),
       (371, 1, 'Anvers', '2845', 'Niel'),
       (372, 1, 'Anvers', '2850', 'Boom'),
       (373, 1, 'Anvers', '2860', 'Sint-Katelijne-Waver'),
       (374, 1, 'Anvers', '2861', 'Onze-Lieve-Vrouw-Waver'),
       (375, 1, 'Anvers', '2870', 'Breendonk'),
       (376, 1, 'Anvers', '2870', 'Liezele'),
       (377, 1, 'Anvers', '2870', 'Puurs'),
       (378, 1, 'Anvers', '2870', 'Ruisbroek'),
       (379, 1, 'Anvers', '2880', 'Bornem'),
       (380, 1, 'Anvers', '2880', 'Hingene'),
       (381, 1, 'Anvers', '2880', 'Mariekerke'),
       (382, 1, 'Anvers', '2880', 'Weert'),
       (383, 1, 'Anvers', '2890', 'Lippelo'),
       (384, 1, 'Anvers', '2890', 'Oppuurs'),
       (385, 1, 'Anvers', '2890', 'Sint-Amands'),
       (386, 1, 'Anvers', '2900', 'Schoten'),
       (387, 1, 'Anvers', '2910', 'Essen'),
       (388, 1, 'Anvers', '2920', 'Kalmthout'),
       (389, 1, 'Anvers', '2930', 'Brasschaat'),
       (390, 1, 'Anvers', '2940', 'Hoevenen'),
       (391, 1, 'Anvers', '2940', 'Stabroek'),
       (392, 1, 'Anvers', '2950', 'Kapellen'),
       (393, 1, 'Anvers', '2960', 'Brecht'),
       (394, 1, 'Anvers', '2960', 'Sint-Job-In-\'T-Goor'),
(395, 1, 'Anvers', '2960', 'Sint-Lenaarts'),
(396, 1, 'Anvers', '2970', '\'S Gravenwezel'),
       (397, 1, 'Anvers', '2970', 'Schilde'),
       (398, 1, 'Anvers', '2980', 'Halle'),
       (399, 1, 'Anvers', '2980', 'Zoersel'),
       (400, 1, 'Anvers', '2990', 'Loenhout'),
       (401, 1, 'Anvers', '2990', 'Wuustwezel'),
       (402, 1, 'Brabant flamand', '3000', 'Leuven'),
       (403, 1, 'Brabant flamand', '3001', 'Heverlee'),
       (404, 1, 'Brabant flamand', '3010', 'Kessel Lo'),
       (405, 1, 'Brabant flamand', '3012', 'Wilsele'),
       (406, 1, 'Brabant flamand', '3018', 'Wijgmaal'),
       (407, 1, 'Brabant flamand', '3020', 'Herent'),
       (408, 1, 'Brabant flamand', '3020', 'Veltem-Beisem'),
       (409, 1, 'Brabant flamand', '3020', 'Winksele'),
       (410, 1, 'Brabant flamand', '3040', 'Huldenberg'),
       (411, 1, 'Brabant flamand', '3040', 'Loonbeek'),
       (412, 1, 'Brabant flamand', '3040', 'Neerijse'),
       (413, 1, 'Brabant flamand', '3040', 'Ottenburg'),
       (414, 1, 'Brabant flamand', '3040', 'Sint-Agatha-Rode'),
       (415, 1, 'Brabant flamand', '3050', 'Oud-Heverlee'),
       (416, 1, 'Brabant flamand', '3051', 'Sint-Joris-Weert'),
       (417, 1, 'Brabant flamand', '3052', 'Blanden'),
       (418, 1, 'Brabant flamand', '3053', 'Haasrode'),
       (419, 1, 'Brabant flamand', '3054', 'Vaalbeek'),
       (420, 1, 'Brabant flamand', '3060', 'Bertem'),
       (421, 1, 'Brabant flamand', '3060', 'Korbeek-Dijle'),
       (422, 1, 'Brabant flamand', '3061', 'Leefdaal'),
       (423, 1, 'Brabant flamand', '3070', 'Kortenberg'),
       (424, 1, 'Brabant flamand', '3071', 'Erps-Kwerps'),
       (425, 1, 'Brabant flamand', '3078', 'Everberg'),
       (426, 1, 'Brabant flamand', '3078', 'Meerbeek'),
       (427, 1, 'Brabant flamand', '3080', 'Duisburg'),
       (428, 1, 'Brabant flamand', '3080', 'Tervuren'),
       (429, 1, 'Brabant flamand', '3080', 'Vossem'),
       (430, 1, 'Brabant flamand', '3090', 'Overijse'),
       (431, 1, 'Brabant flamand', '3110', 'Rotselaar'),
       (432, 1, 'Brabant flamand', '3111', 'Wezemaal'),
       (433, 1, 'Brabant flamand', '3118', 'Werchter'),
       (434, 1, 'Brabant flamand', '3120', 'Tremelo'),
       (435, 1, 'Brabant flamand', '3128', 'Baal'),
       (436, 1, 'Brabant flamand', '3130', 'Begijnendijk'),
       (437, 1, 'Brabant flamand', '3130', 'Betekom'),
       (438, 1, 'Brabant flamand', '3140', 'Keerbergen'),
       (439, 1, 'Brabant flamand', '3150', 'Haacht'),
       (440, 1, 'Brabant flamand', '3150', 'Tildonk'),
       (441, 1, 'Brabant flamand', '3150', 'Wespelaar'),
       (442, 1, 'Brabant flamand', '3190', 'Boortmeerbeek'),
       (443, 1, 'Brabant flamand', '3191', 'Hever'),
       (444, 1, 'Brabant flamand', '3200', 'Aarschot'),
       (445, 1, 'Brabant flamand', '3200', 'Gelrode'),
       (446, 1, 'Brabant flamand', '3201', 'Langdorp'),
       (447, 1, 'Brabant flamand', '3202', 'Rillaar'),
       (448, 1, 'Brabant flamand', '3210', 'Linden'),
       (449, 1, 'Brabant flamand', '3210', 'Lubbeek'),
       (450, 1, 'Brabant flamand', '3211', 'Binkom'),
       (451, 1, 'Brabant flamand', '3212', 'Pellenberg'),
       (452, 1, 'Brabant flamand', '3220', 'Holsbeek'),
       (453, 1, 'Brabant flamand', '3220', 'Kortrijk-Dutsel'),
       (454, 1, 'Brabant flamand', '3220', 'Sint-Pieters-Rode'),
       (455, 1, 'Brabant flamand', '3221', 'Nieuwrode'),
       (456, 1, 'Brabant flamand', '3270', 'Scherpenheuvel'),
       (457, 1, 'Brabant flamand', '3271', 'Averbode'),
       (458, 1, 'Brabant flamand', '3271', 'Zichem'),
       (459, 1, 'Brabant flamand', '3272', 'Messelbroek'),
       (460, 1, 'Brabant flamand', '3272', 'Testelt'),
       (461, 1, 'Brabant flamand', '3290', 'Deurne'),
       (462, 1, 'Brabant flamand', '3290', 'Diest'),
       (463, 1, 'Brabant flamand', '3290', 'Schaffen'),
       (464, 1, 'Brabant flamand', '3290', 'Webbekom'),
       (465, 1, 'Brabant flamand', '3293', 'Kaggevinne'),
       (466, 1, 'Brabant flamand', '3294', 'Molenstede'),
       (467, 1, 'Brabant flamand', '3300', 'Bost'),
       (468, 1, 'Brabant flamand', '3300', 'Goetsenhoven'),
       (469, 1, 'Brabant flamand', '3300', 'Hakendover'),
       (470, 1, 'Brabant flamand', '3300', 'Kumtich'),
       (471, 1, 'Brabant flamand', '3300', 'Oorbeek'),
       (472, 1, 'Brabant flamand', '3300', 'Oplinter'),
       (473, 1, 'Brabant flamand', '3300', 'Sint-Margriete-Houtem'),
       (474, 1, 'Brabant flamand', '3300', 'Tienen'),
       (475, 1, 'Brabant flamand', '3300', 'Vissenaken'),
       (476, 1, 'Brabant flamand', '3320', 'Hoegaarden'),
       (477, 1, 'Brabant flamand', '3320', 'Meldert'),
       (478, 1, 'Brabant flamand', '3321', 'Outgaarden'),
       (479, 1, 'Brabant flamand', '3350', 'Drieslinter'),
       (480, 1, 'Brabant flamand', '3350', 'Linter'),
       (481, 1, 'Brabant flamand', '3350', 'Melkwezer'),
       (482, 1, 'Brabant flamand', '3350', 'Neerhespen'),
       (483, 1, 'Brabant flamand', '3350', 'Neerlinter'),
       (484, 1, 'Brabant flamand', '3350', 'Orsmaal-Gussenhoven'),
       (485, 1, 'Brabant flamand', '3350', 'Overhespen'),
       (486, 1, 'Brabant flamand', '3350', 'Wommersom'),
       (487, 1, 'Brabant flamand', '3360', 'Bierbeek'),
       (488, 1, 'Brabant flamand', '3360', 'Korbeek-Lo'),
       (489, 1, 'Brabant flamand', '3360', 'Lovenjoel'),
       (490, 1, 'Brabant flamand', '3360', 'Opvelp'),
       (491, 1, 'Brabant flamand', '3370', 'Boutersem'),
       (492, 1, 'Brabant flamand', '3370', 'Kerkom'),
       (493, 1, 'Brabant flamand', '3370', 'Neervelp'),
       (494, 1, 'Brabant flamand', '3370', 'Roosbeek'),
       (495, 1, 'Brabant flamand', '3370', 'Vertrijk'),
       (496, 1, 'Brabant flamand', '3370', 'Willebringen'),
       (497, 1, 'Brabant flamand', '3380', 'Bunsbeek'),
       (498, 1, 'Brabant flamand', '3380', 'Glabbeek'),
       (499, 1, 'Brabant flamand', '3381', 'Kapellen'),
       (500, 1, 'Brabant flamand', '3384', 'Attenrode'),
       (501, 1, 'Brabant flamand', '3390', 'Houwaart'),
       (502, 1, 'Brabant flamand', '3390', 'Sint-Joris-Winge'),
       (503, 1, 'Brabant flamand', '3390', 'Tielt'),
       (504, 1, 'Brabant flamand', '3391', 'Meensel-Kiezegem'),
       (505, 1, 'Brabant flamand', '3400', 'Eliksem'),
       (506, 1, 'Brabant flamand', '3400', 'Ezemaal'),
       (507, 1, 'Brabant flamand', '3400', 'Laar'),
       (508, 1, 'Brabant flamand', '3400', 'Landen'),
       (509, 1, 'Brabant flamand', '3400', 'Neerwinden'),
       (510, 1, 'Brabant flamand', '3400', 'Overwinden'),
       (511, 1, 'Brabant flamand', '3400', 'Rumsdorp'),
       (512, 1, 'Brabant flamand', '3400', 'Wange'),
       (513, 1, 'Brabant flamand', '3401', 'Waasmont'),
       (514, 1, 'Brabant flamand', '3401', 'Walsbets'),
       (515, 1, 'Brabant flamand', '3401', 'Walshoutem'),
       (516, 1, 'Brabant flamand', '3401', 'Wezeren'),
       (517, 1, 'Brabant flamand', '3404', 'Attenhoven'),
       (518, 1, 'Brabant flamand', '3404', 'Neerlanden'),
       (519, 1, 'Brabant flamand', '3440', 'Budingen'),
       (520, 1, 'Brabant flamand', '3440', 'Dormaal'),
       (521, 1, 'Brabant flamand', '3440', 'Halle-Booienhoven'),
       (522, 1, 'Brabant flamand', '3440', 'Helen-Bos'),
       (523, 1, 'Brabant flamand', '3440', 'Zoutleeuw'),
       (524, 1, 'Brabant flamand', '3450', 'Geetbets'),
       (525, 1, 'Brabant flamand', '3450', 'Grazen'),
       (526, 1, 'Brabant flamand', '3454', 'Rummen'),
       (527, 1, 'Brabant flamand', '3460', 'Assent'),
       (528, 1, 'Brabant flamand', '3460', 'Bekkevoort'),
       (529, 1, 'Brabant flamand', '3461', 'Molenbeek-Wersbeek'),
       (530, 1, 'Brabant flamand', '3470', 'Kortenaken'),
       (531, 1, 'Brabant flamand', '3470', 'Ransberg'),
       (532, 1, 'Brabant flamand', '3470', 'Sint-Margriete-Houtem'),
       (533, 1, 'Brabant flamand', '3471', 'Hoeleden'),
       (534, 1, 'Brabant flamand', '3472', 'Kersbeek-Miskom'),
       (535, 1, 'Brabant flamand', '3473', 'Waanrode'),
       (536, 1, 'Limbourg', '3500', 'Hasselt'),
       (537, 1, 'Limbourg', '3500', 'Sint-Lambrechts-Herk'),
       (538, 1, 'Limbourg', '3501', 'Wimmertingen'),
       (539, 1, 'Limbourg', '3510', 'Kermt'),
       (540, 1, 'Limbourg', '3510', 'Spalbeek'),
       (541, 1, 'Limbourg', '3511', 'Kuringen'),
       (542, 1, 'Limbourg', '3511', 'Stokrooie'),
       (543, 1, 'Limbourg', '3512', 'Stevoort'),
       (544, 1, 'Limbourg', '3520', 'Zonhoven'),
       (545, 1, 'Limbourg', '3530', 'Helchteren'),
       (546, 1, 'Limbourg', '3530', 'Houthalen'),
       (547, 1, 'Limbourg', '3540', 'Berbroek'),
       (548, 1, 'Limbourg', '3540', 'Donk'),
       (549, 1, 'Limbourg', '3540', 'Herk-De-Stad'),
       (550, 1, 'Limbourg', '3540', 'Schulen'),
       (551, 1, 'Limbourg', '3545', 'Halen'),
       (552, 1, 'Limbourg', '3545', 'Loksbergen'),
       (553, 1, 'Limbourg', '3545', 'Zelem'),
       (554, 1, 'Limbourg', '3550', 'Heusden'),
       (555, 1, 'Limbourg', '3550', 'Heusden-Zolder'),
       (556, 1, 'Limbourg', '3550', 'Zolder'),
       (557, 1, 'Limbourg', '3560', 'Linkhout'),
       (558, 1, 'Limbourg', '3560', 'Lummen'),
       (559, 1, 'Limbourg', '3560', 'Meldert'),
       (560, 1, 'Limbourg', '3570', 'Alken'),
       (561, 1, 'Limbourg', '3580', 'Beringen'),
       (562, 1, 'Limbourg', '3581', 'Beverlo'),
       (563, 1, 'Limbourg', '3582', 'Koersel'),
       (564, 1, 'Limbourg', '3583', 'Paal'),
       (565, 1, 'Limbourg', '3590', 'Diepenbeek'),
       (566, 1, 'Limbourg', '3600', 'Genk'),
       (567, 1, 'Limbourg', '3620', 'Gellik'),
       (568, 1, 'Limbourg', '3620', 'Lanaken'),
       (569, 1, 'Limbourg', '3620', 'Neerharen'),
       (570, 1, 'Limbourg', '3620', 'Veldwezelt'),
       (571, 1, 'Limbourg', '3621', 'Rekem'),
       (572, 1, 'Limbourg', '3630', 'Eisden'),
       (573, 1, 'Limbourg', '3630', 'Leut'),
       (574, 1, 'Limbourg', '3630', 'Maasmechelen'),
       (575, 1, 'Limbourg', '3630', 'Mechelen-Aan-De-Maas'),
       (576, 1, 'Limbourg', '3630', 'Meeswijk'),
       (577, 1, 'Limbourg', '3630', 'Opgrimbie'),
       (578, 1, 'Limbourg', '3630', 'Vucht'),
       (579, 1, 'Limbourg', '3631', 'Boorsem'),
       (580, 1, 'Limbourg', '3631', 'Uikhoven'),
       (581, 1, 'Limbourg', '3640', 'Kessenich'),
       (582, 1, 'Limbourg', '3640', 'Kinrooi'),
       (583, 1, 'Limbourg', '3640', 'Molenbeersel'),
       (584, 1, 'Limbourg', '3640', 'Ophoven'),
       (585, 1, 'Limbourg', '3650', 'Dilsen'),
       (586, 1, 'Limbourg', '3650', 'Dilsen-Stokkem'),
       (587, 1, 'Limbourg', '3650', 'Elen'),
       (588, 1, 'Limbourg', '3650', 'Lanklaar'),
       (589, 1, 'Limbourg', '3650', 'Rotem'),
       (590, 1, 'Limbourg', '3650', 'Stokkem'),
       (591, 1, 'Limbourg', '3660', 'Opglabbeek'),
       (592, 1, 'Limbourg', '3665', 'As'),
       (593, 1, 'Limbourg', '3668', 'Niel-Bij-As'),
       (594, 1, 'Limbourg', '3670', 'Ellikom'),
       (595, 1, 'Limbourg', '3670', 'Gruitrode'),
       (596, 1, 'Limbourg', '3670', 'Meeuwen'),
       (597, 1, 'Limbourg', '3670', 'Neerglabbeek'),
       (598, 1, 'Limbourg', '3670', 'Wijshagen'),
       (599, 1, 'Limbourg', '3680', 'Maaseik'),
       (600, 1, 'Limbourg', '3680', 'Neeroeteren'),
       (601, 1, 'Limbourg', '3680', 'Opoeteren'),
       (602, 1, 'Limbourg', '3690', 'Zutendaal'),
       (603, 1, 'Limbourg', '3700', '\'S Herenelderen'),
(604, 1, 'Limbourg', '3700', 'Berg'),
(605, 1, 'Limbourg', '3700', 'Diets-Heur'),
(606, 1, 'Limbourg', '3700', 'Haren'),
(607, 1, 'Limbourg', '3700', 'Henis'),
(608, 1, 'Limbourg', '3700', 'Kolmont'),
(609, 1, 'Limbourg', '3700', 'Koninksem'),
(610, 1, 'Limbourg', '3700', 'Lauw'),
(611, 1, 'Limbourg', '3700', 'Mal'),
(612, 1, 'Limbourg', '3700', 'Neerrepen'),
(613, 1, 'Limbourg', '3700', 'Nerem'),
(614, 1, 'Limbourg', '3700', 'Overrepen'),
(615, 1, 'Limbourg', '3700', 'Piringen'),
(616, 1, 'Limbourg', '3700', 'Riksingen'),
(617, 1, 'Limbourg', '3700', 'Rutten'),
(618, 1, 'Limbourg', '3700', 'Sluizen'),
(619, 1, 'Limbourg', '3700', 'Tongeren'),
(620, 1, 'Limbourg', '3700', 'Vreren'),
(621, 1, 'Limbourg', '3700', 'Widooie'),
(622, 1, 'Limbourg', '3717', 'Herstappe'),
(623, 1, 'Limbourg', '3720', 'Kortessem'),
(624, 1, 'Limbourg', '3721', 'Vliermaalroot'),
(625, 1, 'Limbourg', '3722', 'Wintershoven'),
(626, 1, 'Limbourg', '3723', 'Guigoven'),
(627, 1, 'Limbourg', '3724', 'Vliermaal'),
(628, 1, 'Limbourg', '3730', 'Hoeselt'),
(629, 1, 'Limbourg', '3730', 'Romershoven'),
(630, 1, 'Limbourg', '3730', 'Sint-Huibrechts-Hern'),
(631, 1, 'Limbourg', '3730', 'Werm'),
(632, 1, 'Limbourg', '3732', 'Schalkhoven'),
(633, 1, 'Limbourg', '3740', 'Beverst'),
(634, 1, 'Limbourg', '3740', 'Bilzen'),
(635, 1, 'Limbourg', '3740', 'Eigenbilzen'),
(636, 1, 'Limbourg', '3740', 'Grote-Spouwen'),
(637, 1, 'Limbourg', '3740', 'Hees'),
(638, 1, 'Limbourg', '3740', 'Kleine-Spouwen'),
(639, 1, 'Limbourg', '3740', 'Mopertingen'),
(640, 1, 'Limbourg', '3740', 'Munsterbilzen'),
(641, 1, 'Limbourg', '3740', 'Rijkhoven'),
(642, 1, 'Limbourg', '3740', 'Rosmeer'),
(643, 1, 'Limbourg', '3740', 'Waltwilder'),
(644, 1, 'Limbourg', '3742', 'Martenslinde'),
(645, 1, 'Limbourg', '3746', 'Hoelbeek'),
(646, 1, 'Limbourg', '3770', 'Genoelselderen'),
(647, 1, 'Limbourg', '3770', 'Herderen'),
(648, 1, 'Limbourg', '3770', 'Kanne'),
(649, 1, 'Limbourg', '3770', 'Membruggen'),
(650, 1, 'Limbourg', '3770', 'Millen'),
(651, 1, 'Limbourg', '3770', 'Riemst'),
(652, 1, 'Limbourg', '3770', 'Val-Meer'),
(653, 1, 'Limbourg', '3770', 'Vlijtingen'),
(654, 1, 'Limbourg', '3770', 'Vroenhoven'),
(655, 1, 'Limbourg', '3770', 'Zichen-Zussen-Bolder'),
(656, 1, 'Limbourg', '3790', 'Fouron-Saint-Martin'),
(657, 1, 'Limbourg', '3790', 'Mouland'),
(658, 1, 'Limbourg', '3791', 'Remersdaal'),
(659, 1, 'Limbourg', '3792', 'Fouron-Saint-Pierre'),
(660, 1, 'Limbourg', '3793', 'Teuven'),
(661, 1, 'Limbourg', '3798', 'Fouron-Le-Comte'),
(662, 1, 'Limbourg', '3800', 'Aalst'),
(663, 1, 'Limbourg', '3800', 'Brustem'),
(664, 1, 'Limbourg', '3800', 'Engelmanshoven'),
(665, 1, 'Limbourg', '3800', 'Gelinden'),
(666, 1, 'Limbourg', '3800', 'Groot-Gelmen'),
(667, 1, 'Limbourg', '3800', 'Halmaal'),
(668, 1, 'Limbourg', '3800', 'Kerkom-Bij-Sint-Truiden'),
(669, 1, 'Limbourg', '3800', 'Ordingen'),
(670, 1, 'Limbourg', '3800', 'Sint-Truiden'),
(671, 1, 'Limbourg', '3800', 'Zepperen'),
(672, 1, 'Limbourg', '3803', 'Duras'),
(673, 1, 'Limbourg', '3803', 'Gorsem'),
(674, 1, 'Limbourg', '3803', 'Runkelen'),
(675, 1, 'Limbourg', '3803', 'Wilderen'),
(676, 1, 'Limbourg', '3806', 'Velm'),
(677, 1, 'Limbourg', '3830', 'Berlingen'),
(678, 1, 'Limbourg', '3830', 'Wellen'),
(679, 1, 'Limbourg', '3831', 'Herten'),
(680, 1, 'Limbourg', '3832', 'Ulbeek'),
(681, 1, 'Limbourg', '3840', 'Bommershoven'),
(682, 1, 'Limbourg', '3840', 'Borgloon'),
(683, 1, 'Limbourg', '3840', 'Broekom'),
(684, 1, 'Limbourg', '3840', 'Gors-Opleeuw'),
(685, 1, 'Limbourg', '3840', 'Gotem'),
(686, 1, 'Limbourg', '3840', 'Groot-Loon'),
(687, 1, 'Limbourg', '3840', 'Haren'),
(688, 1, 'Limbourg', '3840', 'Hendrieken'),
(689, 1, 'Limbourg', '3840', 'Hoepertingen'),
(690, 1, 'Limbourg', '3840', 'Jesseren'),
(691, 1, 'Limbourg', '3840', 'Kerniel'),
(692, 1, 'Limbourg', '3840', 'Kolmont'),
(693, 1, 'Limbourg', '3840', 'Kuttekoven'),
(694, 1, 'Limbourg', '3840', 'Rijkel'),
(695, 1, 'Limbourg', '3840', 'Voort'),
(696, 1, 'Limbourg', '3850', 'Binderveld'),
(697, 1, 'Limbourg', '3850', 'Kozen'),
(698, 1, 'Limbourg', '3850', 'Nieuwerkerken'),
(699, 1, 'Limbourg', '3850', 'Wijer'),
(700, 1, 'Limbourg', '3870', 'Batsheers'),
(701, 1, 'Limbourg', '3870', 'Bovelingen'),
(702, 1, 'Limbourg', '3870', 'Gutshoven'),
(703, 1, 'Limbourg', '3870', 'Heers'),
(704, 1, 'Limbourg', '3870', 'Heks'),
(705, 1, 'Limbourg', '3870', 'Horpmaal'),
(706, 1, 'Limbourg', '3870', 'Klein-Gelmen'),
(707, 1, 'Limbourg', '3870', 'Mechelen-Bovelingen'),
(708, 1, 'Limbourg', '3870', 'Mettekoven'),
(709, 1, 'Limbourg', '3870', 'Opheers'),
(710, 1, 'Limbourg', '3870', 'Rukkelingen-Loon'),
(711, 1, 'Limbourg', '3870', 'Vechmaal'),
(712, 1, 'Limbourg', '3870', 'Veulen'),
(713, 1, 'Limbourg', '3890', 'Boekhout'),
(714, 1, 'Limbourg', '3890', 'Gingelom'),
(715, 1, 'Limbourg', '3890', 'Jeuk'),
(716, 1, 'Limbourg', '3890', 'Kortijs'),
(717, 1, 'Limbourg', '3890', 'Montenaken'),
(718, 1, 'Limbourg', '3890', 'Niel-Bij-Sint-Truiden'),
(719, 1, 'Limbourg', '3890', 'Vorsen'),
(720, 1, 'Limbourg', '3891', 'Borlo'),
(721, 1, 'Limbourg', '3891', 'Buvingen'),
(722, 1, 'Limbourg', '3891', 'Mielen-Boven-Aalst'),
(723, 1, 'Limbourg', '3891', 'Muizen'),
(724, 1, 'Limbourg', '3900', 'Overpelt'),
(725, 1, 'Limbourg', '3910', 'Neerpelt'),
(726, 1, 'Limbourg', '3910', 'Sint-Huibrechts-Lille'),
(727, 1, 'Limbourg', '3920', 'Lommel'),
(728, 1, 'Limbourg', '3930', 'Achel'),
(729, 1, 'Limbourg', '3930', 'Hamont'),
(730, 1, 'Limbourg', '3940', 'Hechtel'),
(731, 1, 'Limbourg', '3941', 'Eksel'),
(732, 1, 'Limbourg', '3945', 'Kwaadmechelen'),
(733, 1, 'Limbourg', '3945', 'Oostham'),
(734, 1, 'Limbourg', '3950', 'Bocholt'),
(735, 1, 'Limbourg', '3950', 'Kaulille'),
(736, 1, 'Limbourg', '3950', 'Reppel'),
(737, 1, 'Limbourg', '3960', 'Beek'),
(738, 1, 'Limbourg', '3960', 'Bree'),
(739, 1, 'Limbourg', '3960', 'Gerdingen'),
(740, 1, 'Limbourg', '3960', 'Opitter'),
(741, 1, 'Limbourg', '3960', 'Tongerlo'),
(742, 1, 'Limbourg', '3970', 'Leopoldsburg'),
(743, 1, 'Limbourg', '3971', 'Heppen'),
(744, 1, 'Limbourg', '3980', 'Tessenderlo'),
(745, 1, 'Limbourg', '3990', 'Grote-Brogel'),
(746, 1, 'Limbourg', '3990', 'Kleine-Brogel'),
(747, 1, 'Limbourg', '3990', 'Peer'),
(748, 1, 'Limbourg', '3990', 'Wijchmaal'),
(749, 1, 'Liège', '4000', 'Glain'),
(750, 1, 'Liège', '4000', 'Liège'),
(751, 1, 'Liège', '4000', 'Rocourt'),
(752, 1, 'Liège', '4020', 'Bressoux'),
(753, 1, 'Liège', '4020', 'Jupille-Sur-Meuse'),
(754, 1, 'Liège', '4020', 'Licge'),
(755, 1, 'Liège', '4020', 'Wandre'),
(756, 1, 'Liège', '4030', 'Grivegnée'),
(757, 1, 'Liège', '4031', 'Angleur'),
(758, 1, 'Liège', '4032', 'Chenée'),
(759, 1, 'Liège', '4040', 'Herstal'),
(760, 1, 'Liège', '4041', 'Milmort'),
(761, 1, 'Liège', '4041', 'Vottem'),
(762, 1, 'Liège', '4042', 'Liers'),
(763, 1, 'Liège', '4050', 'Chaudfontaine'),
(764, 1, 'Liège', '4051', 'Vaux-Sous-Chèvremont'),
(765, 1, 'Liège', '4052', 'Beaufays'),
(766, 1, 'Liège', '4053', 'Embourg'),
(767, 1, 'Liège', '4099', 'Licge X'),
(768, 1, 'Liège', '4100', 'Boncelles'),
(769, 1, 'Liège', '4100', 'Seraing'),
(770, 1, 'Liège', '4101', 'Jemeppe-Sur-Meuse'),
(771, 1, 'Liège', '4102', 'Ougrée'),
(772, 1, 'Liège', '4120', 'Ehein'),
(773, 1, 'Liège', '4120', 'Rotheux-Rimicre'),
(774, 1, 'Liège', '4121', 'Neuville-En-Condroz'),
(775, 1, 'Liège', '4122', 'Plainevaux'),
(776, 1, 'Liège', '4130', 'Esneux'),
(777, 1, 'Liège', '4130', 'Tilff'),
(778, 1, 'Liège', '4140', 'Dolembreux'),
(779, 1, 'Liège', '4140', 'Gomzé-Andoumont'),
(780, 1, 'Liège', '4140', 'Rouvreux'),
(781, 1, 'Liège', '4140', 'Sprimont'),
(782, 1, 'Liège', '4141', 'Louveigné'),
(783, 1, 'Liège', '4160', 'Anthisnes'),
(784, 1, 'Liège', '4161', 'Villers-Aux-Tours'),
(785, 1, 'Liège', '4162', 'Hody'),
(786, 1, 'Liège', '4163', 'Tavier'),
(787, 1, 'Liège', '4170', 'Comblain-Au-Pont'),
(788, 1, 'Liège', '4171', 'Poulseur'),
(789, 1, 'Liège', '4180', 'Comblain-Fairon'),
(790, 1, 'Liège', '4180', 'Comblain-La-Tour'),
(791, 1, 'Liège', '4180', 'Hamoir'),
(792, 1, 'Liège', '4181', 'Filot'),
(793, 1, 'Liège', '4190', 'Ferricres'),
(794, 1, 'Liège', '4190', 'My'),
(795, 1, 'Liège', '4190', 'Vieuxville'),
(796, 1, 'Liège', '4190', 'Werbomont'),
(797, 1, 'Liège', '4190', 'Xhoris'),
(798, 1, 'Liège', '4210', 'Burdinne'),
(799, 1, 'Liège', '4210', 'Hanneche'),
(800, 1, 'Liège', '4210', 'Lamontzée'),
(801, 1, 'Liège', '4210', 'Marneffe'),
(802, 1, 'Liège', '4210', 'Oteppe'),
(803, 1, 'Liège', '4217', 'Héron'),
(804, 1, 'Liège', '4217', 'Lavoir'),
(805, 1, 'Liège', '4217', 'Waret-L\'Eveque'),
       (806, 1, 'Liège', '4218', 'Couthuin'),
       (807, 1, 'Liège', '4219', 'Acosse'),
       (808, 1, 'Liège', '4219', 'Ambresin'),
       (809, 1, 'Liège', '4219', 'Meeffe'),
       (810, 1, 'Liège', '4219', 'Wasseiges'),
       (811, 1, 'Liège', '4250', 'Boëlhe'),
       (812, 1, 'Liège', '4250', 'Geer'),
       (813, 1, 'Liège', '4250', 'Hollogne-Sur-Geer'),
       (814, 1, 'Liège', '4250', 'Lens-Saint-Servais'),
       (815, 1, 'Liège', '4252', 'Omal'),
       (816, 1, 'Liège', '4253', 'Darion'),
       (817, 1, 'Liège', '4254', 'Ligney'),
       (818, 1, 'Liège', '4257', 'Berloz'),
       (819, 1, 'Liège', '4257', 'Corswarem'),
       (820, 1, 'Liège', '4257', 'Rosoux-Crenwick'),
       (821, 1, 'Liège', '4260', 'Avennes'),
       (822, 1, 'Liège', '4260', 'Braives'),
       (823, 1, 'Liège', '4260', 'Ciplet'),
       (824, 1, 'Liège', '4260', 'Fallais'),
       (825, 1, 'Liège', '4260', 'Fumal'),
       (826, 1, 'Liège', '4260', 'Ville-En-Hesbaye'),
       (827, 1, 'Liège', '4261', 'Latinne'),
       (828, 1, 'Liège', '4263', 'Tourinne'),
       (829, 1, 'Liège', '4280', 'Abolens'),
       (830, 1, 'Liège', '4280', 'Avernas-Le-Bauduin'),
       (831, 1, 'Liège', '4280', 'Avin'),
       (832, 1, 'Liège', '4280', 'Bertrée'),
       (833, 1, 'Liège', '4280', 'Blehen'),
       (834, 1, 'Liège', '4280', 'Cras-Avernas'),
       (835, 1, 'Liège', '4280', 'Crehen'),
       (836, 1, 'Liège', '4280', 'Grand-Hallet'),
       (837, 1, 'Liège', '4280', 'Hannut'),
       (838, 1, 'Liège', '4280', 'Lens-Saint-Remy'),
       (839, 1, 'Liège', '4280', 'Merdorp'),
       (840, 1, 'Liège', '4280', 'Moxhe'),
       (841, 1, 'Liège', '4280', 'Petit-Hallet'),
       (842, 1, 'Liège', '4280', 'Poucet'),
       (843, 1, 'Liège', '4280', 'Thisnes'),
       (844, 1, 'Liège', '4280', 'Trognée'),
       (845, 1, 'Liège', '4280', 'Villers-Le-Peuplier'),
       (846, 1, 'Liège', '4280', 'Wansin'),
       (847, 1, 'Liège', '4287', 'Lincent'),
       (848, 1, 'Liège', '4287', 'Pellaines'),
       (849, 1, 'Liège', '4287', 'Racour'),
       (850, 1, 'Liège', '4300', 'Bettincourt'),
       (851, 1, 'Liège', '4300', 'Bleret'),
       (852, 1, 'Liège', '4300', 'Bovenistier'),
       (853, 1, 'Liège', '4300', 'Grand-Axhe'),
       (854, 1, 'Liège', '4300', 'Lantremange'),
       (855, 1, 'Liège', '4300', 'Oleye'),
       (856, 1, 'Liège', '4300', 'Waremme'),
       (857, 1, 'Liège', '4317', 'Aineffe'),
       (858, 1, 'Liège', '4317', 'Borlez'),
       (859, 1, 'Liège', '4317', 'Celles'),
       (860, 1, 'Liège', '4317', 'Faimes'),
       (861, 1, 'Liège', '4317', 'Les Waleffes'),
       (862, 1, 'Liège', '4317', 'Viemme'),
       (863, 1, 'Liège', '4340', 'Awans'),
       (864, 1, 'Liège', '4340', 'Fooz'),
       (865, 1, 'Liège', '4340', 'Othée'),
       (866, 1, 'Liège', '4340', 'Villers-L\'Eveque'),
(867, 1, 'Liège', '4342', 'Hognoul'),
(868, 1, 'Liège', '4347', 'Fexhe-Le-Haut-Clocher'),
(869, 1, 'Liège', '4347', 'Freloux'),
(870, 1, 'Liège', '4347', 'Noville'),
(871, 1, 'Liège', '4347', 'Roloux'),
(872, 1, 'Liège', '4347', 'Voroux-Goreux'),
(873, 1, 'Liège', '4350', 'Lamine'),
(874, 1, 'Liège', '4350', 'Momalle'),
(875, 1, 'Liège', '4350', 'Pousset'),
(876, 1, 'Liège', '4350', 'Remicourt'),
(877, 1, 'Liège', '4351', 'Hodeige'),
(878, 1, 'Liège', '4357', 'Donceel'),
(879, 1, 'Liège', '4357', 'Haneffe'),
(880, 1, 'Liège', '4357', 'Jeneffe'),
(881, 1, 'Liège', '4357', 'Limont'),
(882, 1, 'Liège', '4360', 'Bergilers'),
(883, 1, 'Liège', '4360', 'Grandville'),
(884, 1, 'Liège', '4360', 'Lens-Sur-Geer'),
(885, 1, 'Liège', '4360', 'Oreye'),
(886, 1, 'Liège', '4360', 'Otrange'),
(887, 1, 'Liège', '4367', 'Crisnée'),
(888, 1, 'Liège', '4367', 'Fize-Le-Marsal'),
(889, 1, 'Liège', '4367', 'Kemexhe'),
(890, 1, 'Liège', '4367', 'Odeur'),
(891, 1, 'Liège', '4367', 'Thys'),
(892, 1, 'Liège', '4400', 'Awirs'),
(893, 1, 'Liège', '4400', 'Chokier'),
(894, 1, 'Liège', '4400', 'Flémalle-Grande'),
(895, 1, 'Liège', '4400', 'Flémalle-Haute'),
(896, 1, 'Liège', '4400', 'Gleixhe'),
(897, 1, 'Liège', '4400', 'Ivoz-Ramet'),
(898, 1, 'Liège', '4400', 'Mons-Lez-Licge'),
(899, 1, 'Liège', '4420', 'Montegnée'),
(900, 1, 'Liège', '4420', 'Saint-Nicolas'),
(901, 1, 'Liège', '4420', 'Tilleur'),
(902, 1, 'Liège', '4430', 'Ans'),
(903, 1, 'Liège', '4431', 'Loncin'),
(904, 1, 'Liège', '4432', 'Alleur'),
(905, 1, 'Liège', '4432', 'Xhendremael'),
(906, 1, 'Liège', '4450', 'Juprelle'),
(907, 1, 'Liège', '4450', 'Lantin'),
(908, 1, 'Liège', '4450', 'Slins'),
(909, 1, 'Liège', '4451', 'Voroux-Lez-Liers'),
(910, 1, 'Liège', '4452', 'Paifve'),
(911, 1, 'Liège', '4452', 'Wihogne'),
(912, 1, 'Liège', '4453', 'Villers-Saint-Siméon'),
(913, 1, 'Liège', '4458', 'Fexhe-Slins'),
(914, 1, 'Liège', '4460', 'Bierset'),
(915, 1, 'Liège', '4460', 'Grâce-Berleur'),
(916, 1, 'Liège', '4460', 'Grâce-Hollogne'),
(917, 1, 'Liège', '4460', 'Hollogne-Aux-Pierres'),
(918, 1, 'Liège', '4460', 'Horion-Hozémont'),
(919, 1, 'Liège', '4460', 'Velroux'),
(920, 1, 'Liège', '4470', 'Saint-Georges-Sur-Meuse'),
(921, 1, 'Liège', '4480', 'Clermont-Sous-Huy'),
(922, 1, 'Liège', '4480', 'Ehein'),
(923, 1, 'Liège', '4480', 'Engis'),
(924, 1, 'Liège', '4480', 'Hermalle-Sous-Huy'),
(925, 1, 'Liège', '4500', 'Ben-Ahin'),
(926, 1, 'Liège', '4500', 'Huy'),
(927, 1, 'Liège', '4500', 'Tihange'),
(928, 1, 'Liège', '4520', 'Antheit'),
(929, 1, 'Liège', '4520', 'Bas-Oha'),
(930, 1, 'Liège', '4520', 'Huccorgne'),
(931, 1, 'Liège', '4520', 'Moha'),
(932, 1, 'Liège', '4520', 'Vinalmont'),
(933, 1, 'Liège', '4520', 'Wanze'),
(934, 1, 'Liège', '4530', 'Fize-Fontaine'),
(935, 1, 'Liège', '4530', 'Vaux-Et-Borset'),
(936, 1, 'Liège', '4530', 'Vieux-Waleffe'),
(937, 1, 'Liège', '4530', 'Villers-Le-Bouillet'),
(938, 1, 'Liège', '4530', 'Warnant-Dreye'),
(939, 1, 'Liège', '4537', 'Bodegnée'),
(940, 1, 'Liège', '4537', 'Chapon-Seraing'),
(941, 1, 'Liège', '4537', 'Seraing-Le-Château'),
(942, 1, 'Liège', '4537', 'Verlaine'),
(943, 1, 'Liège', '4540', 'Amay'),
(944, 1, 'Liège', '4540', 'Ampsin'),
(945, 1, 'Liège', '4540', 'Flône'),
(946, 1, 'Liège', '4540', 'Jehay'),
(947, 1, 'Liège', '4540', 'Ombret'),
(948, 1, 'Liège', '4550', 'Nandrin'),
(949, 1, 'Liège', '4550', 'Saint-Séverin'),
(950, 1, 'Liège', '4550', 'Villers-Le-Temple'),
(951, 1, 'Liège', '4550', 'Yernée-Fraineux'),
(952, 1, 'Liège', '4557', 'Abée'),
(953, 1, 'Liège', '4557', 'Fraiture'),
(954, 1, 'Liège', '4557', 'Ramelot'),
(955, 1, 'Liège', '4557', 'Seny'),
(956, 1, 'Liège', '4557', 'Soheit-Tinlot'),
(957, 1, 'Liège', '4557', 'Tinlot'),
(958, 1, 'Liège', '4560', 'Bois-Et-Borsu'),
(959, 1, 'Liège', '4560', 'Clavier'),
(960, 1, 'Liège', '4560', 'Les Avins'),
(961, 1, 'Liège', '4560', 'Ocquier'),
(962, 1, 'Liège', '4560', 'Pailhe'),
(963, 1, 'Liège', '4560', 'Terwagne'),
(964, 1, 'Liège', '4570', 'Marchin'),
(965, 1, 'Liège', '4570', 'Vyle-Et-Tharoul'),
(966, 1, 'Liège', '4577', 'Modave'),
(967, 1, 'Liège', '4577', 'Outrelouxhe'),
(968, 1, 'Liège', '4577', 'Strée-Lez-Huy'),
(969, 1, 'Liège', '4577', 'Vierset-Barse'),
(970, 1, 'Liège', '4590', 'Ellemelle'),
(971, 1, 'Liège', '4590', 'Ouffet'),
(972, 1, 'Liège', '4590', 'Warzée'),
(973, 1, 'Liège', '4600', 'Lanaye'),
(974, 1, 'Liège', '4600', 'Lixhe'),
(975, 1, 'Liège', '4600', 'Richelle'),
(976, 1, 'Liège', '4600', 'Visé'),
(977, 1, 'Liège', '4601', 'Argenteau'),
(978, 1, 'Liège', '4602', 'Cheratte'),
(979, 1, 'Liège', '4606', 'Saint-André'),
(980, 1, 'Liège', '4607', 'Berneau'),
(981, 1, 'Liège', '4607', 'Bombaye'),
(982, 1, 'Liège', '4607', 'Dalhem'),
(983, 1, 'Liège', '4607', 'Feneur'),
(984, 1, 'Liège', '4607', 'Mortroux'),
(985, 1, 'Liège', '4608', 'Neufchâteau'),
(986, 1, 'Liège', '4608', 'Warsage'),
(987, 1, 'Liège', '4610', 'Bellaire'),
(988, 1, 'Liège', '4610', 'Beyne-Heusay'),
(989, 1, 'Liège', '4610', 'Queue-Du-Bois'),
(990, 1, 'Liège', '4620', 'Fléron'),
(991, 1, 'Liège', '4621', 'Retinne'),
(992, 1, 'Liège', '4623', 'Magnée'),
(993, 1, 'Liège', '4624', 'Romsée'),
(994, 1, 'Liège', '4630', 'Ayeneux'),
(995, 1, 'Liège', '4630', 'Micheroux'),
(996, 1, 'Liège', '4630', 'Soumagne'),
(997, 1, 'Liège', '4630', 'Tignée'),
(998, 1, 'Liège', '4631', 'Evegnée'),
(999, 1, 'Liège', '4632', 'Cérexhe-Heuseux'),
(1000, 1, 'Liège', '4633', 'Melen'),
(1001, 1, 'Liège', '4650', 'Chaineux'),
(1002, 1, 'Liège', '4650', 'Grand-Rechain'),
(1003, 1, 'Liège', '4650', 'Herve'),
(1004, 1, 'Liège', '4650', 'Julémont'),
(1005, 1, 'Liège', '4651', 'Battice'),
(1006, 1, 'Liège', '4652', 'Xhendelesse'),
(1007, 1, 'Liège', '4653', 'Bolland'),
(1008, 1, 'Liège', '4654', 'Charneux'),
(1009, 1, 'Liège', '4670', 'Blégny'),
(1010, 1, 'Liège', '4670', 'Mortier'),
(1011, 1, 'Liège', '4670', 'Trembleur'),
(1012, 1, 'Liège', '4671', 'Barchon'),
(1013, 1, 'Liège', '4671', 'Housse'),
(1014, 1, 'Liège', '4671', 'Saive'),
(1015, 1, 'Liège', '4672', 'Saint-Remy'),
(1016, 1, 'Liège', '4680', 'Hermée'),
(1017, 1, 'Liège', '4680', 'Oupeye'),
(1018, 1, 'Liège', '4681', 'Hermalle-Sous-Argenteau'),
(1019, 1, 'Liège', '4682', 'Heure-Le-Romain'),
(1020, 1, 'Liège', '4682', 'Houtain-Saint-Siméon'),
(1021, 1, 'Liège', '4683', 'Vivegnis'),
(1022, 1, 'Liège', '4684', 'Haccourt'),
(1023, 1, 'Liège', '4690', 'Bassenge'),
(1024, 1, 'Liège', '4690', 'Boirs'),
(1025, 1, 'Liège', '4690', 'Eben-Emael'),
(1026, 1, 'Liège', '4690', 'Glons'),
(1027, 1, 'Liège', '4690', 'Roclenge-Sur-Geer'),
(1028, 1, 'Liège', '4690', 'Wonck'),
(1029, 1, 'Liège', '4700', 'Eupen'),
(1030, 1, 'Liège', '4701', 'Kettenis'),
(1031, 1, 'Liège', '4710', 'Lontzen'),
(1032, 1, 'Liège', '4711', 'Walhorn'),
(1033, 1, 'Liège', '4720', 'La Calamine'),
(1034, 1, 'Liège', '4721', 'Neu-Moresnet'),
(1035, 1, 'Liège', '4728', 'Hergenrath'),
(1036, 1, 'Liège', '4730', 'Hauset'),
(1037, 1, 'Liège', '4730', 'Raeren'),
(1038, 1, 'Liège', '4731', 'Eynatten'),
(1039, 1, 'Liège', '4750', 'Butgenbach'),
(1040, 1, 'Liège', '4750', 'Elsenborn'),
(1041, 1, 'Liège', '4760', 'Bullange'),
(1042, 1, 'Liège', '4760', 'Manderfeld'),
(1043, 1, 'Liège', '4761', 'Rocherath'),
(1044, 1, 'Liège', '4770', 'Amblcve'),
(1045, 1, 'Liège', '4770', 'Meyerode'),
(1046, 1, 'Liège', '4771', 'Heppenbach'),
(1047, 1, 'Liège', '4780', 'Recht'),
(1048, 1, 'Liège', '4780', 'Saint-Vith'),
(1049, 1, 'Liège', '4782', 'Schoenberg'),
(1050, 1, 'Liège', '4783', 'Lommersweiler'),
(1051, 1, 'Liège', '4784', 'Crombach'),
(1052, 1, 'Liège', '4790', 'Reuland'),
(1053, 1, 'Liège', '4791', 'Thommen'),
(1054, 1, 'Liège', '4800', 'Ensival'),
(1055, 1, 'Liège', '4800', 'Lambermont'),
(1056, 1, 'Liège', '4800', 'Petit-Rechain'),
(1057, 1, 'Liège', '4800', 'Polleur'),
(1058, 1, 'Liège', '4800', 'Verviers'),
(1059, 1, 'Liège', '4801', 'Stembert'),
(1060, 1, 'Liège', '4802', 'Heusy'),
(1061, 1, 'Liège', '4820', 'Dison'),
(1062, 1, 'Liège', '4821', 'Andrimont'),
(1063, 1, 'Liège', '4830', 'Limbourg'),
(1064, 1, 'Liège', '4831', 'Bilstain'),
(1065, 1, 'Liège', '4834', 'Goé'),
(1066, 1, 'Liège', '4837', 'Baelen'),
(1067, 1, 'Liège', '4837', 'Membach'),
(1068, 1, 'Liège', '4840', 'Welkenraedt'),
(1069, 1, 'Liège', '4841', 'Henri-Chapelle'),
(1070, 1, 'Liège', '4845', 'Jalhay'),
(1071, 1, 'Liège', '4845', 'Sart-Lez-Spa'),
(1072, 1, 'Liège', '4850', 'Montzen'),
(1073, 1, 'Liège', '4850', 'Moresnet'),
(1074, 1, 'Liège', '4851', 'Gemmenich'),
(1075, 1, 'Liège', '4851', 'Sippenaeken'),
(1076, 1, 'Liège', '4852', 'Hombourg'),
(1077, 1, 'Liège', '4860', 'Cornesse'),
(1078, 1, 'Liège', '4860', 'Pepinster'),
(1079, 1, 'Liège', '4860', 'Wegnez'),
(1080, 1, 'Liège', '4861', 'Soiron'),
(1081, 1, 'Liège', '4870', 'Foret'),
(1082, 1, 'Liège', '4870', 'Fraipont'),
(1083, 1, 'Liège', '4870', 'Nessonvaux'),
(1084, 1, 'Liège', '4877', 'Olne'),
(1085, 1, 'Liège', '4880', 'Aubel'),
(1086, 1, 'Liège', '4890', 'Clermont'),
(1087, 1, 'Liège', '4890', 'Thimister'),
(1088, 1, 'Liège', '4900', 'Spa'),
(1089, 1, 'Liège', '4910', 'La Reid'),
(1090, 1, 'Liège', '4910', 'Polleur'),
(1091, 1, 'Liège', '4910', 'Theux'),
(1092, 1, 'Liège', '4920', 'Aywaille'),
(1093, 1, 'Liège', '4920', 'Ernonheid'),
(1094, 1, 'Liège', '4920', 'Harzé'),
(1095, 1, 'Liège', '4920', 'Louveigné'),
(1096, 1, 'Liège', '4920', 'Sougné-Remouchamps'),
(1097, 1, 'Liège', '4950', 'Faymonville'),
(1098, 1, 'Liège', '4950', 'Robertville'),
(1099, 1, 'Liège', '4950', 'Sourbrodt'),
(1100, 1, 'Liège', '4950', 'Waimes'),
(1101, 1, 'Liège', '4960', 'Bellevaux-Ligneuville'),
(1102, 1, 'Liège', '4960', 'Bevercé'),
(1103, 1, 'Liège', '4960', 'Malmedy'),
(1104, 1, 'Liège', '4970', 'Francorchamps'),
(1105, 1, 'Liège', '4970', 'Stavelot'),
(1106, 1, 'Liège', '4980', 'Fosse'),
(1107, 1, 'Liège', '4980', 'Trois-Ponts'),
(1108, 1, 'Liège', '4980', 'Wanne'),
(1109, 1, 'Liège', '4983', 'Basse-Bodeux'),
(1110, 1, 'Liège', '4987', 'Chevron'),
(1111, 1, 'Liège', '4987', 'La Gleize'),
(1112, 1, 'Liège', '4987', 'Lorcé'),
(1113, 1, 'Liège', '4987', 'Rahier'),
(1114, 1, 'Liège', '4987', 'Stoumont'),
(1115, 1, 'Liège', '4990', 'Arbrefontaine'),
(1116, 1, 'Liège', '4990', 'Bra'),
(1117, 1, 'Liège', '4990', 'Lierneux'),
(1118, 1, 'Namur', '5000', 'Beez'),
(1119, 1, 'Namur', '5000', 'Namur'),
(1120, 1, 'Namur', '5001', 'Belgrade'),
(1121, 1, 'Namur', '5002', 'Saint-Servais'),
(1122, 1, 'Namur', '5003', 'Saint-Marc'),
(1123, 1, 'Namur', '5004', 'Bouge'),
(1124, 1, 'Namur', '5010', 'SA SudPresse'),
(1125, 1, 'Namur', '5012', 'Parlement Wallon'),
(1126, 1, 'Namur', '5020', 'Champion'),
(1127, 1, 'Namur', '5020', 'Daussoulx'),
(1128, 1, 'Namur', '5020', 'Flawinne'),
(1129, 1, 'Namur', '5020', 'Malonne'),
(1130, 1, 'Namur', '5020', 'Suarlée'),
(1131, 1, 'Namur', '5020', 'Temploux'),
(1132, 1, 'Namur', '5020', 'Vedrin'),
(1133, 1, 'Namur', '5021', 'Boninne'),
(1134, 1, 'Namur', '5022', 'Cognelée'),
(1135, 1, 'Namur', '5024', 'Gelbressée'),
(1136, 1, 'Namur', '5024', 'Marche-Les-Dames'),
(1137, 1, 'Namur', '5030', 'Beuzet'),
(1138, 1, 'Namur', '5030', 'Ernage'),
(1139, 1, 'Namur', '5030', 'Gembloux'),
(1140, 1, 'Namur', '5030', 'Grand-Manil'),
(1141, 1, 'Namur', '5030', 'Lonzée'),
(1142, 1, 'Namur', '5030', 'Sauvenicre'),
(1143, 1, 'Namur', '5031', 'Grand-Leez'),
(1144, 1, 'Namur', '5032', 'Bossicre'),
(1145, 1, 'Namur', '5032', 'Bothey'),
(1146, 1, 'Namur', '5032', 'Corroy-Le-Château'),
(1147, 1, 'Namur', '5032', 'Isnes'),
(1148, 1, 'Namur', '5032', 'Mazy'),
(1149, 1, 'Namur', '5060', 'Arsimont'),
(1150, 1, 'Namur', '5060', 'Auvelais'),
(1151, 1, 'Namur', '5060', 'Falisolle'),
(1152, 1, 'Namur', '5060', 'Keumiée'),
(1153, 1, 'Namur', '5060', 'Moignelée'),
(1154, 1, 'Namur', '5060', 'Tamines'),
(1155, 1, 'Namur', '5060', 'Velaine-Sur-Sambre'),
(1156, 1, 'Namur', '5070', 'Aisemont'),
(1157, 1, 'Namur', '5070', 'Fosses-La-Ville'),
(1158, 1, 'Namur', '5070', 'Le Roux'),
(1159, 1, 'Namur', '5070', 'Sart-Eustache'),
(1160, 1, 'Namur', '5070', 'Sart-Saint-Laurent'),
(1161, 1, 'Namur', '5070', 'Vitrival'),
(1162, 1, 'Namur', '5080', 'Emines'),
(1163, 1, 'Namur', '5080', 'Rhisnes'),
(1164, 1, 'Namur', '5080', 'Villers-Lez-Heest'),
(1165, 1, 'Namur', '5080', 'Warisoulx'),
(1166, 1, 'Namur', '5081', 'Bovesse'),
(1167, 1, 'Namur', '5081', 'Meux'),
(1168, 1, 'Namur', '5081', 'Saint-Denis-Bovesse'),
(1169, 1, 'Namur', '5100', 'Dave'),
(1170, 1, 'Namur', '5100', 'Jambes'),
(1171, 1, 'Namur', '5100', 'Naninne'),
(1172, 1, 'Namur', '5100', 'Wépion'),
(1173, 1, 'Namur', '5100', 'Wierde'),
(1174, 1, 'Namur', '5101', 'Erpent'),
(1175, 1, 'Namur', '5101', 'Lives-Sur-Meuse'),
(1176, 1, 'Namur', '5101', 'Loyers'),
(1177, 1, 'Namur', '5140', 'Boignée'),
(1178, 1, 'Namur', '5140', 'Ligny'),
(1179, 1, 'Namur', '5140', 'Sombreffe'),
(1180, 1, 'Namur', '5140', 'Tongrinne'),
(1181, 1, 'Namur', '5150', 'Floreffe'),
(1182, 1, 'Namur', '5150', 'Floriffoux'),
(1183, 1, 'Namur', '5150', 'Franicre'),
(1184, 1, 'Namur', '5150', 'Soye'),
(1185, 1, 'Namur', '5170', 'Arbre'),
(1186, 1, 'Namur', '5170', 'Bois-De-Villers'),
(1187, 1, 'Namur', '5170', 'Lesve'),
(1188, 1, 'Namur', '5170', 'Lustin'),
(1189, 1, 'Namur', '5170', 'Profondeville'),
(1190, 1, 'Namur', '5170', 'Rivicre'),
(1191, 1, 'Namur', '5190', 'Balâtre'),
(1192, 1, 'Namur', '5190', 'Ham-Sur-Sambre');
INSERT INTO `cities` (`ID`, `ID_Countries`, `Region`, `Postal_Code`, `Label`) VALUES
(1193, 1, 'Namur', '5190', 'Jemeppe-Sur-Sambre'),
(1194, 1, 'Namur', '5190', 'Mornimont'),
(1195, 1, 'Namur', '5190', 'Moustier-Sur-Sambre'),
(1196, 1, 'Namur', '5190', 'Onoz'),
(1197, 1, 'Namur', '5190', 'Saint-Martin'),
(1198, 1, 'Namur', '5190', 'Spy'),
(1199, 1, 'Namur', '5300', 'Andenne'),
(1200, 1, 'Namur', '5300', 'Bonneville'),
(1201, 1, 'Namur', '5300', 'Coutisse'),
(1202, 1, 'Namur', '5300', 'Landenne'),
(1203, 1, 'Namur', '5300', 'Maizeret'),
(1204, 1, 'Namur', '5300', 'Nameche'),
(1205, 1, 'Namur', '5300', 'Sclayn'),
(1206, 1, 'Namur', '5300', 'Seilles'),
(1207, 1, 'Namur', '5300', 'Thon'),
(1208, 1, 'Namur', '5300', 'Vezin'),
(1209, 1, 'Namur', '5310', 'Aische-En-Refail'),
(1210, 1, 'Namur', '5310', 'Bolinne'),
(1211, 1, 'Namur', '5310', 'Boneffe'),
(1212, 1, 'Namur', '5310', 'Branchon'),
(1213, 1, 'Namur', '5310', 'Dhuy'),
(1214, 1, 'Namur', '5310', 'Eghezée'),
(1215, 1, 'Namur', '5310', 'Hanret'),
(1216, 1, 'Namur', '5310', 'Leuze'),
(1217, 1, 'Namur', '5310', 'Liernu'),
(1218, 1, 'Namur', '5310', 'Longchamps'),
(1219, 1, 'Namur', '5310', 'Mehaigne'),
(1220, 1, 'Namur', '5310', 'Noville-Sur-Mehaigne'),
(1221, 1, 'Namur', '5310', 'Saint-Germain'),
(1222, 1, 'Namur', '5310', 'Taviers'),
(1223, 1, 'Namur', '5310', 'Upigny'),
(1224, 1, 'Namur', '5310', 'Waret-La-Chaussée'),
(1225, 1, 'Namur', '5330', 'Assesse'),
(1226, 1, 'Namur', '5330', 'Maillen'),
(1227, 1, 'Namur', '5330', 'Sart-Bernard'),
(1228, 1, 'Namur', '5332', 'Crupet'),
(1229, 1, 'Namur', '5333', 'Sorinne-La-Longue'),
(1230, 1, 'Namur', '5334', 'Florée'),
(1231, 1, 'Namur', '5336', 'Courricre'),
(1232, 1, 'Namur', '5340', 'Faulx-Les-Tombes'),
(1233, 1, 'Namur', '5340', 'Gesves'),
(1234, 1, 'Namur', '5340', 'Haltinne'),
(1235, 1, 'Namur', '5340', 'Mozet'),
(1236, 1, 'Namur', '5340', 'Sorée'),
(1237, 1, 'Namur', '5350', 'Evelette'),
(1238, 1, 'Namur', '5350', 'Ohey'),
(1239, 1, 'Namur', '5351', 'Haillot'),
(1240, 1, 'Namur', '5352', 'Perwez-Haillot'),
(1241, 1, 'Namur', '5353', 'Goesnes'),
(1242, 1, 'Namur', '5354', 'Jallet'),
(1243, 1, 'Namur', '5360', 'Hamois'),
(1244, 1, 'Namur', '5360', 'Natoye'),
(1245, 1, 'Namur', '5361', 'Mohiville'),
(1246, 1, 'Namur', '5361', 'Scy'),
(1247, 1, 'Namur', '5362', 'Achet'),
(1248, 1, 'Namur', '5363', 'Emptinne'),
(1249, 1, 'Namur', '5364', 'Schaltin'),
(1250, 1, 'Namur', '5370', 'Barvaux-Condroz'),
(1251, 1, 'Namur', '5370', 'Flostoy'),
(1252, 1, 'Namur', '5370', 'Havelange'),
(1253, 1, 'Namur', '5370', 'Jeneffe'),
(1254, 1, 'Namur', '5370', 'Porcheresse'),
(1255, 1, 'Namur', '5370', 'Verlée'),
(1256, 1, 'Namur', '5372', 'Méan'),
(1257, 1, 'Namur', '5374', 'Maffe'),
(1258, 1, 'Namur', '5376', 'Miécret'),
(1259, 1, 'Namur', '5377', 'Baillonville'),
(1260, 1, 'Namur', '5377', 'Bonsin'),
(1261, 1, 'Namur', '5377', 'Heure'),
(1262, 1, 'Namur', '5377', 'Hogne'),
(1263, 1, 'Namur', '5377', 'Nettinne'),
(1264, 1, 'Namur', '5377', 'Noiseux'),
(1265, 1, 'Namur', '5377', 'Sinsin'),
(1266, 1, 'Namur', '5377', 'Somme-Leuze'),
(1267, 1, 'Namur', '5377', 'Waillet'),
(1268, 1, 'Namur', '5380', 'Bierwart'),
(1269, 1, 'Namur', '5380', 'Cortil-Wodon'),
(1270, 1, 'Namur', '5380', 'Forville'),
(1271, 1, 'Namur', '5380', 'Franc-Waret'),
(1272, 1, 'Namur', '5380', 'Hemptinne'),
(1273, 1, 'Namur', '5380', 'Hingeon'),
(1274, 1, 'Namur', '5380', 'Marchovelette'),
(1275, 1, 'Namur', '5380', 'Noville-Les-Bois'),
(1276, 1, 'Namur', '5380', 'Pontillas'),
(1277, 1, 'Namur', '5380', 'Tillier'),
(1278, 1, 'Namur', '5500', 'Anseremme'),
(1279, 1, 'Namur', '5500', 'Bouvignes-Sur-Meuse'),
(1280, 1, 'Namur', '5500', 'Dinant'),
(1281, 1, 'Namur', '5500', 'Dréhance'),
(1282, 1, 'Namur', '5500', 'Falmagne'),
(1283, 1, 'Namur', '5500', 'Falmignoul'),
(1284, 1, 'Namur', '5500', 'Furfooz'),
(1285, 1, 'Namur', '5501', 'Lisogne'),
(1286, 1, 'Namur', '5502', 'Thynes'),
(1287, 1, 'Namur', '5503', 'Sorinnes'),
(1288, 1, 'Namur', '5504', 'Foy-Notre-Dame'),
(1289, 1, 'Namur', '5520', 'Anthée'),
(1290, 1, 'Namur', '5520', 'Onhaye'),
(1291, 1, 'Namur', '5521', 'Serville'),
(1292, 1, 'Namur', '5522', 'Falaën'),
(1293, 1, 'Namur', '5523', 'Sommicre'),
(1294, 1, 'Namur', '5523', 'Weillen'),
(1295, 1, 'Namur', '5524', 'Gérin'),
(1296, 1, 'Namur', '5530', 'Dorinne'),
(1297, 1, 'Namur', '5530', 'Durnal'),
(1298, 1, 'Namur', '5530', 'Evrehailles'),
(1299, 1, 'Namur', '5530', 'Godinne'),
(1300, 1, 'Namur', '5530', 'Houx'),
(1301, 1, 'Namur', '5530', 'Mont'),
(1302, 1, 'Namur', '5530', 'Purnode'),
(1303, 1, 'Namur', '5530', 'Spontin'),
(1304, 1, 'Namur', '5530', 'Yvoir'),
(1305, 1, 'Namur', '5537', 'Anhée'),
(1306, 1, 'Namur', '5537', 'Annevoie-Rouillon'),
(1307, 1, 'Namur', '5537', 'Bioul'),
(1308, 1, 'Namur', '5537', 'Denée'),
(1309, 1, 'Namur', '5537', 'Haut-Le-Wastia'),
(1310, 1, 'Namur', '5537', 'Sosoye'),
(1311, 1, 'Namur', '5537', 'Warnant'),
(1312, 1, 'Namur', '5540', 'Hasticre-Lavaux'),
(1313, 1, 'Namur', '5540', 'Hermeton-Sur-Meuse'),
(1314, 1, 'Namur', '5540', 'Waulsort'),
(1315, 1, 'Namur', '5541', 'Hasticre-Par-Delr'),
(1316, 1, 'Namur', '5542', 'Blaimont'),
(1317, 1, 'Namur', '5543', 'Heer'),
(1318, 1, 'Namur', '5544', 'Agimont'),
(1319, 1, 'Namur', '5550', 'Alle'),
(1320, 1, 'Namur', '5550', 'Bagimont'),
(1321, 1, 'Namur', '5550', 'Bohan'),
(1322, 1, 'Namur', '5550', 'Chairicre'),
(1323, 1, 'Namur', '5550', 'Laforet'),
(1324, 1, 'Namur', '5550', 'Membre'),
(1325, 1, 'Namur', '5550', 'Mouzaive'),
(1326, 1, 'Namur', '5550', 'Nafraiture'),
(1327, 1, 'Namur', '5550', 'Orchimont'),
(1328, 1, 'Namur', '5550', 'Pussemange'),
(1329, 1, 'Namur', '5550', 'Sugny'),
(1330, 1, 'Namur', '5550', 'Vresse-Sur-Semois'),
(1331, 1, 'Namur', '5555', 'Baillamont'),
(1332, 1, 'Namur', '5555', 'Bellefontaine'),
(1333, 1, 'Namur', '5555', 'Bicvre'),
(1334, 1, 'Namur', '5555', 'Cornimont'),
(1335, 1, 'Namur', '5555', 'Graide'),
(1336, 1, 'Namur', '5555', 'Gros-Fays'),
(1337, 1, 'Namur', '5555', 'Monceau-En-Ardenne'),
(1338, 1, 'Namur', '5555', 'Naomé'),
(1339, 1, 'Namur', '5555', 'Oizy'),
(1340, 1, 'Namur', '5555', 'Petit-Fays'),
(1341, 1, 'Namur', '5560', 'Ciergnon'),
(1342, 1, 'Namur', '5560', 'Finnevaux'),
(1343, 1, 'Namur', '5560', 'Houyet'),
(1344, 1, 'Namur', '5560', 'Hulsonniaux'),
(1345, 1, 'Namur', '5560', 'Mesnil-Eglise'),
(1346, 1, 'Namur', '5560', 'Mesnil-Saint-Blaise'),
(1347, 1, 'Namur', '5561', 'Celles'),
(1348, 1, 'Namur', '5562', 'Custinne'),
(1349, 1, 'Namur', '5563', 'Hour'),
(1350, 1, 'Namur', '5564', 'Wanlin'),
(1351, 1, 'Namur', '5570', 'Baronville'),
(1352, 1, 'Namur', '5570', 'Beauraing'),
(1353, 1, 'Namur', '5570', 'Dion'),
(1354, 1, 'Namur', '5570', 'Felenne'),
(1355, 1, 'Namur', '5570', 'Feschaux'),
(1356, 1, 'Namur', '5570', 'Honnay'),
(1357, 1, 'Namur', '5570', 'Javingue'),
(1358, 1, 'Namur', '5570', 'Voneche'),
(1359, 1, 'Namur', '5570', 'Wancennes'),
(1360, 1, 'Namur', '5570', 'Winenne'),
(1361, 1, 'Namur', '5571', 'Wiesme'),
(1362, 1, 'Namur', '5572', 'Focant'),
(1363, 1, 'Namur', '5573', 'Martouzin-Neuville'),
(1364, 1, 'Namur', '5574', 'Pondrôme'),
(1365, 1, 'Namur', '5575', 'Bourseigne-Neuve'),
(1366, 1, 'Namur', '5575', 'Bourseigne-Vieille'),
(1367, 1, 'Namur', '5575', 'Gedinne'),
(1368, 1, 'Namur', '5575', 'Houdremont'),
(1369, 1, 'Namur', '5575', 'Louette-Saint-Denis'),
(1370, 1, 'Namur', '5575', 'Louette-Saint-Pierre'),
(1371, 1, 'Namur', '5575', 'Malvoisin'),
(1372, 1, 'Namur', '5575', 'Patignies'),
(1373, 1, 'Namur', '5575', 'Rienne'),
(1374, 1, 'Namur', '5575', 'Sart-Custinne'),
(1375, 1, 'Namur', '5575', 'Vencimont'),
(1376, 1, 'Namur', '5575', 'Willerzie'),
(1377, 1, 'Namur', '5576', 'Froidfontaine'),
(1378, 1, 'Namur', '5580', 'Ave-Et-Auffe'),
(1379, 1, 'Namur', '5580', 'Buissonville'),
(1380, 1, 'Namur', '5580', 'Eprave'),
(1381, 1, 'Namur', '5580', 'Han-Sur-Lesse'),
(1382, 1, 'Namur', '5580', 'Jemelle'),
(1383, 1, 'Namur', '5580', 'Lavaux-Sainte-Anne'),
(1384, 1, 'Namur', '5580', 'Lessive'),
(1385, 1, 'Namur', '5580', 'Mont-Gauthier'),
(1386, 1, 'Namur', '5580', 'Rochefort'),
(1387, 1, 'Namur', '5580', 'Villers-Sur-Lesse'),
(1388, 1, 'Namur', '5580', 'Wavreille'),
(1389, 1, 'Namur', '5589', 'Jemelle'),
(1390, 1, 'Namur', '5590', 'Achene'),
(1391, 1, 'Namur', '5590', 'Braibant'),
(1392, 1, 'Namur', '5590', 'Chevetogne'),
(1393, 1, 'Namur', '5590', 'Ciney'),
(1394, 1, 'Namur', '5590', 'Conneux'),
(1395, 1, 'Namur', '5590', 'Haversin'),
(1396, 1, 'Namur', '5590', 'Leignon'),
(1397, 1, 'Namur', '5590', 'Pessoux'),
(1398, 1, 'Namur', '5590', 'Serinchamps'),
(1399, 1, 'Namur', '5590', 'Sovet'),
(1400, 1, 'Namur', '5600', 'Fagnolle'),
(1401, 1, 'Namur', '5600', 'Franchimont'),
(1402, 1, 'Namur', '5600', 'Jamagne'),
(1403, 1, 'Namur', '5600', 'Jamiolle'),
(1404, 1, 'Namur', '5600', 'Merlemont'),
(1405, 1, 'Namur', '5600', 'Neuville'),
(1406, 1, 'Namur', '5600', 'Omezée'),
(1407, 1, 'Namur', '5600', 'Philippeville'),
(1408, 1, 'Namur', '5600', 'Roly'),
(1409, 1, 'Namur', '5600', 'Romedenne'),
(1410, 1, 'Namur', '5600', 'Samart'),
(1411, 1, 'Namur', '5600', 'Sart-En-Fagne'),
(1412, 1, 'Namur', '5600', 'Sautour'),
(1413, 1, 'Namur', '5600', 'Surice'),
(1414, 1, 'Namur', '5600', 'Villers-En-Fagne'),
(1415, 1, 'Namur', '5600', 'Villers-Le-Gambon'),
(1416, 1, 'Namur', '5600', 'Vodecée'),
(1417, 1, 'Namur', '5620', 'Corenne'),
(1418, 1, 'Namur', '5620', 'Flavion'),
(1419, 1, 'Namur', '5620', 'Florennes'),
(1420, 1, 'Namur', '5620', 'Hemptinne-Lez-Florennes'),
(1421, 1, 'Namur', '5620', 'Morville'),
(1422, 1, 'Namur', '5620', 'Rosée'),
(1423, 1, 'Namur', '5620', 'Saint-Aubin'),
(1424, 1, 'Namur', '5621', 'Hanzinelle'),
(1425, 1, 'Namur', '5621', 'Hanzinne'),
(1426, 1, 'Namur', '5621', 'Morialmé'),
(1427, 1, 'Namur', '5621', 'Thy-Le-Baudouin'),
(1428, 1, 'Namur', '5630', 'Cerfontaine'),
(1429, 1, 'Namur', '5630', 'Daussois'),
(1430, 1, 'Namur', '5630', 'Senzeilles'),
(1431, 1, 'Namur', '5630', 'Silenrieux'),
(1432, 1, 'Namur', '5630', 'Soumoy'),
(1433, 1, 'Namur', '5630', 'Villers-Deux-Eglises'),
(1434, 1, 'Namur', '5640', 'Biesme'),
(1435, 1, 'Namur', '5640', 'Biesmerée'),
(1436, 1, 'Namur', '5640', 'Graux'),
(1437, 1, 'Namur', '5640', 'Mettet'),
(1438, 1, 'Namur', '5640', 'Oret'),
(1439, 1, 'Namur', '5640', 'Saint-Gérard'),
(1440, 1, 'Namur', '5641', 'Furnaux'),
(1441, 1, 'Namur', '5644', 'Ermeton-Sur-Biert'),
(1442, 1, 'Namur', '5646', 'Stave'),
(1443, 1, 'Namur', '5650', 'Castillon'),
(1444, 1, 'Namur', '5650', 'Chastrcs'),
(1445, 1, 'Namur', '5650', 'Clermont'),
(1446, 1, 'Namur', '5650', 'Fontenelle'),
(1447, 1, 'Namur', '5650', 'Fraire'),
(1448, 1, 'Namur', '5650', 'Pry'),
(1449, 1, 'Namur', '5650', 'Vogenée'),
(1450, 1, 'Namur', '5650', 'Walcourt'),
(1451, 1, 'Namur', '5650', 'Yves-Gomezée'),
(1452, 1, 'Namur', '5651', 'Berzée'),
(1453, 1, 'Namur', '5651', 'Gourdinne'),
(1454, 1, 'Namur', '5651', 'Laneffe'),
(1455, 1, 'Namur', '5651', 'Rognée'),
(1456, 1, 'Namur', '5651', 'Somzée'),
(1457, 1, 'Namur', '5651', 'Tarcienne'),
(1458, 1, 'Namur', '5651', 'Thy-Le-Château'),
(1459, 1, 'Namur', '5660', 'Aublain'),
(1460, 1, 'Namur', '5660', 'Boussu-En-Fagne'),
(1461, 1, 'Namur', '5660', 'Bruly'),
(1462, 1, 'Namur', '5660', 'Bruly-De-Pesche'),
(1463, 1, 'Namur', '5660', 'Couvin'),
(1464, 1, 'Namur', '5660', 'Cul-Des-Sarts'),
(1465, 1, 'Namur', '5660', 'Dailly'),
(1466, 1, 'Namur', '5660', 'Frasnes'),
(1467, 1, 'Namur', '5660', 'Gonrieux'),
(1468, 1, 'Namur', '5660', 'Mariembourg'),
(1469, 1, 'Namur', '5660', 'Pesche'),
(1470, 1, 'Namur', '5660', 'Pétigny'),
(1471, 1, 'Namur', '5660', 'Petite-Chapelle'),
(1472, 1, 'Namur', '5660', 'Presgaux'),
(1473, 1, 'Namur', '5670', 'Dourbes'),
(1474, 1, 'Namur', '5670', 'Le Mesnil'),
(1475, 1, 'Namur', '5670', 'Mazée'),
(1476, 1, 'Namur', '5670', 'Nismes'),
(1477, 1, 'Namur', '5670', 'Oignies-En-Thiérache'),
(1478, 1, 'Namur', '5670', 'Olloy-Sur-Viroin'),
(1479, 1, 'Namur', '5670', 'Treignes'),
(1480, 1, 'Namur', '5670', 'Vierves-Sur-Viroin'),
(1481, 1, 'Namur', '5680', 'Doische'),
(1482, 1, 'Namur', '5680', 'Gimnée'),
(1483, 1, 'Namur', '5680', 'Gochenée'),
(1484, 1, 'Namur', '5680', 'Matagne-La-Grande'),
(1485, 1, 'Namur', '5680', 'Matagne-La-Petite'),
(1486, 1, 'Namur', '5680', 'Niverlée'),
(1487, 1, 'Namur', '5680', 'Romerée'),
(1488, 1, 'Namur', '5680', 'Soulme'),
(1489, 1, 'Namur', '5680', 'Vaucelles'),
(1490, 1, 'Namur', '5680', 'Vodelée'),
(1491, 1, 'Hainaut', '6000', 'Charleroi'),
(1492, 1, 'Hainaut', '6001', 'Marcinelle'),
(1493, 1, 'Hainaut', '6010', 'Couillet'),
(1494, 1, 'Hainaut', '6020', 'Dampremy'),
(1495, 1, 'Hainaut', '6030', 'Goutroux'),
(1496, 1, 'Hainaut', '6030', 'Marchienne-Au-Pont'),
(1497, 1, 'Hainaut', '6031', 'Monceau-Sur-Sambre'),
(1498, 1, 'Hainaut', '6032', 'Mont-Sur-Marchienne'),
(1499, 1, 'Hainaut', '6040', 'Jumet'),
(1500, 1, 'Hainaut', '6041', 'Gosselies'),
(1501, 1, 'Hainaut', '6042', 'Lodelinsart'),
(1502, 1, 'Hainaut', '6043', 'Ransart'),
(1503, 1, 'Hainaut', '6044', 'Roux'),
(1504, 1, 'Hainaut', '6060', 'Gilly'),
(1505, 1, 'Hainaut', '6061', 'Montignies-Sur-Sambre'),
(1506, 1, 'Hainaut', '6075', 'CSM Charleroi X'),
(1507, 1, 'Hainaut', '6099', 'Charleroi X'),
(1508, 1, 'Hainaut', '6110', 'Montigny-Le-Tilleul'),
(1509, 1, 'Hainaut', '6111', 'Landelies'),
(1510, 1, 'Hainaut', '6120', 'Cour-Sur-Heure'),
(1511, 1, 'Hainaut', '6120', 'Ham-Sur-Heure'),
(1512, 1, 'Hainaut', '6120', 'Jamioulx'),
(1513, 1, 'Hainaut', '6120', 'Marbaix'),
(1514, 1, 'Hainaut', '6120', 'Nalinnes'),
(1515, 1, 'Hainaut', '6140', 'Fontaine-L\'Eveque'),
       (1516, 1, 'Hainaut', '6141', 'Forchies-La-Marche'),
       (1517, 1, 'Hainaut', '6142', 'Leernes'),
       (1518, 1, 'Hainaut', '6150', 'Anderlues'),
       (1519, 1, 'Hainaut', '6180', 'Courcelles'),
       (1520, 1, 'Hainaut', '6181', 'Gouy-Lez-Piéton'),
       (1521, 1, 'Hainaut', '6182', 'Souvret'),
       (1522, 1, 'Hainaut', '6183', 'Trazegnies'),
       (1523, 1, 'Hainaut', '6200', 'Bouffioulx'),
       (1524, 1, 'Hainaut', '6200', 'Châtelet'),
       (1525, 1, 'Bruxelles-Capitale', '1000', 'Bruxelles'),
       (1526, 1, 'Hainaut', '6210', 'Frasnes-Lez-Gosselies'),
       (1527, 1, 'Hainaut', '6210', 'Rcves'),
       (1528, 1, 'Hainaut', '6210', 'Villers-Perwin'),
       (1529, 1, 'Hainaut', '6210', 'Wayaux'),
       (1530, 1, 'Hainaut', '6211', 'Mellet'),
       (1531, 1, 'Hainaut', '6220', 'Fleurus'),
       (1532, 1, 'Hainaut', '6220', 'Heppignies'),
       (1533, 1, 'Hainaut', '6220', 'Lambusart'),
       (1534, 1, 'Hainaut', '6220', 'Wangenies'),
       (1535, 1, 'Hainaut', '6221', 'Saint-Amand'),
       (1536, 1, 'Hainaut', '6222', 'Brye'),
       (1537, 1, 'Hainaut', '6223', 'Wagnelée'),
       (1538, 1, 'Hainaut', '6224', 'Wanfercée-Baulet'),
       (1539, 1, 'Hainaut', '6230', 'Buzet'),
       (1540, 1, 'Hainaut', '6230', 'Obaix'),
       (1541, 1, 'Hainaut', '6230', 'Pont-à-Celles'),
       (1542, 1, 'Hainaut', '6230', 'Thiméon'),
       (1543, 1, 'Hainaut', '6230', 'Viesville'),
       (1544, 1, 'Hainaut', '6238', 'Liberchies'),
       (1545, 1, 'Hainaut', '6238', 'Luttre'),
       (1546, 1, 'Hainaut', '6240', 'Farciennes'),
       (1547, 1, 'Hainaut', '6240', 'Pironchamps'),
       (1548, 1, 'Hainaut', '6250', 'Aiseau'),
       (1549, 1, 'Hainaut', '6250', 'Pont-De-Loup'),
       (1550, 1, 'Hainaut', '6250', 'Presles'),
       (1551, 1, 'Hainaut', '6250', 'Roselies'),
       (1552, 1, 'Hainaut', '6280', 'Acoz'),
       (1553, 1, 'Hainaut', '6280', 'Gerpinnes'),
       (1554, 1, 'Hainaut', '6280', 'Gougnies'),
       (1555, 1, 'Hainaut', '6280', 'Joncret'),
       (1556, 1, 'Hainaut', '6280', 'Loverval'),
       (1557, 1, 'Hainaut', '6280', 'Villers-Poterie'),
       (1558, 1, 'Hainaut', '6440', 'Boussu-Lez-Walcourt'),
       (1559, 1, 'Hainaut', '6440', 'Fourbechies'),
       (1560, 1, 'Hainaut', '6440', 'Froidchapelle'),
       (1561, 1, 'Hainaut', '6440', 'Vergnies'),
       (1562, 1, 'Hainaut', '6441', 'Erpion'),
       (1563, 1, 'Hainaut', '6460', 'Bailicvre'),
       (1564, 1, 'Hainaut', '6460', 'Chimay'),
       (1565, 1, 'Hainaut', '6460', 'Robechies'),
       (1566, 1, 'Hainaut', '6460', 'Saint-Remy'),
       (1567, 1, 'Hainaut', '6460', 'Salles'),
       (1568, 1, 'Hainaut', '6460', 'Villers-La-Tour'),
       (1569, 1, 'Hainaut', '6461', 'Virelles'),
       (1570, 1, 'Hainaut', '6462', 'Vaulx-Lez-Chimay'),
       (1571, 1, 'Hainaut', '6463', 'Lompret'),
       (1572, 1, 'Hainaut', '6464', 'Baileux'),
       (1573, 1, 'Hainaut', '6464', 'Bourlers'),
       (1574, 1, 'Hainaut', '6464', 'Forges'),
       (1575, 1, 'Hainaut', '6464', 'L\'Escaillcre'),
(1576, 1, 'Hainaut', '6464', 'Riczes'),
(1577, 1, 'Hainaut', '6470', 'Grandrieu'),
(1578, 1, 'Hainaut', '6470', 'Montbliart'),
(1579, 1, 'Hainaut', '6470', 'Rance'),
(1580, 1, 'Hainaut', '6470', 'Sautin'),
(1581, 1, 'Hainaut', '6470', 'Sivry'),
(1582, 1, 'Hainaut', '6500', 'Barbençon'),
(1583, 1, 'Hainaut', '6500', 'Beaumont'),
(1584, 1, 'Hainaut', '6500', 'Leugnies'),
(1585, 1, 'Hainaut', '6500', 'Leval-Chaudeville'),
(1586, 1, 'Hainaut', '6500', 'Renlies'),
(1587, 1, 'Hainaut', '6500', 'Solre-Saint-Géry'),
(1588, 1, 'Hainaut', '6500', 'Thirimont'),
(1589, 1, 'Hainaut', '6511', 'Strée'),
(1590, 1, 'Hainaut', '6530', 'Leers-Et-Fosteau'),
(1591, 1, 'Hainaut', '6530', 'Thuin'),
(1592, 1, 'Hainaut', '6531', 'Biesme-Sous-Thuin'),
(1593, 1, 'Hainaut', '6532', 'Ragnies'),
(1594, 1, 'Hainaut', '6533', 'Biercée'),
(1595, 1, 'Hainaut', '6534', 'Gozée'),
(1596, 1, 'Hainaut', '6536', 'Donstiennes'),
(1597, 1, 'Hainaut', '6536', 'Thuillies'),
(1598, 1, 'Hainaut', '6540', 'Lobbes'),
(1599, 1, 'Hainaut', '6540', 'Mont-Sainte-Genevicve'),
(1600, 1, 'Hainaut', '6542', 'Sars-La-Buissicre'),
(1601, 1, 'Hainaut', '6543', 'Bienne-Lez-Happart'),
(1602, 1, 'Hainaut', '6560', 'Bersillies-L\'Abbaye'),
       (1603, 1, 'Hainaut', '6560', 'Erquelinnes'),
       (1604, 1, 'Hainaut', '6560', 'Grand-Reng'),
       (1605, 1, 'Hainaut', '6560', 'Hantes-Wihéries'),
       (1606, 1, 'Hainaut', '6560', 'Montignies-Saint-Christophe'),
       (1607, 1, 'Hainaut', '6560', 'Solre-Sur-Sambre'),
       (1608, 1, 'Hainaut', '6567', 'Fontaine-Valmont'),
       (1609, 1, 'Hainaut', '6567', 'Labuissicre'),
       (1610, 1, 'Hainaut', '6567', 'Merbes-Le-Château'),
       (1611, 1, 'Hainaut', '6567', 'Merbes-Sainte-Marie'),
       (1612, 1, 'Hainaut', '6590', 'Momignies'),
       (1613, 1, 'Hainaut', '6591', 'Macon'),
       (1614, 1, 'Hainaut', '6592', 'Monceau-Imbrechies'),
       (1615, 1, 'Hainaut', '6593', 'Macquenoise'),
       (1616, 1, 'Hainaut', '6594', 'Beauwelz'),
       (1617, 1, 'Hainaut', '6596', 'Forge-Philippe'),
       (1618, 1, 'Hainaut', '6596', 'Seloignes'),
       (1619, 1, 'Luxembourg', '6600', 'Bastogne'),
       (1620, 1, 'Luxembourg', '6600', 'Longvilly'),
       (1621, 1, 'Luxembourg', '6600', 'Noville'),
       (1622, 1, 'Luxembourg', '6600', 'Villers-La-Bonne-Eau'),
       (1623, 1, 'Luxembourg', '6600', 'Wardin'),
       (1624, 1, 'Luxembourg', '6630', 'Martelange'),
       (1625, 1, 'Luxembourg', '6637', 'Fauvillers'),
       (1626, 1, 'Luxembourg', '6637', 'Hollange'),
       (1627, 1, 'Luxembourg', '6637', 'Tintange'),
       (1628, 1, 'Luxembourg', '6640', 'Hompré'),
       (1629, 1, 'Luxembourg', '6640', 'Morhet'),
       (1630, 1, 'Luxembourg', '6640', 'Nives'),
       (1631, 1, 'Luxembourg', '6640', 'Sibret'),
       (1632, 1, 'Luxembourg', '6640', 'Vaux-Lez-Rosicres'),
       (1633, 1, 'Luxembourg', '6640', 'Vaux-Sur-Sure'),
       (1634, 1, 'Luxembourg', '6642', 'Juseret'),
       (1635, 1, 'Luxembourg', '6660', 'Houffalize'),
       (1636, 1, 'Luxembourg', '6660', 'Nadrin'),
       (1637, 1, 'Luxembourg', '6661', 'Mont'),
       (1638, 1, 'Luxembourg', '6661', 'Tailles'),
       (1639, 1, 'Luxembourg', '6662', 'Tavigny'),
       (1640, 1, 'Luxembourg', '6663', 'Mabompré'),
       (1641, 1, 'Luxembourg', '6666', 'Wibrin'),
       (1642, 1, 'Luxembourg', '6670', 'Gouvy'),
       (1643, 1, 'Luxembourg', '6670', 'Limerlé'),
       (1644, 1, 'Luxembourg', '6671', 'Bovigny'),
       (1645, 1, 'Luxembourg', '6672', 'Beho'),
       (1646, 1, 'Luxembourg', '6673', 'Cherain'),
       (1647, 1, 'Luxembourg', '6674', 'Montleban'),
       (1648, 1, 'Luxembourg', '6680', 'Amberloup'),
       (1649, 1, 'Luxembourg', '6680', 'Tillet'),
       (1650, 1, 'Luxembourg', '6681', 'Lavacherie'),
       (1651, 1, 'Luxembourg', '6686', 'Flamierge'),
       (1652, 1, 'Luxembourg', '6687', 'Bertogne'),
       (1653, 1, 'Luxembourg', '6688', 'Longchamps'),
       (1654, 1, 'Luxembourg', '6690', 'Bihain'),
       (1655, 1, 'Luxembourg', '6690', 'Vielsalm'),
       (1656, 1, 'Luxembourg', '6692', 'Petit-Thier'),
       (1657, 1, 'Luxembourg', '6698', 'Grand-Halleux'),
       (1658, 1, 'Luxembourg', '6700', 'Arlon'),
       (1659, 1, 'Luxembourg', '6700', 'Bonnert'),
       (1660, 1, 'Luxembourg', '6700', 'Heinsch'),
       (1661, 1, 'Luxembourg', '6700', 'Toernich'),
       (1662, 1, 'Luxembourg', '6704', 'Guirsch'),
       (1663, 1, 'Luxembourg', '6706', 'Autelbas'),
       (1664, 1, 'Luxembourg', '6717', 'Attert'),
       (1665, 1, 'Luxembourg', '6717', 'Nobressart'),
       (1666, 1, 'Luxembourg', '6717', 'Nothomb'),
       (1667, 1, 'Luxembourg', '6717', 'Thiaumont'),
       (1668, 1, 'Luxembourg', '6717', 'Tontelange'),
       (1669, 1, 'Luxembourg', '6720', 'Habay-La-Neuve'),
       (1670, 1, 'Luxembourg', '6720', 'Hachy'),
       (1671, 1, 'Luxembourg', '6721', 'Anlier'),
       (1672, 1, 'Luxembourg', '6723', 'Habay-La-Vieille'),
       (1673, 1, 'Luxembourg', '6724', 'Houdemont'),
       (1674, 1, 'Luxembourg', '6724', 'Rulles'),
       (1675, 1, 'Luxembourg', '6730', 'Bellefontaine'),
       (1676, 1, 'Luxembourg', '6730', 'Rossignol'),
       (1677, 1, 'Luxembourg', '6730', 'Saint-Vincent'),
       (1678, 1, 'Luxembourg', '6730', 'Tintigny'),
       (1679, 1, 'Luxembourg', '6740', 'Etalle'),
       (1680, 1, 'Luxembourg', '6740', 'Sainte-Marie-Sur-Semois'),
       (1681, 1, 'Luxembourg', '6740', 'Villers-Sur-Semois'),
       (1682, 1, 'Luxembourg', '6741', 'Vance'),
       (1683, 1, 'Luxembourg', '6742', 'Chantemelle'),
       (1684, 1, 'Luxembourg', '6743', 'Buzenol'),
       (1685, 1, 'Luxembourg', '6747', 'Châtillon'),
       (1686, 1, 'Luxembourg', '6747', 'Meix-Le-Tige'),
       (1687, 1, 'Luxembourg', '6747', 'Saint-Léger'),
       (1688, 1, 'Luxembourg', '6750', 'Musson'),
       (1689, 1, 'Luxembourg', '6750', 'Mussy-La-Ville'),
       (1690, 1, 'Luxembourg', '6750', 'Signeulx'),
       (1691, 1, 'Luxembourg', '6760', 'Bleid');

-- --------------------------------------------------------

--
-- Structure de la table `civilities`
--

CREATE TABLE `civilities`
(
    `ID`    int(11) UNSIGNED NOT NULL,
    `Label` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `civilities`
--

INSERT INTO `civilities` (`ID`, `Label`)
VALUES (1, 'Mr'),
       (2, 'Mme');

-- --------------------------------------------------------

--
-- Structure de la table `companies`
--

CREATE TABLE `companies`
(
    `ID`                   int(11) UNSIGNED NOT NULL,
    `ID_Users`             int(11) UNSIGNED NOT NULL,
    `ID_Company_Types`     int(11) UNSIGNED NOT NULL,
    `ID_Branch_Activities` int(11) UNSIGNED DEFAULT NULL,
    `Label`                varchar(255) COLLATE utf8_bin NOT NULL,
    `Description`          varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `VAT_Number`           varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `Bank_Account`         varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `Domain_Name`          varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `Employees_Number`     int(11) DEFAULT NULL,
    `Modification_Date`    datetime                      DEFAULT NULL,
    `Register_Date`        datetime                      NOT NULL,
    `Annual_Sales`         double                        DEFAULT NULL,
    `Revenue`              double                        DEFAULT NULL,
    `LinkedIn_Page`        varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `Creation_Date`        int(11) DEFAULT NULL,
    `Closing_Date`         int(11) DEFAULT NULL,
    `Phone_Number`         varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `Email`                varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `IsActive`             tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `companies`
--

INSERT INTO `companies` (`ID`, `ID_Users`, `ID_Company_Types`, `ID_Branch_Activities`, `Label`, `Description`,
                         `VAT_Number`, `Bank_Account`, `Domain_Name`, `Employees_Number`, `Modification_Date`,
                         `Register_Date`, `Annual_Sales`, `Revenue`, `LinkedIn_Page`, `Creation_Date`, `Closing_Date`,
                         `Phone_Number`, `Email`, `IsActive`)
VALUES (2, 1, 2, 4, 'VOO SA', '', 'BE-0205954655', 'BE-94091000619314', 'voo.be', 0, '2021-10-07 13:01:00',
        '2021-10-07 12:27:33', NULL, 278665105.28, '', 0, 0, '+32 08008002', '', 1),
       (3, 1, 4, 1, 'Amazon', '', '', '', 'Amazon.com', 0, '2021-10-07 12:29:16', '2021-10-07 12:28:16', NULL, NULL, '',
        0, 0, '', '', 1),
       (4, 10, 1, 3, 'Barcelona F.C', NULL, NULL, NULL, '', 0, NULL, '2021-10-07 12:41:26', NULL, NULL, '', 0, 0, NULL,
        NULL, 1),
       (5, 1, 2, 4, 'Orange', NULL, NULL, NULL, '', 0, NULL, '2021-10-07 13:08:44', NULL, NULL, '', 0, 0, NULL, NULL,
        0),
       (6, 9, 1, 1, 'Samsung', NULL, NULL, NULL, '', 0, NULL, '2021-10-07 13:20:40', NULL, NULL, '', 0, 0, NULL, NULL,
        1),
       (7, 9, 1, NULL, 'Sony', NULL, NULL, NULL, '', 0, NULL, '2021-10-07 13:20:46', NULL, NULL, '', 0, 0, NULL, NULL,
        0);

-- --------------------------------------------------------

--
-- Structure de la table `companies_contacts`
--

CREATE TABLE `companies_contacts`
(
    `ID`           int(11) UNSIGNED NOT NULL,
    `ID_Companies` int(11) UNSIGNED NOT NULL,
    `ID_Contacts`  int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `companies_contacts`
--

INSERT INTO `companies_contacts` (`ID`, `ID_Companies`, `ID_Contacts`)
VALUES (2, 4, 2);

-- --------------------------------------------------------

--
-- Structure de la table `company_types`
--

CREATE TABLE `company_types`
(
    `ID`    int(11) UNSIGNED NOT NULL,
    `Label` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `company_types`
--

INSERT INTO `company_types` (`ID`, `Label`)
VALUES (1, 'Prospect'),
       (2, 'Partenaire'),
       (3, 'Revendeur'),
       (4, 'Fournisseur'),
       (5, 'Autre');

-- --------------------------------------------------------

--
-- Structure de la table `contacts`
--

CREATE TABLE `contacts`
(
    `ID`                   int(11) UNSIGNED NOT NULL,
    `ID_Users`             int(11) UNSIGNED NOT NULL,
    `ID_Civilities`        int(11) UNSIGNED DEFAULT NULL,
    `ID_Job_Titles`        int(11) UNSIGNED DEFAULT NULL,
    `ID_Contact_Types`     int(11) UNSIGNED NOT NULL,
    `ID_Branch_Activities` int(11) UNSIGNED DEFAULT NULL,
    `Lastname`             varchar(255) COLLATE utf8_bin NOT NULL,
    `Firstname`            varchar(255) COLLATE utf8_bin NOT NULL,
    `Bank_Account`         varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `Email`                varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `Register_Date`        datetime                      NOT NULL,
    `Modification_Date`    datetime                      DEFAULT NULL,
    `Phone_Number`         varchar(255) COLLATE utf8_bin DEFAULT NULL,
    `IsActive`             tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `contacts`
--

INSERT INTO `contacts` (`ID`, `ID_Users`, `ID_Civilities`, `ID_Job_Titles`, `ID_Contact_Types`, `ID_Branch_Activities`,
                        `Lastname`, `Firstname`, `Bank_Account`, `Email`, `Register_Date`, `Modification_Date`,
                        `Phone_Number`, `IsActive`)
VALUES (1, 1, NULL, 1, 2, NULL, 'Parker', 'Peter', NULL, '', '2021-10-07 12:34:28', NULL, '+32 470451255', 1),
       (2, 10, 1, 3, 2, NULL, 'Busquets', 'Sergio', '', '', '2021-10-07 12:40:51', '2021-10-07 12:41:59', '', 1),
       (3, 1, NULL, NULL, 2, NULL, 'Wick', 'John', NULL, '', '2021-10-07 13:09:16', NULL, '', 0),
       (4, 9, NULL, NULL, 2, NULL, 'Kàkà', 'Ricardo', NULL, '', '2021-10-07 13:22:34', NULL, '', 1),
       (5, 9, NULL, NULL, 2, NULL, 'Guardiola', 'Pep', NULL, '', '2021-10-07 13:22:46', NULL, '', 1),
       (6, 9, NULL, NULL, 2, NULL, 'Mendy', 'Benjamin', NULL, '', '2021-10-07 13:23:04', NULL, '', 0);

-- --------------------------------------------------------

--
-- Structure de la table `contact_types`
--

CREATE TABLE `contact_types`
(
    `ID`    int(11) UNSIGNED NOT NULL,
    `Label` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `contact_types`
--

INSERT INTO `contact_types` (`ID`, `Label`)
VALUES (1, 'Prospect'),
       (2, 'Client');

-- --------------------------------------------------------

--
-- Structure de la table `conversations`
--

CREATE TABLE `conversations`
(
    `ID`            int(11) UNSIGNED NOT NULL,
    `ID_Users`      int(11) UNSIGNED NOT NULL,
    `Creation_Date` datetime                      NOT NULL,
    `Message`       varchar(255) COLLATE utf8_bin NOT NULL,
    `IsActive`      tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `conversations`
--

INSERT INTO `conversations` (`ID`, `ID_Users`, `Creation_Date`, `Message`, `IsActive`)
VALUES (1, 1, '2021-10-07 13:01:19', 'Bienvenue à tous ! ', 1),
       (2, 9, '2021-10-07 13:02:41', 'Bonjour ! ', 1),
       (3, 9, '2021-10-07 13:20:01', 'Hello ! ', 0),
       (4, 10, '2021-10-07 13:26:08', 'Salut ! ', 1);

-- --------------------------------------------------------

--
-- Structure de la table `countries`
--

CREATE TABLE `countries`
(
    `ID`    int(11) UNSIGNED NOT NULL,
    `Label` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `countries`
--

INSERT INTO `countries` (`ID`, `Label`)
VALUES (1, 'Belgique');

-- --------------------------------------------------------

--
-- Structure de la table `job_titles`
--

CREATE TABLE `job_titles`
(
    `ID`    int(11) UNSIGNED NOT NULL,
    `Label` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `job_titles`
--

INSERT INTO `job_titles` (`ID`, `Label`)
VALUES (1, 'Sécrétaire'),
       (2, 'Docteur'),
       (3, 'Ingénieur');

-- --------------------------------------------------------

--
-- Structure de la table `notes`
--

CREATE TABLE `notes`
(
    `ID`            int(11) UNSIGNED NOT NULL,
    `ID_Users`      int(11) UNSIGNED NOT NULL,
    `ID_Contacts`   int(11) UNSIGNED DEFAULT NULL,
    `ID_Companies`  int(11) UNSIGNED DEFAULT NULL,
    `Creation_Date` datetime                      NOT NULL,
    `Message`       varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `notes`
--

INSERT INTO `notes` (`ID`, `ID_Users`, `ID_Contacts`, `ID_Companies`, `Creation_Date`, `Message`)
VALUES (1, 1, NULL, 2, '2021-10-07 12:32:50', 'Voo est un fournisseur d\'accès internet'),
(2, 10, NULL, 4, '2021-10-07 12:42:22', 'Club de foot'),
(3, 10, 2, NULL, '2021-10-07 12:43:02', 'A vendre');

-- --------------------------------------------------------

--
-- Structure de la table `permissions`
--

CREATE TABLE `permissions` (
  `ID` int(11) UNSIGNED NOT NULL,
  `Label` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `permissions`
--

INSERT INTO `permissions` (`ID`, `Label`) VALUES
(90, 'addBranchActivities'),
(33, 'addCompagnies'),
(37, 'addContacts'),
(9, 'addConversations'),
(41, 'addJobTitles'),
(45, 'addNotes'),
(6, 'addRoles'),
(49, 'addRolesPermissions'),
(57, 'addTaskTypes'),
(53, 'addTasks'),
(93, 'addTransactionHistories'),
(61, 'addTransactions'),
(1, 'addUsers'),
(99, 'addVoucherHistories'),
(77, 'addVouchers'),
(35, 'deleteCompagnies'),
(40, 'deleteContacts'),
(11, 'deleteConversations'),
(42, 'deleteJobTitles'),
(47, 'deleteNotes'),
(8, 'deleteRoles'),
(52, 'deleteRolesPermissions'),
(58, 'deleteTaskTypes'),
(56, 'deleteTasks'),
(97, 'deleteTransactionHistories'),
(63, 'deleteTransactions'),
(4, 'deleteUsers'),
(101, 'deleteVoucherHistories'),
(89, 'showBranchActivities'),
(34, 'showCompagnies'),
(38, 'showContacts'),
(12, 'showConversations'),
(44, 'showJobTitles'),
(48, 'showNotes'),
(5, 'showRoles'),
(51, 'showRolesPermissions'),
(60, 'showTaskTypes'),
(55, 'showTasks'),
(92, 'showTransactionHistories'),
(62, 'showTransactions'),
(3, 'showUsers'),
(98, 'showVoucherHistories'),
(80, 'showVouchers'),
(91, 'updateBranchActivities'),
(36, 'updateCompagnies'),
(39, 'updateContacts'),
(43, 'updateJobTitles'),
(46, 'updateNotes'),
(7, 'updateRoles'),
(50, 'updateRolesPermissions'),
(59, 'updateTaskTypes'),
(54, 'updateTasks'),
(96, 'updateTransactionHistories'),
(64, 'updateTransactions'),
(2, 'updateUsers'),
(100, 'updateVoucherHistories'),
(78, 'updateVouchers');

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

CREATE TABLE `roles` (
  `ID` int(11) UNSIGNED NOT NULL,
  `Label` varchar(255) COLLATE utf8_bin NOT NULL,
  `IsActive` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `roles`
--

INSERT INTO `roles` (`ID`, `Label`, `IsActive`) VALUES
(1, 'Admin', 1),
(2, 'Utilisateur', 1),
(3, 'Guest', 1);

-- --------------------------------------------------------

--
-- Structure de la table `roles_permissions`
--

CREATE TABLE `roles_permissions` (
  `ID` int(11) UNSIGNED NOT NULL,
  `ID_Roles` int(11) UNSIGNED NOT NULL,
  `ID_Permissions` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `roles_permissions`
--

INSERT INTO `roles_permissions` (`ID`, `ID_Roles`, `ID_Permissions`) VALUES
(1577, 1, 90),
(1578, 1, 33),
(1579, 1, 37),
(1580, 1, 9),
(1581, 1, 41),
(1582, 1, 45),
(1583, 1, 6),
(1584, 1, 49),
(1585, 1, 57),
(1586, 1, 53),
(1587, 1, 93),
(1588, 1, 61),
(1589, 1, 1),
(1590, 1, 99),
(1591, 1, 77),
(1592, 1, 35),
(1593, 1, 40),
(1594, 1, 11),
(1595, 1, 42),
(1596, 1, 47),
(1597, 1, 8),
(1598, 1, 52),
(1599, 1, 58),
(1600, 1, 56),
(1601, 1, 97),
(1602, 1, 63),
(1603, 1, 4),
(1604, 1, 101),
(1605, 1, 89),
(1606, 1, 34),
(1607, 1, 38),
(1608, 1, 12),
(1609, 1, 44),
(1610, 1, 48),
(1611, 1, 5),
(1612, 1, 51),
(1613, 1, 60),
(1614, 1, 55),
(1615, 1, 92),
(1616, 1, 62),
(1617, 1, 3),
(1618, 1, 98),
(1619, 1, 80),
(1620, 1, 91),
(1621, 1, 36),
(1622, 1, 39),
(1623, 1, 43),
(1624, 1, 46),
(1625, 1, 7),
(1626, 1, 50),
(1627, 1, 59),
(1628, 1, 54),
(1629, 1, 96),
(1630, 1, 64),
(1631, 1, 2),
(1632, 1, 100),
(1633, 1, 78),
(1691, 3, 34),
(1692, 3, 38),
(1693, 3, 48),
(1694, 3, 55),
(1695, 3, 92),
(1696, 3, 62),
(1697, 3, 98),
(1698, 3, 80),
(1699, 2, 33),
(1700, 2, 37),
(1701, 2, 9),
(1702, 2, 45),
(1703, 2, 53),
(1704, 2, 93),
(1705, 2, 61),
(1706, 2, 99),
(1707, 2, 77),
(1708, 2, 35),
(1709, 2, 40),
(1710, 2, 47),
(1711, 2, 56),
(1712, 2, 97),
(1713, 2, 63),
(1714, 2, 101),
(1715, 2, 34),
(1716, 2, 38),
(1717, 2, 48),
(1718, 2, 55),
(1719, 2, 92),
(1720, 2, 62),
(1721, 2, 98),
(1722, 2, 80),
(1723, 2, 36),
(1724, 2, 39),
(1725, 2, 46),
(1726, 2, 54),
(1727, 2, 96),
(1728, 2, 64),
(1729, 2, 100),
(1730, 2, 78);

-- --------------------------------------------------------

--
-- Structure de la table `tasks`
--

CREATE TABLE `tasks` (
  `ID` int(11) UNSIGNED NOT NULL,
  `ID_Users` int(11) UNSIGNED NOT NULL,
  `ID_Contacts` int(11) UNSIGNED DEFAULT NULL,
  `ID_Companies` int(11) UNSIGNED DEFAULT NULL,
  `ID_Task_Types` int(11) UNSIGNED DEFAULT NULL,
  `Title` varchar(255) COLLATE utf8_bin NOT NULL,
  `Priority` enum('ELEVEE','MOYENNE','FAIBLE','') COLLATE utf8_bin DEFAULT NULL,
  `Creation_Date` datetime NOT NULL,
  `End_Date` datetime DEFAULT NULL,
  `Description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `tasks`
--

INSERT INTO `tasks` (`ID`, `ID_Users`, `ID_Contacts`, `ID_Companies`, `ID_Task_Types`, `Title`, `Priority`, `Creation_Date`, `End_Date`, `Description`, `Status`) VALUES
(1, 1, 1, 2, 1, 'Appel service marketing', 'MOYENNE', '2021-10-07 12:33:33', '2021-10-21 21:37:00', '', 0),
(2, 10, 2, 4, 2, 'Transfert', 'ELEVEE', '2021-10-07 12:42:46', '2021-10-21 21:42:00', '', 0),
(3, 9, 5, NULL, 1, 'Rappeler Guardiola', 'ELEVEE', '2021-10-07 13:24:32', NULL, '', 0);

-- --------------------------------------------------------

--
-- Structure de la table `task_types`
--

CREATE TABLE `task_types` (
  `ID` int(11) UNSIGNED NOT NULL,
  `Label` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `task_types`
--

INSERT INTO `task_types` (`ID`, `Label`) VALUES
(1, 'Appel'),
(2, 'Email'),
(3, 'A faire');

-- --------------------------------------------------------

--
-- Structure de la table `transactions`
--

CREATE TABLE `transactions` (
  `ID` int(11) UNSIGNED NOT NULL,
  `ID_Users` int(11) UNSIGNED NOT NULL,
  `ID_Contacts` int(11) UNSIGNED DEFAULT NULL,
  `ID_Companies` int(11) UNSIGNED DEFAULT NULL,
  `ID_Transaction_Types` int(11) UNSIGNED DEFAULT NULL,
  `ID_Transaction_Phases` int(11) UNSIGNED DEFAULT NULL,
  `Title` varchar(255) COLLATE utf8_bin NOT NULL,
  `Amount` double UNSIGNED DEFAULT NULL,
  `Creation_Date` datetime NOT NULL,
  `End_Date` datetime DEFAULT NULL,
  `IsActive` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `transactions`
--

INSERT INTO `transactions` (`ID`, `ID_Users`, `ID_Contacts`, `ID_Companies`, `ID_Transaction_Types`, `ID_Transaction_Phases`, `Title`, `Amount`, `Creation_Date`, `End_Date`, `IsActive`) VALUES
(1, 10, 2, 4, 2, 4, 'Transfert Busquets', 1500000, '2021-10-07 12:44:27', '2021-10-21 21:43:00', 1),
(2, 1, NULL, 3, 2, 5, 'Achat pc dell', 10000, '2021-10-07 13:05:25', '2021-10-21 21:04:00', 1),
(3, 1, NULL, 3, 1, 6, 'Achat SSD', 2500, '2021-10-07 13:06:16', NULL, 1),
(4, 9, 5, 6, 1, 5, 'Achat smartphone', 3000, '2021-10-07 13:24:05', NULL, 1);

-- --------------------------------------------------------

--
-- Structure de la table `transaction_histories`
--

CREATE TABLE `transaction_histories` (
  `ID` int(11) UNSIGNED NOT NULL,
  `ID_Transactions` int(11) UNSIGNED NOT NULL,
  `ID_Transaction_Phases` int(11) UNSIGNED DEFAULT NULL,
  `Save_Date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `transaction_histories`
--

INSERT INTO `transaction_histories` (`ID`, `ID_Transactions`, `ID_Transaction_Phases`, `Save_Date`) VALUES
(1, 1, 4, '2021-10-07 12:44:27'),
(2, 2, 5, '2021-10-07 13:05:25'),
(3, 3, 6, '2021-10-07 13:06:16'),
(4, 4, 5, '2021-10-07 13:24:05');

-- --------------------------------------------------------

--
-- Structure de la table `transaction_phases`
--

CREATE TABLE `transaction_phases` (
  `ID` int(11) UNSIGNED NOT NULL,
  `Label` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `transaction_phases`
--

INSERT INTO `transaction_phases` (`ID`, `Label`) VALUES
(1, 'Prospection'),
(2, 'Qualification'),
(3, 'Proposition'),
(4, 'Négociation'),
(5, 'Conclue'),
(6, 'Annulé');

-- --------------------------------------------------------

--
-- Structure de la table `transaction_types`
--

CREATE TABLE `transaction_types` (
  `ID` int(11) UNSIGNED NOT NULL,
  `Label` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `transaction_types`
--

INSERT INTO `transaction_types` (`ID`, `Label`) VALUES
(1, 'Nouvelle entreprise'),
(2, 'Entreprise existante');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `ID` int(11) UNSIGNED NOT NULL,
  `ID_Roles` int(11) UNSIGNED NOT NULL,
  `Firstname` varchar(255) COLLATE utf8_bin NOT NULL,
  `Lastname` varchar(255) COLLATE utf8_bin NOT NULL,
  `Username` varchar(255) COLLATE utf8_bin NOT NULL,
  `Password` varchar(255) COLLATE utf8_bin NOT NULL,
  `Email` varchar(255) COLLATE utf8_bin NOT NULL,
  `Register_Date` datetime NOT NULL,
  `IsActive` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`ID`, `ID_Roles`, `Firstname`, `Lastname`, `Username`, `Password`, `Email`, `Register_Date`, `IsActive`) VALUES
(1, 1, 'Admin', 'Admin', 'Admin', '0fadf52a4580cfebb99e61162139af3d3a6403c1d36b83e4962b721d1c8cbd0b', 'Admin@test.com', '2021-05-15 14:38:10', 1),
(9, 2, 'Lewis', 'Hamilton', 'LewHam42', '0fadf52a4580cfebb99e61162139af3d3a6403c1d36b83e4962b721d1c8cbd0b', 'max2759@gmail.com', '2021-10-06 14:40:38', 1),
(10, 3, 'Lionel', 'Messi', 'LioMes49', '0fadf52a4580cfebb99e61162139af3d3a6403c1d36b83e4962b721d1c8cbd0b', 'max2759@gmail.com', '2021-10-07 12:37:43', 1);

-- --------------------------------------------------------

--
-- Structure de la table `vouchers`
--

CREATE TABLE `vouchers` (
  `ID` int(11) UNSIGNED NOT NULL,
  `ID_Users` int(11) UNSIGNED NOT NULL,
  `ID_Contacts` int(11) UNSIGNED DEFAULT NULL,
  `ID_Companies` int(11) UNSIGNED DEFAULT NULL,
  `ID_Voucher_Status` int(11) UNSIGNED NOT NULL,
  `Title` varchar(255) COLLATE utf8_bin NOT NULL,
  `Description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Priority` enum('ELEVEE','MOYENNE','FAIBLE','') COLLATE utf8_bin DEFAULT NULL,
  `Creation_Date` datetime NOT NULL,
  `End_Date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `vouchers`
--

INSERT INTO `vouchers` (`ID`, `ID_Users`, `ID_Contacts`, `ID_Companies`, `ID_Voucher_Status`, `Title`, `Description`, `Priority`, `Creation_Date`, `End_Date`) VALUES
(1, 10, 2, 4, 2, 'Transfert', '', 'ELEVEE', '2021-10-07 12:43:32', NULL),
(2, 1, NULL, 3, 1, 'Annulation achat SSD', '', 'ELEVEE', '2021-10-07 13:06:48', NULL),
(3, 1, NULL, 3, 3, 'Erreur prix achat dell', '', 'ELEVEE', '2021-10-07 13:07:28', '2021-10-07 13:07:28'),
(4, 1, NULL, 3, 2, 'Demander partenariat Amazon Prime', '', 'ELEVEE', '2021-10-07 13:08:01', NULL),
(5, 9, NULL, 6, 2, 'Emballage défectueux', '', 'FAIBLE', '2021-10-07 13:25:21', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `voucher_histories`
--

CREATE TABLE `voucher_histories` (
  `ID` int(11) UNSIGNED NOT NULL,
  `ID_Vouchers` int(11) UNSIGNED NOT NULL,
  `ID_Voucher_Status` int(11) UNSIGNED NOT NULL,
  `Save_Date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `voucher_histories`
--

INSERT INTO `voucher_histories` (`ID`, `ID_Vouchers`, `ID_Voucher_Status`, `Save_Date`) VALUES
(1, 1, 2, '2021-10-07 12:43:32'),
(2, 2, 1, '2021-10-07 13:06:48'),
(3, 3, 3, '2021-10-07 13:07:28'),
(4, 4, 2, '2021-10-07 13:08:01'),
(5, 5, 2, '2021-10-07 13:25:21');

-- --------------------------------------------------------

--
-- Structure de la table `voucher_status`
--

CREATE TABLE `voucher_status` (
  `ID` int(11) UNSIGNED NOT NULL,
  `Label` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `voucher_status`
--

INSERT INTO `voucher_status` (`ID`, `Label`) VALUES
(1, 'En attente de contact'),
(2, 'En attente'),
(3, 'Fermé');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `addresses`
--
ALTER TABLE `addresses`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Cities` (`ID_Cities`),
  ADD KEY `ID_Contacts` (`ID_Contacts`),
  ADD KEY `ID_Companies` (`ID_Companies`) USING BTREE;

--
-- Index pour la table `branch_activities`
--
ALTER TABLE `branch_activities`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `cities`
--
ALTER TABLE `cities`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Countries` (`ID_Countries`);

--
-- Index pour la table `civilities`
--
ALTER TABLE `civilities`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `companies`
--
ALTER TABLE `companies`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Branch_Activities` (`ID_Branch_Activities`),
  ADD KEY `ID_Users` (`ID_Users`),
  ADD KEY `ID_Company_Types` (`ID_Company_Types`) USING BTREE;

--
-- Index pour la table `companies_contacts`
--
ALTER TABLE `companies_contacts`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Contacts` (`ID_Contacts`),
  ADD KEY `ID_Companies` (`ID_Companies`) USING BTREE;

--
-- Index pour la table `company_types`
--
ALTER TABLE `company_types`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `contacts`
--
ALTER TABLE `contacts`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Branch_Activities` (`ID_Branch_Activities`),
  ADD KEY `ID_Civilities` (`ID_Civilities`),
  ADD KEY `ID_Contact_Types` (`ID_Contact_Types`),
  ADD KEY `ID_Job_Titles` (`ID_Job_Titles`),
  ADD KEY `ID_Users` (`ID_Users`);

--
-- Index pour la table `contact_types`
--
ALTER TABLE `contact_types`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `conversations`
--
ALTER TABLE `conversations`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Users` (`ID_Users`);

--
-- Index pour la table `countries`
--
ALTER TABLE `countries`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `job_titles`
--
ALTER TABLE `job_titles`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `notes`
--
ALTER TABLE `notes`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_ Contacts` (`ID_Contacts`),
  ADD KEY `ID_Users` (`ID_Users`),
  ADD KEY `ID_ Companies` (`ID_Companies`) USING BTREE;

--
-- Index pour la table `permissions`
--
ALTER TABLE `permissions`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Label` (`Label`);

--
-- Index pour la table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Label` (`Label`);

--
-- Index pour la table `roles_permissions`
--
ALTER TABLE `roles_permissions`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Permissions` (`ID_Permissions`),
  ADD KEY `ID_Roles` (`ID_Roles`);

--
-- Index pour la table `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Contacts` (`ID_Contacts`),
  ADD KEY `ID_Users` (`ID_Users`),
  ADD KEY `ID_Task_Types` (`ID_Task_Types`),
  ADD KEY `ID_Companies` (`ID_Companies`) USING BTREE;

--
-- Index pour la table `task_types`
--
ALTER TABLE `task_types`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Contacts` (`ID_Contacts`),
  ADD KEY `ID_Transaction_Phases` (`ID_Transaction_Phases`),
  ADD KEY `ID_Transaction_Types` (`ID_Transaction_Types`),
  ADD KEY `ID_Users` (`ID_Users`),
  ADD KEY `ID_Companies` (`ID_Companies`) USING BTREE;

--
-- Index pour la table `transaction_histories`
--
ALTER TABLE `transaction_histories`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Transactions` (`ID_Transactions`),
  ADD KEY `ID_Transaction_Phases` (`ID_Transaction_Phases`);

--
-- Index pour la table `transaction_phases`
--
ALTER TABLE `transaction_phases`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `transaction_types`
--
ALTER TABLE `transaction_types`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Username` (`Username`),
  ADD KEY `ID_Roles` (`ID_Roles`);

--
-- Index pour la table `vouchers`
--
ALTER TABLE `vouchers`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Contacts` (`ID_Contacts`),
  ADD KEY `ID_Users` (`ID_Users`),
  ADD KEY `ID_Voucher_Status` (`ID_Voucher_Status`),
  ADD KEY `ID_Companies` (`ID_Companies`) USING BTREE;

--
-- Index pour la table `voucher_histories`
--
ALTER TABLE `voucher_histories`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Vouchers` (`ID_Vouchers`),
  ADD KEY `ID_Voucher_Status` (`ID_Voucher_Status`);

--
-- Index pour la table `voucher_status`
--
ALTER TABLE `voucher_status`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `addresses`
--
ALTER TABLE `addresses`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `branch_activities`
--
ALTER TABLE `branch_activities`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `cities`
--
ALTER TABLE `cities`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1692;

--
-- AUTO_INCREMENT pour la table `civilities`
--
ALTER TABLE `civilities`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `companies`
--
ALTER TABLE `companies`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `companies_contacts`
--
ALTER TABLE `companies_contacts`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `company_types`
--
ALTER TABLE `company_types`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `contacts`
--
ALTER TABLE `contacts`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `contact_types`
--
ALTER TABLE `contact_types`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `conversations`
--
ALTER TABLE `conversations`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `countries`
--
ALTER TABLE `countries`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `job_titles`
--
ALTER TABLE `job_titles`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `notes`
--
ALTER TABLE `notes`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `permissions`
--
ALTER TABLE `permissions`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;

--
-- AUTO_INCREMENT pour la table `roles`
--
ALTER TABLE `roles`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `roles_permissions`
--
ALTER TABLE `roles_permissions`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1731;

--
-- AUTO_INCREMENT pour la table `tasks`
--
ALTER TABLE `tasks`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `task_types`
--
ALTER TABLE `task_types`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `transaction_histories`
--
ALTER TABLE `transaction_histories`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `transaction_phases`
--
ALTER TABLE `transaction_phases`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `transaction_types`
--
ALTER TABLE `transaction_types`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `vouchers`
--
ALTER TABLE `vouchers`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `voucher_histories`
--
ALTER TABLE `voucher_histories`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `voucher_status`
--
ALTER TABLE `voucher_status`
  MODIFY `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `addresses`
--
ALTER TABLE `addresses`
  ADD CONSTRAINT `addresses_ibfk_1` FOREIGN KEY (`ID_Cities`) REFERENCES `cities` (`ID`),
  ADD CONSTRAINT `addresses_ibfk_2` FOREIGN KEY (`ID_Companies`) REFERENCES `companies` (`ID`),
  ADD CONSTRAINT `addresses_ibfk_3` FOREIGN KEY (`ID_Contacts`) REFERENCES `contacts` (`ID`);

--
-- Contraintes pour la table `cities`
--
ALTER TABLE `cities`
  ADD CONSTRAINT `cities_ibfk_1` FOREIGN KEY (`ID_Countries`) REFERENCES `countries` (`ID`);

--
-- Contraintes pour la table `companies`
--
ALTER TABLE `companies`
  ADD CONSTRAINT `companies_ibfk_1` FOREIGN KEY (`ID_Branch_Activities`) REFERENCES `branch_activities` (`ID`),
  ADD CONSTRAINT `companies_ibfk_2` FOREIGN KEY (`ID_Company_Types`) REFERENCES `company_types` (`ID`),
  ADD CONSTRAINT `companies_ibfk_3` FOREIGN KEY (`ID_Users`) REFERENCES `users` (`ID`);

--
-- Contraintes pour la table `companies_contacts`
--
ALTER TABLE `companies_contacts`
  ADD CONSTRAINT `companies_contacts_ibfk_1` FOREIGN KEY (`ID_Companies`) REFERENCES `companies` (`ID`),
  ADD CONSTRAINT `companies_contacts_ibfk_2` FOREIGN KEY (`ID_Contacts`) REFERENCES `contacts` (`ID`);

--
-- Contraintes pour la table `contacts`
--
ALTER TABLE `contacts`
  ADD CONSTRAINT `contacts_ibfk_1` FOREIGN KEY (`ID_Branch_Activities`) REFERENCES `branch_activities` (`ID`),
  ADD CONSTRAINT `contacts_ibfk_2` FOREIGN KEY (`ID_Civilities`) REFERENCES `civilities` (`ID`),
  ADD CONSTRAINT `contacts_ibfk_3` FOREIGN KEY (`ID_Contact_Types`) REFERENCES `contact_types` (`ID`),
  ADD CONSTRAINT `contacts_ibfk_4` FOREIGN KEY (`ID_Job_Titles`) REFERENCES `job_titles` (`ID`),
  ADD CONSTRAINT `contacts_ibfk_5` FOREIGN KEY (`ID_Users`) REFERENCES `users` (`ID`);

--
-- Contraintes pour la table `conversations`
--
ALTER TABLE `conversations`
  ADD CONSTRAINT `conversations_ibfk_1` FOREIGN KEY (`ID_Users`) REFERENCES `users` (`ID`);

--
-- Contraintes pour la table `notes`
--
ALTER TABLE `notes`
  ADD CONSTRAINT `notes_ibfk_1` FOREIGN KEY (`ID_Companies`) REFERENCES `companies` (`ID`),
  ADD CONSTRAINT `notes_ibfk_3` FOREIGN KEY (`ID_Users`) REFERENCES `users` (`ID`),
  ADD CONSTRAINT `notes_ibfk_4` FOREIGN KEY (`ID_Contacts`) REFERENCES `contacts` (`ID`);

--
-- Contraintes pour la table `roles_permissions`
--
ALTER TABLE `roles_permissions`
  ADD CONSTRAINT `roles_permissions_ibfk_1` FOREIGN KEY (`ID_Permissions`) REFERENCES `permissions` (`ID`),
  ADD CONSTRAINT `roles_permissions_ibfk_2` FOREIGN KEY (`ID_Roles`) REFERENCES `roles` (`ID`);

--
-- Contraintes pour la table `tasks`
--
ALTER TABLE `tasks`
  ADD CONSTRAINT `tasks_ibfk_1` FOREIGN KEY (`ID_Companies`) REFERENCES `companies` (`ID`),
  ADD CONSTRAINT `tasks_ibfk_3` FOREIGN KEY (`ID_Users`) REFERENCES `users` (`ID`),
  ADD CONSTRAINT `tasks_ibfk_4` FOREIGN KEY (`ID_Task_Types`) REFERENCES `task_types` (`ID`),
  ADD CONSTRAINT `tasks_ibfk_5` FOREIGN KEY (`ID_Contacts`) REFERENCES `contacts` (`ID`);

--
-- Contraintes pour la table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`ID_Companies`) REFERENCES `companies` (`ID`),
  ADD CONSTRAINT `transactions_ibfk_3` FOREIGN KEY (`ID_Transaction_Phases`) REFERENCES `transaction_phases` (`ID`),
  ADD CONSTRAINT `transactions_ibfk_4` FOREIGN KEY (`ID_Transaction_Types`) REFERENCES `transaction_types` (`ID`),
  ADD CONSTRAINT `transactions_ibfk_5` FOREIGN KEY (`ID_Users`) REFERENCES `users` (`ID`),
  ADD CONSTRAINT `transactions_ibfk_6` FOREIGN KEY (`ID_Contacts`) REFERENCES `contacts` (`ID`);

--
-- Contraintes pour la table `transaction_histories`
--
ALTER TABLE `transaction_histories`
  ADD CONSTRAINT `transaction_histories_ibfk_1` FOREIGN KEY (`ID_Transactions`) REFERENCES `transactions` (`ID`),
  ADD CONSTRAINT `transaction_histories_ibfk_2` FOREIGN KEY (`ID_Transaction_Phases`) REFERENCES `transaction_phases` (`ID`);

--
-- Contraintes pour la table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`ID_Roles`) REFERENCES `roles` (`ID`);

--
-- Contraintes pour la table `vouchers`
--
ALTER TABLE `vouchers`
  ADD CONSTRAINT `vouchers_ibfk_1` FOREIGN KEY (`ID_Companies`) REFERENCES `companies` (`ID`),
  ADD CONSTRAINT `vouchers_ibfk_3` FOREIGN KEY (`ID_Users`) REFERENCES `users` (`ID`),
  ADD CONSTRAINT `vouchers_ibfk_4` FOREIGN KEY (`ID_Voucher_Status`) REFERENCES `voucher_status` (`ID`),
  ADD CONSTRAINT `vouchers_ibfk_5` FOREIGN KEY (`ID_Contacts`) REFERENCES `contacts` (`ID`);

--
-- Contraintes pour la table `voucher_histories`
--
ALTER TABLE `voucher_histories`
  ADD CONSTRAINT `voucher_histories_ibfk_1` FOREIGN KEY (`ID_Vouchers`) REFERENCES `vouchers` (`ID`),
  ADD CONSTRAINT `voucher_histories_ibfk_2` FOREIGN KEY (`ID_Voucher_Status`) REFERENCES `voucher_status` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
