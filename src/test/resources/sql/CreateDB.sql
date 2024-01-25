
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


	CREATE TABLE IF NOT EXISTS public."Person"
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