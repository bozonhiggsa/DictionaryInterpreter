DROP TABLE IF EXISTS postgres.public.third_table CASCADE;
DROP TABLE IF EXISTS postgres.public.english_words CASCADE;
DROP TABLE IF EXISTS postgres.public.russian_words CASCADE;

CREATE TABLE postgres.public.english_words
(
     id serial NOT NULL,
     word character varying(200) NOT NULL,
     mark BOOLEAN,
     done BOOLEAN,
     CONSTRAINT id PRIMARY KEY (id),
     CONSTRAINT "wordEng_UNIQUE" UNIQUE (word)
)
WITH (
OIDS = FALSE
)
;

CREATE TABLE postgres.public.russian_words
(
     id serial NOT NULL,
     word character varying(200) NOT NULL,
     CONSTRAINT id2 PRIMARY KEY (id),
     CONSTRAINT "wordRus_UNIQUE" UNIQUE (word)
)
WITH (
OIDS = FALSE
)
;

CREATE TABLE postgres.public.third_table
(
     id_eng serial NOT NULL,
     id_rus serial NOT NULL,
     CONSTRAINT id_eng FOREIGN KEY (id_eng) REFERENCES postgres.public.english_words (id) ON UPDATE CASCADE ON DELETE CASCADE,
     CONSTRAINT id_rus FOREIGN KEY (id_rus) REFERENCES postgres.public.russian_words (id) ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
OIDS = FALSE
)
;