USE `welovecoding`;

SET FOREIGN_KEY_CHECKS=0;

--
-- `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COLOR` varchar(7) NOT NULL,
  `CREATED` datetime DEFAULT NULL,
  `LASTMODIFIED` datetime DEFAULT NULL,
  `SLUG` varchar(255) NOT NULL,
  `TITLE` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY (`TITLE`)
) ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

--
-- `providers`
--

DROP TABLE IF EXISTS `providers`;
CREATE TABLE IF NOT EXISTS `providers` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `LASTMODIFIED` datetime DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

--
-- `languages`
--

DROP TABLE IF EXISTS `languages`;
CREATE TABLE IF NOT EXISTS `languages` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `LASTMODIFIED` datetime DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

--
-- `owners`
--

DROP TABLE IF EXISTS `owners`;
CREATE TABLE IF NOT EXISTS `owners` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `channel_url` varchar(255) DEFAULT NULL,
  `CREATED` datetime DEFAULT NULL,
  `DESCRIPTION` longtext,
  `LASTMODIFIED` datetime DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  `TYPE` int(11) DEFAULT NULL,
  `WEBSITE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

--
-- `providers`
--

DROP TABLE IF EXISTS `providers`;
CREATE TABLE IF NOT EXISTS `providers` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATED` datetime DEFAULT NULL,
  `LASTMODIFIED` datetime DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

--
-- `playlists`
--

DROP TABLE IF EXISTS `playlists`;
CREATE TABLE IF NOT EXISTS `playlists` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(255) DEFAULT NULL,
  `CREATED` datetime DEFAULT NULL,
  `DESCRIPTION` longtext,
  `LASTMODIFIED` datetime DEFAULT NULL,
  `SLUG` varchar(255) NOT NULL,
  `TITLE` varchar(255) NOT NULL,
  `category_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `owner_id` int(11) NOT NULL,
  `provider` int(11) NOT NULL,
  PRIMARY KEY(`id`),
  FOREIGN KEY(category_id) REFERENCES categories(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY(owner_id) REFERENCES owners(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY(provider) REFERENCES providers(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY(language_id) REFERENCES languages(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

--
-- `videos`
--

CREATE TABLE IF NOT EXISTS `videos` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(255) NOT NULL,
  `CREATED` datetime DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `LASTMODIFIED` datetime DEFAULT NULL,
  `TITLE` varchar(255) NOT NULL,
  `playlist_id` int(11) NOT NULL,
  PRIMARY KEY(`ID`),
  FOREIGN KEY(playlist_id) REFERENCES playlists(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

SET FOREIGN_KEY_CHECKS=1;