CREATE SCHEMA IF NOT EXISTS SUPERHEROES;

SET SCHEMA SUPERHEROES;

CREATE TABLE SUPERHERO(

	ID INT NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(255) NOT NULL,
	CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	UPDATED_AT TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	DELETED_AT TIMESTAMP,
	PRIMARY KEY (ID)

);

INSERT INTO SUPERHERO (NAME)
VALUES
  ('Superman'),
  ('Spider-Man'),
  ('Wonder Woman'),
  ('Iron Man'),
  ('Capitán América'),
  ('Viuda Negra'),
  ('Flash'),
  ('Thor'),
  ('Hulk'),
  ('Batman'),
  ('Linterna Verde'),
  ('Aquaman'),
  ('Pantera Negra'),
  ('Wolverine'),
  ('Doctor Strange'),
  ('Supergirl'),
  ('Ant-Man'),
  ('Capitana Marvel'),
  ('Deadpool'),
  ('Green Arrow'),
  ('Bruja Escarlata'),
  ('Ojo de Halcón'),
  ('Cíclope'),
  ('Tormenta'),
  ('Supergirl'),
  ('The Punisher'),
  ('Daredevil'),
  ('Jean Grey'),
  ('Bestia'),
  ('Rogue'),
  ('Nightcrawler'),
  ('Luke Cage'),
  ('Iron Fist'),
  ('Coloso'),
  ('Gambito'),
  ('Silver Surfer'),
  ('Visión'),
  ('Hawkgirl'),
  ('Shazam'),
  ('Batwoman'),
  ('Detective Marciano'),
  ('Robin'),
  ('Acertijo'),
  ('Catwoman'),
  ('Harley Quinn'),
  ('Joker'),
  ('Lex Luthor'),
  ('Penguin'),
  ('Poison Ivy');