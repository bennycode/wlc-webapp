-- Authors
SET foreign_key_checks = 0;
TRUNCATE TABLE `author`;
SET foreign_key_checks = 1;

INSERT INTO `author` 
	(`ID`, `CHANNELURL`, `CREATED`, `DESCRIPTION`, `LASTMODIFIED`, `NAME`, `WEBSITE`) 
VALUES 
	(1, 'https://www.youtube.com/user/apfelbenny', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Benny Neugebauer', 'http://www.bennyn.de/'),
	(2, 'https://www.youtube.com/user/tomwendelger', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Tom Wendel', 'http://tomwendelblog.wordpress.com/'),
	(3, 'https://www.youtube.com/user/JoernLoviscach', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Prof. Dr. Jörn Loviscach', 'http://www.j3L7h.de/'),
	(4, NULL, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Lars Vogel', 'http://www.vogella.com/'),
	(5, NULL, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Tom Wendel & Marius Musch', NULL),
	(6, NULL, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Tom Wendel, Kai Jäger & Felix Rieseberg', NULL),
	(7, NULL, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Tom Wendel & Felix Rieseberg', NULL),
	(8, 'http://vimeo.com/wolter', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Sascha Wolter', 'http://www.wolter.biz/'),
	(9, NULL, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Alex Garrett', 'http://phpacademy.org/'),
	(10, 'https://www.youtube.com/user/thenewboston', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Bucky Roberts', 'http://thenewboston.org/'),
	(11, 'http://www.youtube.com/user/iTzAdam5X', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'iTzAdam5X', NULL),
	(12, 'http://www.youtube.com/user/VISUALWORLDGmbH', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Géraud Reichert', 'http://www.visual-world.de/'),
	(13, 'http://www.youtube.com/user/johnpapaYT', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'John Papa', 'http://www.johnpapa.net/'),
	(14, NULL, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Adam Bien', 'http://adam-bien.tv/'),
	(15, NULL, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Travis', NULL),
	(16, 'https://www.youtube.com/user/NixiePixel', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Nixie Pixel', 'http://www.nixiepixel.com/'),
	(17, 'https://www.youtube.com/user/wwwTheTutorialde', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Christian Simons', 'http://www.thetutorial.de/'),
	(18, NULL, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Andy Beaulieau', 'http://www.andybeaulieu.com/'),
	(19, NULL, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Bob Tabor', 'http://www.bobtabor.com/'),
	(20, NULL, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Dan Fernandez', NULL),
	(21, NULL, CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Rick Barraza', NULL),
	(22, 'https://www.youtube.com/user/codegearguru', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Alister Christie', 'http://www.learndelphi.tv/'),
	(23, 'https://www.youtube.com/user/theSkyLapse', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Moritz Gunz & Leo Bernard', 'http://skylapse.de/'),
	(24, 'https://www.youtube.com/user/iBasicTutorials', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'iBasicTutorials', NULL),
	(25, 'https://www.youtube.com/user/tsjDEV86', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Thomas Sebastian Jensen', 'http://www.tsjdev.de/'),
	(26, 'https://www.youtube.com/user/CsDoctore', CURRENT_TIMESTAMP, '', CURRENT_TIMESTAMP, 'Doctore', NULL)
	;