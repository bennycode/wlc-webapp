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
	
-- Categories	
SET foreign_key_checks = 0;
TRUNCATE TABLE `category`;
SET foreign_key_checks = 1;

INSERT INTO `category` 
	(`ID`, `COLOR`, `CREATED`, `LASTMODIFIED`, `SLUG`, `NAME`) 
VALUES 
	(1, '#19A2DE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'windows-phone', 'Windows Phone'),
	(2, '#E61400', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'java', 'Java'),
	(3,'#643EBF',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'php','PHP'),
	(4,'#9C5100',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'theorie','Theorie'),
	(5,'#2672EC',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'windows-7','Windows 7'),
	(7,'#EF9608',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'c','C'),
	(8,'#8CBE29',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'android','Android'),
	(9,'#F8CA00',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'c++','C++'),
	(10,'#95040B',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'ruby','Ruby'),
	(11,'#00C2D6',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'c-sharp','C#'),
	(12,'#F14A29',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'html5','HTML5'),
	(13,'#FF6D34',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'mysql','MySQL'),
	(14,'#00ADEF',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'windows-store','Windows Store'),
	(15,'#0093F8',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'javascript','JavaScript'),
	(16,'#3C4B6B',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'python','Python'),
	(17,'#999999',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'iphone','iPhone'),
	(18,'#999999',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'objective-c','Objective-C'),
	(19,'#669900',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'linux','Linux'),
	(20,'#0099CC',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'visual-basic','Visual Basic'),
	(21,'#7B9FCF',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'delphi','Delphi')
	;
	
-- Playlists

SET foreign_key_checks = 0;
TRUNCATE TABLE `playlist`;
SET foreign_key_checks = 1;

INSERT INTO  `playlist` 
	(`ID`, `CODE`, `CREATED`, `LASTMODIFIED`, `NAME`, `AUTHOR_ID`, `CATEGORY_ID`, `LANGUAGECODE`)
VALUES 
	(1, '406285BEFEA47E54z', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Windows Phone Workshop (MMT30)', 7, 1, 'German'),
	(2, '5EA5B1829771349A',	CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Windows Phone 7 Dev Speedruns', 5, 1, 'German')
	;