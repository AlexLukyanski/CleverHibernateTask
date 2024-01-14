CREATE DATABASE "HibernateTaskDB"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
	
	
	
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

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Person"
    OWNER to postgres;
	
	
	
	
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

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.m2m_house_person
    OWNER to postgres;