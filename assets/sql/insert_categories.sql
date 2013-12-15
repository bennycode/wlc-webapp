USE welovecoding;

SET FOREIGN_KEY_CHECKS=0;
-- TRUNCATE playlists;
TRUNCATE categories;
SET FOREIGN_KEY_CHECKS=1;

INSERT INTO categories(id,title,slug,color)VALUES
('1', 'Windows Phone', 'windows-phone', '#19A2DE'),
('2', 'Java', 'java', '#E61400'),
('3', 'PHP', 'php', '#643EBF'),
('4', 'Theorie', 'theorie', '#9C5100'),
('5', 'Windows 7', 'windows-7', '#2672EC'),
('6', 'Fun', 'fun', '#E671B5'),
('7', 'C', 'c', '#EF9608'),
('8', 'Android', 'android', '#8CBE29'),
('9', 'C++', 'c-plus-plus', '#F8CA00'),
('10','Ruby', 'ruby', '#95040B'),
('11','C#', 'c-sharp', '#00C2D6'),
('12','HTML5', 'html5', '#F14A29'),
('13','MySQL', 'mysql', '#FF6D34'),
('14','Windows Store', 'windows-store', '#00ADEF'),
('15','JavaScript', 'javascript', '#0093F8'),
('16','Python', 'python', '#3C4B6B'),
('17','iPhone', 'iphone', '#999999'),
('18','Objective-C', 'objective-c', '#999999'),
('19','Linux', 'linux', '#669900'),
('20','Visual Basic', 'visual-basic', '#0099CC'),
('21','Delphi', 'delphi', '#7B9FCF');