--liquibase formatted sql

--changeset alexlukyanski:1
CREATE TABLE IF NOT EXISTS public."House"
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    uuid character varying COLLATE pg_catalog."default" NOT NULL DEFAULT gen_random_uuid(),
    area numeric NOT NULL,
    country character varying COLLATE pg_catalog."default" NOT NULL,
    city character varying COLLATE pg_catalog."default" NOT NULL,
    street character varying COLLATE pg_catalog."default" NOT NULL,
    "number" character varying COLLATE pg_catalog."default" NOT NULL,
    create_date timestamp without time zone NOT NULL,
    CONSTRAINT "House_pkey" PRIMARY KEY (id),
    CONSTRAINT uuid_unique UNIQUE (uuid)
)


--changeset alexlukyanski:2
	CREATE TABLE public."Person"
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY,
    uuid character varying NOT NULL DEFAULT gen_random_uuid(),
    name character varying NOT NULL,
    surname character varying NOT NULL,
    sex character varying NOT NULL,
    passport_series character varying NOT NULL,
    passport_number character varying NOT NULL,
    create_date timestamp without time zone NOT NULL,
    update_date timestamp without time zone,
    home integer NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT unique_uuid_p UNIQUE (uuid),
    CONSTRAINT unique_passport UNIQUE (passport_number, passport_series),
    CONSTRAINT fk_house FOREIGN KEY (home)
        REFERENCES public."House" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)


--changeset alexlukyanski:3
CREATE TABLE IF NOT EXISTS public.m2m_house_person
(
    house integer NOT NULL,
    person integer NOT NULL,
    CONSTRAINT m2m_house_person_pkey PRIMARY KEY (house, person),
    CONSTRAINT m2m_house_fk FOREIGN KEY (house)
        REFERENCES public."House" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT m2m_person_fk FOREIGN KEY (person)
        REFERENCES public."Person" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)


--changeset alexlukyanski:4
INSERT into public."House" (area,country,city,street,number,create_date)
VALUES (77, 'Belarus','Mogilev','First',11,'2021-09-11 11:01:23');
INSERT into public."House" (area, country, city,street,number,create_date)
VALUES (41, 'Belarus','Brest','Second',5,'2022-02-02 02:02:02');
INSERT into public."House" (area, country, city,street,number,create_date)
VALUES (54, 'Belarus','Minsk','Third',17, '2023-03-03 03:03:53');
INSERT into public."House" (area, country, city,street,number,create_date)
VALUES (43, 'Belarus','Grodno','Fourth',22, '2022-04-04 04:04:44');
INSERT into public."House" (area, country, city,street,number,create_date)
VALUES (29, 'Belarus','Gomel','Fifth',34, '2021-05-05 05:05:55');


--changeset alexlukyanski:5
INSERT into public."Person" (name,surname,sex,passport_series,passport_number,create_date,update_date, home)
VALUES ('Alex', 'THE','MALE','GHVJV','55KJGJK99441', '2023-10-19 10:23:54', '2023-10-19 10:23:54', 1);
INSERT into public."Person" (name,surname,sex,passport_series,passport_number,create_date,update_date, home)
VALUES ('Dima', 'Second','MALE','HDVCB','LVUVUBIIHO', '2024-01-01 08:01:51', '2024-01-01 08:01:51', 1);
INSERT into public."Person" ("name",surname,sex,passport_series,passport_number,create_date,update_date, home)
VALUES ('Vera', 'Third','FEMALE','KKBNM','45216DSSVA', '2020-02-02 07:01:51', '2020-02-02 07:01:51',1);
INSERT into public."Person" ("name",surname,sex,passport_series,passport_number,create_date,update_date, home)
VALUES ('Sveta', 'Forth','FEMALE','KHLMV','LLJNOLKB49B', '2021-04-03 12:11:40', '2021-04-03 12:11:40',3);
INSERT into public."Person" ("name",surname,sex,passport_series,passport_number,create_date,update_date, home)
VALUES ('Artem', 'Fifth','MALE','PHNSL','52DBTECE53', '2023-03-03 03:03:53', '2023-03-03 03:03:53', 3);
INSERT into public."Person" ("name",surname,sex,passport_series,passport_number,create_date,update_date, home)
VALUES ('Vitya', 'Sixth','MALE','KGGUG','0OJHUYVUTFU', '2023-05-05 05:05:55', '2023-05-05 05:05:55', 3);
INSERT into public."Person" ("name",surname,sex,passport_series,passport_number,create_date,update_date, home)
VALUES ('Dima', 'Seventh','MALE','PPPPP','11111111111', '2023-11-11 11:11:01', '2023-11-11 11:11:01', 1);
INSERT into public."Person" ("name",surname,sex,passport_series,passport_number,create_date,update_date, home)
VALUES ('Tamara', 'Eightth','FEMALE','BBBBB','3333333333O', '2023-12-12 12:12:12', '2023-12-12 12:12:12', 5);
INSERT into public."Person" ("name",surname,sex,passport_series,passport_number,create_date,update_date, home)
VALUES ('Konstantin', 'Ninth','MALE','ZXZXZ','KKKHG888888', '2023-07-07 07:07:57', '2023-07-07 07:07:57', 5);
INSERT into public."Person" ("name",surname,sex,passport_series,passport_number,create_date,update_date, home)
VALUES ('Tamara', 'Tenth','FEMALE','HKKKK','LIJIGUV445IP', '2023-04-04 04:04:14', '2023-04-04 04:04:14', 1);

--changeset alexlukyanski:6
INSERT into public.m2m_house_person (house,person)
VALUES (2,5);
INSERT into public.m2m_house_person (house,person)
VALUES (3,1);
INSERT into public.m2m_house_person (house,person)
VALUES (5,5);
INSERT into public.m2m_house_person (house,person)
VALUES (5,4);