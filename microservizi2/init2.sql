--
-- PostgreSQL database dump
--

-- Dumped from database version 11.11
-- Dumped by pg_dump version 11.11

-- Started on 2021-12-15 11:33:42

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 17804)
-- Name: business_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.business_user (
    id integer NOT NULL,
    firstname character varying,
    lastname character varying,
    fiscalcode character varying
);


ALTER TABLE public.business_user OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 17802)
-- Name: business_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.business_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.business_user_id_seq OWNER TO postgres;

--
-- TOC entry 2902 (class 0 OID 0)
-- Dependencies: 196
-- Name: business_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.business_user_id_seq OWNED BY public.business_user.id;


--
-- TOC entry 202 (class 1259 OID 17840)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer NOT NULL,
    name character varying,
    date date
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 17838)
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq OWNER TO postgres;

--
-- TOC entry 2903 (class 0 OID 0)
-- Dependencies: 201
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- TOC entry 199 (class 1259 OID 17815)
-- Name: sim; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sim (
    id integer NOT NULL,
    msisdn character varying,
    imsi character varying,
    business_user_id integer
);


ALTER TABLE public.sim OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 17813)
-- Name: sim_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sim_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sim_id_seq OWNER TO postgres;

--
-- TOC entry 2905 (class 0 OID 0)
-- Dependencies: 198
-- Name: sim_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sim_id_seq OWNED BY public.sim.id;


--
-- TOC entry 200 (class 1259 OID 17833)
-- Name: sim_product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sim_product (
    sim_id integer NOT NULL,
    product_id integer NOT NULL
);


ALTER TABLE public.sim_product OWNER TO postgres;


--
-- TOC entry 204 (class 1259 OID 17861)
-- Name: validation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.validation (
    id integer NOT NULL,
    date timestamp with time zone,
    business_user_id integer
);


ALTER TABLE public.validation OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 17859)
-- Name: validation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.validation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.validation_id_seq OWNER TO postgres;

--
-- TOC entry 2907 (class 0 OID 0)
-- Dependencies: 203
-- Name: validation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.validation_id_seq OWNED BY public.validation.id;


--
-- TOC entry 2728 (class 2604 OID 17807)
-- Name: business_user id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.business_user ALTER COLUMN id SET DEFAULT nextval('public.business_user_id_seq'::regclass);


--
-- TOC entry 2730 (class 2604 OID 17843)
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- TOC entry 2729 (class 2604 OID 17818)
-- Name: sim id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sim ALTER COLUMN id SET DEFAULT nextval('public.sim_id_seq'::regclass);


--
-- TOC entry 2731 (class 2604 OID 17864)
-- Name: validation id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.validation ALTER COLUMN id SET DEFAULT nextval('public.validation_id_seq'::regclass);


--
-- TOC entry 2884 (class 0 OID 17804)
-- Dependencies: 197
-- Data for Name: business_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.business_user (id, firstname, lastname, fiscalcode) FROM stdin;
1	Mario	Rossi	12345
10	Luigi	Bianchi	089735
11	Laura	Verdi	127890
13	Ludovico	Ariosto	QWERT
14	John	Smith	2345789
15	Pippo	Pippo	235780
16	Pluto	Pluti	ASDFGHJ
\.


--
-- TOC entry 2889 (class 0 OID 17840)
-- Dependencies: 202
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product (id, name, date) FROM stdin;
1	GIGA 1000	2021-07-22
2	GIGA EXTRA	2021-07-23
3	TEST	2021-07-23
\.


--
-- TOC entry 2886 (class 0 OID 17815)
-- Dependencies: 199
-- Data for Name: sim; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sim (id, msisdn, imsi, business_user_id) FROM stdin;
1	34567890	1234567	10
2	11111	111111	10
3	431234	981230000	\N
5	33333	555555	15
7	333338	555558	14
\.


--
-- TOC entry 2887 (class 0 OID 17833)
-- Dependencies: 200
-- Data for Name: sim_product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sim_product (sim_id, product_id) FROM stdin;
1	1
\.


--
-- TOC entry 2891 (class 0 OID 17861)
-- Dependencies: 204
-- Data for Name: validation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.validation (id, date, business_user_id) FROM stdin;
2	2021-07-29 00:00:00+02	\N
\.


--
-- TOC entry 2908 (class 0 OID 0)
-- Dependencies: 196
-- Name: business_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.business_user_id_seq', 45, true);


--
-- TOC entry 2909 (class 0 OID 0)
-- Dependencies: 201
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 3, true);


--
-- TOC entry 2911 (class 0 OID 0)
-- Dependencies: 198
-- Name: sim_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sim_id_seq', 7, true);


--
-- TOC entry 2913 (class 0 OID 0)
-- Dependencies: 203
-- Name: validation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.validation_id_seq', 2, true);


--
-- TOC entry 2747 (class 2606 OID 17876)
-- Name: validation business_user_id_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.validation
    ADD CONSTRAINT business_user_id_unique UNIQUE (business_user_id);


--
-- TOC entry 2735 (class 2606 OID 17812)
-- Name: business_user business_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.business_user
    ADD CONSTRAINT business_user_pkey PRIMARY KEY (id);


--
-- TOC entry 2737 (class 2606 OID 17827)
-- Name: sim imsi_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sim
    ADD CONSTRAINT imsi_unique UNIQUE (imsi) INCLUDE (imsi);


--
-- TOC entry 2739 (class 2606 OID 17825)
-- Name: sim msisdn_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sim
    ADD CONSTRAINT msisdn_unique UNIQUE (msisdn) INCLUDE (msisdn);


--
-- TOC entry 2745 (class 2606 OID 17848)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 2741 (class 2606 OID 17823)
-- Name: sim sim_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sim
    ADD CONSTRAINT sim_pkey PRIMARY KEY (id);


--
-- TOC entry 2743 (class 2606 OID 17837)
-- Name: sim_product sim_product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sim_product
    ADD CONSTRAINT sim_product_pkey PRIMARY KEY (sim_id, product_id);

--
-- TOC entry 2749 (class 2606 OID 17866)
-- Name: validation validation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.validation
    ADD CONSTRAINT validation_pkey PRIMARY KEY (id);


--
-- TOC entry 2759 (class 2606 OID 17877)
-- Name: validation business_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.validation
    ADD CONSTRAINT business_user_fk FOREIGN KEY (business_user_id) REFERENCES public.business_user(id) NOT VALID;


--
-- TOC entry 2756 (class 2606 OID 17828)
-- Name: sim business_user_foreign_key; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sim
    ADD CONSTRAINT business_user_foreign_key FOREIGN KEY (business_user_id) REFERENCES public.business_user(id);


--
-- TOC entry 2758 (class 2606 OID 17854)
-- Name: sim_product product_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sim_product
    ADD CONSTRAINT product_fk FOREIGN KEY (product_id) REFERENCES public.product(id) NOT VALID;


--
-- TOC entry 2757 (class 2606 OID 17849)
-- Name: sim_product sim_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sim_product
    ADD CONSTRAINT sim_fk FOREIGN KEY (sim_id) REFERENCES public.sim(id) NOT VALID;


-- Completed on 2021-12-15 11:33:42

--
-- PostgreSQL database dump complete
--


