--
-- PostgreSQL database dump
--

-- Dumped from database version 10.3
-- Dumped by pg_dump version 10.3

-- Started on 2018-10-10 21:57:04

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2851 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 16553)
-- Name: address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.address (
    contact_id integer NOT NULL,
    country character varying,
    region character varying,
    locality character varying,
    postcode integer,
    removed boolean NOT NULL
);


ALTER TABLE public.address OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16700)
-- Name: attachment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.attachment (
    id integer NOT NULL,
    contact_id integer NOT NULL,
    name character varying NOT NULL,
    uploaded date NOT NULL,
    path character varying NOT NULL,
    comment character varying
);


ALTER TABLE public.attachment OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16698)
-- Name: attachment2_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.attachment2_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.attachment2_id_seq OWNER TO postgres;

--
-- TOC entry 2852 (class 0 OID 0)
-- Dependencies: 202
-- Name: attachment2_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.attachment2_id_seq OWNED BY public.attachment.id;


--
-- TOC entry 199 (class 1259 OID 16614)
-- Name: avatar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.avatar (
    contact_id integer NOT NULL,
    path character varying
);


ALTER TABLE public.avatar OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16577)
-- Name: contact; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contact (
    id integer NOT NULL,
    name character varying NOT NULL,
    surname character varying NOT NULL,
    patronymic character varying,
    sex boolean,
    birth date,
    nationality character varying,
    marital_status character varying,
    website character varying,
    email character varying,
    workplace character varying,
    removed boolean NOT NULL
);


ALTER TABLE public.contact OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 16575)
-- Name: contact_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.contact_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contact_id_seq OWNER TO postgres;

--
-- TOC entry 2853 (class 0 OID 0)
-- Dependencies: 197
-- Name: contact_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.contact_id_seq OWNED BY public.contact.id;


--
-- TOC entry 201 (class 1259 OID 16683)
-- Name: phone_number; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.phone_number (
    id integer NOT NULL,
    contact_id integer NOT NULL,
    area_code integer NOT NULL,
    operator_code integer NOT NULL,
    number integer NOT NULL,
    type boolean,
    comment character varying
);


ALTER TABLE public.phone_number OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16681)
-- Name: phone_number_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.phone_number_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.phone_number_id_seq OWNER TO postgres;

--
-- TOC entry 2854 (class 0 OID 0)
-- Dependencies: 200
-- Name: phone_number_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.phone_number_id_seq OWNED BY public.phone_number.id;


--
-- TOC entry 2697 (class 2604 OID 16703)
-- Name: attachment id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attachment ALTER COLUMN id SET DEFAULT nextval('public.attachment2_id_seq'::regclass);


--
-- TOC entry 2695 (class 2604 OID 16580)
-- Name: contact id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contact ALTER COLUMN id SET DEFAULT nextval('public.contact_id_seq'::regclass);


--
-- TOC entry 2696 (class 2604 OID 16686)
-- Name: phone_number id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.phone_number ALTER COLUMN id SET DEFAULT nextval('public.phone_number_id_seq'::regclass);


--
-- TOC entry 2836 (class 0 OID 16553)
-- Dependencies: 196
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.address VALUES (52, '', '', '', NULL, false);
INSERT INTO public.address VALUES (4, 'BEL', 'Minsk', 'Somest 12', 1234567, false);
INSERT INTO public.address VALUES (35, NULL, NULL, NULL, NULL, false);
INSERT INTO public.address VALUES (3, 'BEL', 'Minsk', 'Novaye 10', 1234567, false);
INSERT INTO public.address VALUES (26, 'in', 'some', 'region', 12345, false);
INSERT INTO public.address VALUES (44, NULL, NULL, NULL, NULL, false);
INSERT INTO public.address VALUES (45, NULL, NULL, NULL, NULL, false);
INSERT INTO public.address VALUES (46, NULL, NULL, NULL, NULL, false);
INSERT INTO public.address VALUES (47, NULL, NULL, NULL, NULL, false);
INSERT INTO public.address VALUES (48, NULL, NULL, NULL, NULL, false);
INSERT INTO public.address VALUES (49, NULL, NULL, NULL, NULL, false);
INSERT INTO public.address VALUES (51, '', '', '', NULL, false);
INSERT INTO public.address VALUES (2, 'BEL', 'Minsk', 'Land 10298', 123456, false);
INSERT INTO public.address VALUES (5, 'BEL', 'Minsk', 'Derevnya 10', 1234567, false);
INSERT INTO public.address VALUES (50, '', '', '', 123, false);
INSERT INTO public.address VALUES (36, '', '', '', NULL, true);
INSERT INTO public.address VALUES (37, NULL, NULL, NULL, NULL, true);
INSERT INTO public.address VALUES (1, 'BEL', 'Minsk', '', 1234567, false);
INSERT INTO public.address VALUES (38, '', '', '', NULL, true);
INSERT INTO public.address VALUES (60, 'Japan', 'chinjiao', 'hakian st 2892', 2948623, false);
INSERT INTO public.address VALUES (39, NULL, NULL, NULL, NULL, true);
INSERT INTO public.address VALUES (61, '', '', '', NULL, false);
INSERT INTO public.address VALUES (40, NULL, NULL, NULL, NULL, true);
INSERT INTO public.address VALUES (41, NULL, NULL, NULL, NULL, true);
INSERT INTO public.address VALUES (42, NULL, NULL, NULL, NULL, true);
INSERT INTO public.address VALUES (43, NULL, NULL, NULL, NULL, true);


--
-- TOC entry 2843 (class 0 OID 16700)
-- Dependencies: 203
-- Data for Name: attachment; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.attachment VALUES (2, 1, 'File2', '2018-04-05', '/path2', NULL);
INSERT INTO public.attachment VALUES (1, 1, 'Иван', '2018-03-04', '/somepath', 'Some comment2');
INSERT INTO public.attachment VALUES (8, 1, '123', '2018-09-30', 'files/attachments/2_5310057965728825594.pdf', 'Коммент');
INSERT INTO public.attachment VALUES (9, 52, 'методка2', '2018-09-30', 'files/attachments/2_5310057965728825594.pdf', '');
INSERT INTO public.attachment VALUES (11, 2, 'image', '2018-10-01', 'files/attachments/dOcWU9po-RU.jpg', '');
INSERT INTO public.attachment VALUES (13, 60, 'some picture', '2018-10-10', '{"timestamp":"2018-10-10T18:27:38.632+0000","status":500,"error":"Internal Server Error","message":"Maximum upload size exceeded; nested exception is java.lang.IllegalStateException: org.apache.tomcat.util.http.fileupload.FileUploadBase$FileSizeLimitExceededException: The field file exceeds its maximum permitted size of 5242880 bytes.","path":"/attachment/upload"}', 'pic');
INSERT INTO public.attachment VALUES (14, 61, 'картинка', '2018-10-10', 'files/attachments/life code.png', '');


--
-- TOC entry 2839 (class 0 OID 16614)
-- Dependencies: 199
-- Data for Name: avatar; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.avatar VALUES (52, 'files/avatars/r2GyUhveOuQ.png');
INSERT INTO public.avatar VALUES (2, 'files/avatars/zZN6YYJNrvA.jpg');
INSERT INTO public.avatar VALUES (3, 'files/avatars/zZN6YYJNrvA.jpg');
INSERT INTO public.avatar VALUES (1, 'files/avatars/r2GyUhveOuQ.png');


--
-- TOC entry 2838 (class 0 OID 16577)
-- Dependencies: 198
-- Data for Name: contact; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.contact VALUES (50, 'Another Name', 'One', 'Guy Dude', false, '2018-09-15', '', '', '', '', '', false);
INSERT INTO public.contact VALUES (1, 'Иван', 'Ivanov', 'Алексеев', true, '2000-06-06', 'BEL', 'married', 'somesite.com', 'somemail@gmail.com', 'Google', false);
INSERT INTO public.contact VALUES (53, 'Some', 'Name', 'Lala', true, '2010-02-02', 'BEL', 'single', 'website.com', 'web@site.com', 'work inc.', false);
INSERT INTO public.contact VALUES (54, 'Some', 'Name', 'Lala', true, '2010-02-02', 'BEL', 'single', 'website.com', 'web@site.com', 'work inc.', false);
INSERT INTO public.contact VALUES (55, 'Some', 'Name', 'Lala', true, '2010-02-02', 'BEL', 'single', 'website.com', 'web@site.com', 'work inc.', false);
INSERT INTO public.contact VALUES (51, 'Ваня', 'Дорофеев', '', NULL, NULL, '', '', '', '', '', false);
INSERT INTO public.contact VALUES (56, 'Some', 'Name', 'Lala', true, '2010-02-02', 'BEL', 'single', 'website.com', 'web@site.com', 'work inc.', false);
INSERT INTO public.contact VALUES (36, 'Another', 'One', 'Guy', true, NULL, '', '', '', '', '', true);
INSERT INTO public.contact VALUES (37, 'Another', 'One', 'Guy', true, NULL, NULL, NULL, NULL, NULL, NULL, true);
INSERT INTO public.contact VALUES (38, 'Another', 'One', 'Guy', true, NULL, '', '', '', '', '', true);
INSERT INTO public.contact VALUES (39, 'Another', 'One', 'Guy', true, NULL, NULL, NULL, NULL, NULL, NULL, true);
INSERT INTO public.contact VALUES (40, 'Another', 'One', 'Guy', true, NULL, NULL, NULL, NULL, NULL, NULL, true);
INSERT INTO public.contact VALUES (41, 'Another', 'One', 'Guy', true, NULL, NULL, NULL, NULL, NULL, NULL, true);
INSERT INTO public.contact VALUES (42, 'Another', 'One', 'Guy', true, NULL, NULL, NULL, NULL, NULL, NULL, true);
INSERT INTO public.contact VALUES (43, 'Another', 'One', 'Guy', true, NULL, NULL, NULL, NULL, NULL, NULL, true);
INSERT INTO public.contact VALUES (57, 'Some', 'Name', 'Lala', true, '2010-02-02', 'BEL', 'single', 'website.com', 'web@site.com', 'work inc.', false);
INSERT INTO public.contact VALUES (58, 'Some', 'Name', 'Lala', true, '2010-02-02', 'BEL', 'single', 'website.com', 'web@site.com', 'work inc.', false);
INSERT INTO public.contact VALUES (59, 'Some', 'Name', 'Lala', true, '2010-02-02', 'BEL', 'single', 'website.com', 'web@site.com', 'work inc.', false);
INSERT INTO public.contact VALUES (60, 'Some', 'Name', 'Lala', true, '2010-02-02', 'BEL', 'single', 'website.com', 'web@site.com', 'work inc.', false);
INSERT INTO public.contact VALUES (61, 'имя', 'фамилия', '', NULL, NULL, '', '', '', '', '', false);
INSERT INTO public.contact VALUES (4, 'Legit', 'Paren', 'Pat', NULL, '1995-06-16', 'BEL', 'single', NULL, NULL, 'Yahoo', false);
INSERT INTO public.contact VALUES (26, 'some', 'name', 'guy', true, '1990-10-10', 'na', 'ti', 'on', 'nal', NULL, false);
INSERT INTO public.contact VALUES (5, 'Patric', 'Enderson', '', NULL, '2007-10-20', 'BEL', 'single', '', '', 'MTZ', false);
INSERT INTO public.contact VALUES (52, 'Никита', 'Петухов', '', NULL, NULL, '', '', '', '', '', false);
INSERT INTO public.contact VALUES (35, 'Name', 'Surname', NULL, true, NULL, 'RUS', NULL, NULL, NULL, 'Some', false);
INSERT INTO public.contact VALUES (44, 'Another', 'One', 'Guy', true, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO public.contact VALUES (45, 'Another', 'One', 'Guy', true, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO public.contact VALUES (46, 'Another', 'One', 'Guy', true, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO public.contact VALUES (47, 'Another', 'One', 'Guy', true, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO public.contact VALUES (48, 'Another', 'One', 'Guy', true, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO public.contact VALUES (49, 'Another', 'One', 'Guy', true, NULL, NULL, NULL, NULL, NULL, NULL, false);
INSERT INTO public.contact VALUES (3, 'Some', 'Name', 'Guy', NULL, '2018-09-17', 'BEL', 'single', '', '', 'Blala', false);
INSERT INTO public.contact VALUES (2, 'Aleg', 'Alexeev', 'Hoho', true, '2000-06-22', 'BEL', 'single', 'site.com', 'somemail@com', 'Yandex', false);


--
-- TOC entry 2841 (class 0 OID 16683)
-- Dependencies: 201
-- Data for Name: phone_number; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.phone_number VALUES (2, 1, 333, 44, 1234568, false, 'Some comment');
INSERT INTO public.phone_number VALUES (3, 1, 345, 12, 9876433, false, 'Comment');
INSERT INTO public.phone_number VALUES (7, 51, 123, 23, 3211231, true, '');
INSERT INTO public.phone_number VALUES (8, 52, 123, 32, 1231233, NULL, '');
INSERT INTO public.phone_number VALUES (10, 2, 123, 32, 1231231, NULL, '');
INSERT INTO public.phone_number VALUES (12, 53, 763, 37, 8763120, true, 'Some phone');
INSERT INTO public.phone_number VALUES (13, 53, 123, 87, 5675657, NULL, '');
INSERT INTO public.phone_number VALUES (15, 54, 763, 37, 8763120, true, 'Some phone');
INSERT INTO public.phone_number VALUES (14, 54, 123, 87, 5675657, NULL, '');
INSERT INTO public.phone_number VALUES (16, 55, 123, 87, 5675657, NULL, '');
INSERT INTO public.phone_number VALUES (17, 55, 763, 37, 8763120, true, 'Some phone');
INSERT INTO public.phone_number VALUES (18, 56, 763, 37, 8763120, true, 'Some phone');
INSERT INTO public.phone_number VALUES (19, 56, 123, 87, 5675657, NULL, '');
INSERT INTO public.phone_number VALUES (20, 57, 763, 37, 8763120, true, 'Some phone');
INSERT INTO public.phone_number VALUES (21, 57, 123, 87, 5675657, NULL, '');
INSERT INTO public.phone_number VALUES (22, 58, 763, 37, 8763120, true, 'Some phone');
INSERT INTO public.phone_number VALUES (23, 58, 123, 87, 5675657, NULL, '');
INSERT INTO public.phone_number VALUES (24, 59, 123, 87, 5675657, NULL, '');
INSERT INTO public.phone_number VALUES (25, 59, 763, 37, 8763120, true, 'Some phone');
INSERT INTO public.phone_number VALUES (26, 60, 763, 37, 8763120, true, 'Some phone');
INSERT INTO public.phone_number VALUES (27, 60, 123, 87, 5675657, NULL, '');


--
-- TOC entry 2855 (class 0 OID 0)
-- Dependencies: 202
-- Name: attachment2_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.attachment2_id_seq', 14, true);


--
-- TOC entry 2856 (class 0 OID 0)
-- Dependencies: 197
-- Name: contact_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.contact_id_seq', 61, true);


--
-- TOC entry 2857 (class 0 OID 0)
-- Dependencies: 200
-- Name: phone_number_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.phone_number_id_seq', 27, true);


--
-- TOC entry 2700 (class 2606 OID 16636)
-- Name: address address_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pk PRIMARY KEY (contact_id);


--
-- TOC entry 2710 (class 2606 OID 16708)
-- Name: attachment attachment2_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attachment
    ADD CONSTRAINT attachment2_pkey PRIMARY KEY (id);


--
-- TOC entry 2703 (class 2606 OID 16585)
-- Name: contact contact_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contact
    ADD CONSTRAINT contact_pkey PRIMARY KEY (id);


--
-- TOC entry 2707 (class 2606 OID 16691)
-- Name: phone_number phone_number_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.phone_number
    ADD CONSTRAINT phone_number_pkey PRIMARY KEY (id);


--
-- TOC entry 2698 (class 1259 OID 16634)
-- Name: address_contact_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX address_contact_id_uindex ON public.address USING btree (contact_id);


--
-- TOC entry 2708 (class 1259 OID 16714)
-- Name: attachment2_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX attachment2_id_uindex ON public.attachment USING btree (id);


--
-- TOC entry 2704 (class 1259 OID 16620)
-- Name: avatar_contact_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX avatar_contact_id_uindex ON public.avatar USING btree (contact_id);


--
-- TOC entry 2701 (class 1259 OID 16586)
-- Name: contact_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX contact_id_uindex ON public.contact USING btree (id);


--
-- TOC entry 2705 (class 1259 OID 16697)
-- Name: phone_number_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX phone_number_id_uindex ON public.phone_number USING btree (id);


--
-- TOC entry 2711 (class 2606 OID 16592)
-- Name: address address_contact_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_contact_id_fk FOREIGN KEY (contact_id) REFERENCES public.contact(id);


--
-- TOC entry 2714 (class 2606 OID 16709)
-- Name: attachment attachment2_contact_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attachment
    ADD CONSTRAINT attachment2_contact_id_fk FOREIGN KEY (contact_id) REFERENCES public.contact(id);


--
-- TOC entry 2712 (class 2606 OID 16621)
-- Name: avatar avatar_contact_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.avatar
    ADD CONSTRAINT avatar_contact_id_fk FOREIGN KEY (contact_id) REFERENCES public.contact(id);


--
-- TOC entry 2713 (class 2606 OID 16692)
-- Name: phone_number phone_number_contact_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.phone_number
    ADD CONSTRAINT phone_number_contact_id_fk FOREIGN KEY (contact_id) REFERENCES public.contact(id);


-- Completed on 2018-10-10 21:57:04

--
-- PostgreSQL database dump complete
--

