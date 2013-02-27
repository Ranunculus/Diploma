DROP TABLE Categories;
DROP TABLE Classifiers;
DROP TABLE Dictionaries;
DROP TABLE Links;
DROP TABLE Word_Categories;
DROP TABLE Word_Classifier;
DROP TABLE Word_Dictionary;
DROP TABLE Words;

PRAGMA foreign_key = ON;

CREATE TABLE Categories(
	id INTEGER primary key,
	category varchar(50)
);
CREATE TABLE Classifiers(
	id INTEGER primary key,
	classifier varchar(50)
);
CREATE TABLE Dictionaries(
	id INTEGER primary key,
	name varchar(255)
);

CREATE TABLE Links(
	id INTEGER primary key,
	word_1_id int,
	word_2_id int,
	link_type varchar(255),
	FOREIGN KEY (word_1_id) REFERENCES Words(id),
	FOREIGN KEY (word_2_id) REFERENCES Words(id)
);
CREATE TABLE Word_categories(
	id INTEGER primary key,
	word_id int,
	category_id int,
	FOREIGN KEY (word_id) REFERENCES Words(id),
	FOREIGN KEY (category_id) REFERENCES Categories(id)
);
CREATE TABLE Word_classifier(
	id INTEGER primary key not null,
	word_id int,
	classifier_id int,
	 FOREIGN KEY (word_id) REFERENCES Words(id),
	 FOREIGN KEY (classifier_id) REFERENCES Classifiers(id)

);
CREATE TABLE Word_dictionary(
	id INTEGER primary key not null,
	word_id int,
	dictionary_id int,
	FOREIGN KEY (word_id) REFERENCES Words(id),
	FOREIGN KEY (dictionary_id) REFERENCES Dictionaries(id)
);
CREATE TABLE Words (
	id INTEGER primary key,
	word varchar(50),
	language varchar(50),
	pronunciation varchar(50)
);

--Inserting data into dictionaries
INSERT INTO Dictionaries VALUES (1, "HSK 1");
INSERT INTO Dictionaries VALUES (2, "HSK 2");
INSERT INTO Dictionaries VALUES (3, "HSK 3");
INSERT INTO Dictionaries VALUES (4, "HSK 4");
INSERT INTO Dictionaries VALUES (5, "HSK 5");
INSERT INTO Dictionaries VALUES (6, "HSK 6");
