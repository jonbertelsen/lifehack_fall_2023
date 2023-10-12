![Lifehack](./src/main/resources/public/images/lifehack.png)

# Life hack website - fall 2023

This website is a collection of life hacks that we have found useful. 

We hope you find them useful too!

How to create the database from PgAdmin:

```sql
CREATE DATABASE lifehack WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE lifehack OWNER TO postgres;

CREATE TABLE public."user" (
                               id integer NOT NULL,
                               name character varying(250) NOT NULL,
                               password character varying(250) NOT NULL
);

ALTER TABLE public."user" OWNER TO postgres;

CREATE SEQUENCE public.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.user_id_seq OWNER TO postgres;

ALTER SEQUENCE public.user_id_seq OWNED BY public."user".id;

ALTER TABLE ONLY public."user" ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);

INSERT INTO public."user" VALUES (1, 'user', '1234');

SELECT pg_catalog.setval('public.user_id_seq', 1, true);

ALTER TABLE ONLY public."user"
ADD CONSTRAINT user_pkey PRIMARY KEY (id);
```

