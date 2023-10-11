![Lifehack](./src/main/resources/public/images/lifehack.png)

# Life hack website - fall 2023

This website is a collection of life hacks that we have found useful. 

We hope you find them useful too!

```sql
-- Database: lifehack

-- DROP DATABASE IF EXISTS lifehack;

CREATE DATABASE lifehack
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default 
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;


```

```sql
-- SEQUENCE: public.user_id_seq

-- DROP SEQUENCE IF EXISTS public.user_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.user_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY "user".id;

ALTER SEQUENCE public.user_id_seq
    OWNER TO postgres;

```

```sql
-- Table: public.user

-- DROP TABLE IF EXISTS public."user";

CREATE TABLE IF NOT EXISTS public."user"
(
    id integer NOT NULL DEFAULT nextval('user_id_seq'::regclass),
    name character varying(250) COLLATE pg_catalog."default" NOT NULL,
    password character varying(250) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."user"
    OWNER to postgres;
```