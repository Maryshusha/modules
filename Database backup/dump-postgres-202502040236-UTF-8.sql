--
-- PostgreSQL database dump
--

-- Dumped from database version 17.1
-- Dumped by pg_dump version 17.1

-- Started on 2025-02-04 02:36:58

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 24591)
-- Name: thirst; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA thirst;


ALTER SCHEMA thirst OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 234 (class 1259 OID 24725)
-- Name: history_sale; Type: TABLE; Schema: thirst; Owner: postgres
--

CREATE TABLE thirst.history_sale (
    history_sale_id integer NOT NULL,
    partners_id integer,
    product_id integer,
    sale_date date NOT NULL,
    product_count integer NOT NULL
);


ALTER TABLE thirst.history_sale OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 24724)
-- Name: history_sale_history_sale_id_seq; Type: SEQUENCE; Schema: thirst; Owner: postgres
--

CREATE SEQUENCE thirst.history_sale_history_sale_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE thirst.history_sale_history_sale_id_seq OWNER TO postgres;

--
-- TOC entry 5049 (class 0 OID 0)
-- Dependencies: 233
-- Name: history_sale_history_sale_id_seq; Type: SEQUENCE OWNED BY; Schema: thirst; Owner: postgres
--

ALTER SEQUENCE thirst.history_sale_history_sale_id_seq OWNED BY thirst.history_sale.history_sale_id;


--
-- TOC entry 224 (class 1259 OID 24672)
-- Name: material_type; Type: TABLE; Schema: thirst; Owner: postgres
--

CREATE TABLE thirst.material_type (
    material_id integer NOT NULL,
    material_type text NOT NULL,
    percent_defect text NOT NULL
);


ALTER TABLE thirst.material_type OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 24671)
-- Name: material_type_material_id_seq; Type: SEQUENCE; Schema: thirst; Owner: postgres
--

CREATE SEQUENCE thirst.material_type_material_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE thirst.material_type_material_id_seq OWNER TO postgres;

--
-- TOC entry 5050 (class 0 OID 0)
-- Dependencies: 223
-- Name: material_type_material_id_seq; Type: SEQUENCE OWNED BY; Schema: thirst; Owner: postgres
--

ALTER SEQUENCE thirst.material_type_material_id_seq OWNED BY thirst.material_type.material_id;


--
-- TOC entry 230 (class 1259 OID 24697)
-- Name: partners; Type: TABLE; Schema: thirst; Owner: postgres
--

CREATE TABLE thirst.partners (
    partners_id integer NOT NULL,
    partners_type integer,
    partners_title text NOT NULL,
    full_name text NOT NULL,
    partners_email text NOT NULL,
    partners_phone text NOT NULL,
    address_partners text NOT NULL,
    partners_inn bigint NOT NULL,
    partners_rating integer NOT NULL
);


ALTER TABLE thirst.partners OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 24696)
-- Name: partners_partners_id_seq; Type: SEQUENCE; Schema: thirst; Owner: postgres
--

CREATE SEQUENCE thirst.partners_partners_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE thirst.partners_partners_id_seq OWNER TO postgres;

--
-- TOC entry 5051 (class 0 OID 0)
-- Dependencies: 229
-- Name: partners_partners_id_seq; Type: SEQUENCE OWNED BY; Schema: thirst; Owner: postgres
--

ALTER SEQUENCE thirst.partners_partners_id_seq OWNED BY thirst.partners.partners_id;


--
-- TOC entry 228 (class 1259 OID 24690)
-- Name: partners_type; Type: TABLE; Schema: thirst; Owner: postgres
--

CREATE TABLE thirst.partners_type (
    partners_type_id integer NOT NULL,
    partners_title character varying(100) NOT NULL
);


ALTER TABLE thirst.partners_type OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 24689)
-- Name: partners_type_partners_type_id_seq; Type: SEQUENCE; Schema: thirst; Owner: postgres
--

CREATE SEQUENCE thirst.partners_type_partners_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE thirst.partners_type_partners_type_id_seq OWNER TO postgres;

--
-- TOC entry 5052 (class 0 OID 0)
-- Dependencies: 227
-- Name: partners_type_partners_type_id_seq; Type: SEQUENCE OWNED BY; Schema: thirst; Owner: postgres
--

ALTER SEQUENCE thirst.partners_type_partners_type_id_seq OWNED BY thirst.partners_type.partners_type_id;


--
-- TOC entry 226 (class 1259 OID 24681)
-- Name: product_type; Type: TABLE; Schema: thirst; Owner: postgres
--

CREATE TABLE thirst.product_type (
    product_type_id integer NOT NULL,
    product_type text NOT NULL,
    product_ratio double precision NOT NULL
);


ALTER TABLE thirst.product_type OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 24680)
-- Name: product_type_product_type_id_seq; Type: SEQUENCE; Schema: thirst; Owner: postgres
--

CREATE SEQUENCE thirst.product_type_product_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE thirst.product_type_product_type_id_seq OWNER TO postgres;

--
-- TOC entry 5053 (class 0 OID 0)
-- Dependencies: 225
-- Name: product_type_product_type_id_seq; Type: SEQUENCE OWNED BY; Schema: thirst; Owner: postgres
--

ALTER SEQUENCE thirst.product_type_product_type_id_seq OWNED BY thirst.product_type.product_type_id;


--
-- TOC entry 232 (class 1259 OID 24711)
-- Name: products; Type: TABLE; Schema: thirst; Owner: postgres
--

CREATE TABLE thirst.products (
    product_id integer NOT NULL,
    product_type integer,
    product_title text NOT NULL,
    product_article bigint NOT NULL,
    product_min_cost double precision NOT NULL,
    material_id integer NOT NULL
);


ALTER TABLE thirst.products OWNER TO postgres;

--
-- TOC entry 261 (class 1259 OID 25003)
-- Name: products_material_id_seq; Type: SEQUENCE; Schema: thirst; Owner: postgres
--

CREATE SEQUENCE thirst.products_material_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE thirst.products_material_id_seq OWNER TO postgres;

--
-- TOC entry 5054 (class 0 OID 0)
-- Dependencies: 261
-- Name: products_material_id_seq; Type: SEQUENCE OWNED BY; Schema: thirst; Owner: postgres
--

ALTER SEQUENCE thirst.products_material_id_seq OWNED BY thirst.products.material_id;


--
-- TOC entry 231 (class 1259 OID 24710)
-- Name: products_product_id_seq; Type: SEQUENCE; Schema: thirst; Owner: postgres
--

CREATE SEQUENCE thirst.products_product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE thirst.products_product_id_seq OWNER TO postgres;

--
-- TOC entry 5055 (class 0 OID 0)
-- Dependencies: 231
-- Name: products_product_id_seq; Type: SEQUENCE OWNED BY; Schema: thirst; Owner: postgres
--

ALTER SEQUENCE thirst.products_product_id_seq OWNED BY thirst.products.product_id;


--
-- TOC entry 4868 (class 2604 OID 24728)
-- Name: history_sale history_sale_id; Type: DEFAULT; Schema: thirst; Owner: postgres
--

ALTER TABLE ONLY thirst.history_sale ALTER COLUMN history_sale_id SET DEFAULT nextval('thirst.history_sale_history_sale_id_seq'::regclass);


--
-- TOC entry 4862 (class 2604 OID 24675)
-- Name: material_type material_id; Type: DEFAULT; Schema: thirst; Owner: postgres
--

ALTER TABLE ONLY thirst.material_type ALTER COLUMN material_id SET DEFAULT nextval('thirst.material_type_material_id_seq'::regclass);


--
-- TOC entry 4865 (class 2604 OID 24700)
-- Name: partners partners_id; Type: DEFAULT; Schema: thirst; Owner: postgres
--

ALTER TABLE ONLY thirst.partners ALTER COLUMN partners_id SET DEFAULT nextval('thirst.partners_partners_id_seq'::regclass);


--
-- TOC entry 4864 (class 2604 OID 24693)
-- Name: partners_type partners_type_id; Type: DEFAULT; Schema: thirst; Owner: postgres
--

ALTER TABLE ONLY thirst.partners_type ALTER COLUMN partners_type_id SET DEFAULT nextval('thirst.partners_type_partners_type_id_seq'::regclass);


--
-- TOC entry 4863 (class 2604 OID 24684)
-- Name: product_type product_type_id; Type: DEFAULT; Schema: thirst; Owner: postgres
--

ALTER TABLE ONLY thirst.product_type ALTER COLUMN product_type_id SET DEFAULT nextval('thirst.product_type_product_type_id_seq'::regclass);


--
-- TOC entry 4866 (class 2604 OID 24714)
-- Name: products product_id; Type: DEFAULT; Schema: thirst; Owner: postgres
--

ALTER TABLE ONLY thirst.products ALTER COLUMN product_id SET DEFAULT nextval('thirst.products_product_id_seq'::regclass);


--
-- TOC entry 4867 (class 2604 OID 25004)
-- Name: products material_id; Type: DEFAULT; Schema: thirst; Owner: postgres
--

ALTER TABLE ONLY thirst.products ALTER COLUMN material_id SET DEFAULT nextval('thirst.products_material_id_seq'::regclass);


--
-- TOC entry 5042 (class 0 OID 24725)
-- Dependencies: 234
-- Data for Name: history_sale; Type: TABLE DATA; Schema: thirst; Owner: postgres
--

INSERT INTO thirst.history_sale VALUES (33, 2, 6, '2023-03-23', 15500);
INSERT INTO thirst.history_sale VALUES (34, 2, 8, '2024-06-07', 37400);
INSERT INTO thirst.history_sale VALUES (35, 2, 9, '2023-12-18', 12350);
INSERT INTO thirst.history_sale VALUES (36, 6, 6, '2024-04-15', 35000);
INSERT INTO thirst.history_sale VALUES (37, 6, 7, '2024-06-12', 25000);
INSERT INTO thirst.history_sale VALUES (38, 6, 8, '2023-11-10', 670000);
INSERT INTO thirst.history_sale VALUES (39, 6, 9, '2023-09-19', 50000);
INSERT INTO thirst.history_sale VALUES (40, 2, 6, '2024-07-01', 7550);
INSERT INTO thirst.history_sale VALUES (41, 2, 10, '2023-05-17', 1250);
INSERT INTO thirst.history_sale VALUES (42, 2, 7, '2022-12-02', 35000);
INSERT INTO thirst.history_sale VALUES (43, 2, 9, '2024-06-07', 1000);
INSERT INTO thirst.history_sale VALUES (44, 5, 10, '2024-05-14', 4500);
INSERT INTO thirst.history_sale VALUES (45, 5, 8, '2023-03-20', 59050);
INSERT INTO thirst.history_sale VALUES (46, 5, 9, '2024-03-12', 37200);
INSERT INTO thirst.history_sale VALUES (47, 4, 6, '2023-01-22', 7250);
INSERT INTO thirst.history_sale VALUES (48, 4, 7, '2024-07-05', 2500);


--
-- TOC entry 5032 (class 0 OID 24672)
-- Dependencies: 224
-- Data for Name: material_type; Type: TABLE DATA; Schema: thirst; Owner: postgres
--

INSERT INTO thirst.material_type VALUES (1, 'Material Type 1', '0.001');
INSERT INTO thirst.material_type VALUES (3, 'Material Type 3', '0.0028');
INSERT INTO thirst.material_type VALUES (5, 'Material Type 5', '0.0034');
INSERT INTO thirst.material_type VALUES (4, 'Material Type 4', '0.0055');
INSERT INTO thirst.material_type VALUES (2, 'Material Type 2', '0.095');


--
-- TOC entry 5038 (class 0 OID 24697)
-- Dependencies: 230
-- Data for Name: partners; Type: TABLE DATA; Schema: thirst; Owner: postgres
--

INSERT INTO thirst.partners VALUES (2, 6, 'Base ', 'Ivanova Alexandra ', 'aleksandraivanova@', '493 123 45 69', '15 Lesnaya str., Yurga, ', 2222455, 5);
INSERT INTO thirst.partners VALUES (3, 7, 'Parquet 29', 'Petrov Vasily ', 'vppetrov@', '987 123 56 ', '18 Stroiteley St.,', 333388, 6);
INSERT INTO thirst.partners VALUES (4, 7, 'Stroy', 'Solovyov Andrey Nikolaevich', 'ansolovev@st.ru', '812 223 ', '188910, Leningrad region, Primorsk, 21 Parkovaya St.', 4440391035, 7);
INSERT INTO thirst.partners VALUES (9, 5, 'выв', 'вывы', 'вывы', 'вывы', 'вывы', 1, 1);
INSERT INTO thirst.partners VALUES (6, 5, 'Installation of the Pro', 'Stepanov Stepan Sergeevich', 'stepanov@stepan.ru', '912 888 33 33', '122 Rabochaya str., Stary Oskol, Belgorod region, 309500', 5552431140, 10);
INSERT INTO thirst.partners VALUES (7, 7, 'тест имя', 'тест фио', 'тест почта', 'тест телефон', 'тест адрес', 1231231231, 3);
INSERT INTO thirst.partners VALUES (10, 5, 'kjk', 'kjkj', 'kj', 'kj', 'kjkj', 1, 2323);
INSERT INTO thirst.partners VALUES (8, 6, 'тест1', 'тест1', 'тест1', 'тест1', 'тест1', 2, 5);
INSERT INTO thirst.partners VALUES (5, 5, 'Renovation and finishing', 'Vorobyova Ekaterina Valeryevna', 'ekaterina.vorobeva@ml.ru', '444 222 33 11', '51 Svobody St., Reutov, Moscow region, 143960', 11115208571, 5);


--
-- TOC entry 5036 (class 0 OID 24690)
-- Dependencies: 228
-- Data for Name: partners_type; Type: TABLE DATA; Schema: thirst; Owner: postgres
--

INSERT INTO thirst.partners_type VALUES (5, 'ZAO');
INSERT INTO thirst.partners_type VALUES (6, 'OOO');
INSERT INTO thirst.partners_type VALUES (7, 'PAO');
INSERT INTO thirst.partners_type VALUES (8, 'OAO');


--
-- TOC entry 5034 (class 0 OID 24681)
-- Dependencies: 226
-- Data for Name: product_type; Type: TABLE DATA; Schema: thirst; Owner: postgres
--

INSERT INTO thirst.product_type VALUES (1, 'Laminate flooring', 2.35);
INSERT INTO thirst.product_type VALUES (2, 'Massive board', 5.15);
INSERT INTO thirst.product_type VALUES (3, 'Parquet board', 4.34);
INSERT INTO thirst.product_type VALUES (4, 'Cork coating', 1.5);


--
-- TOC entry 5040 (class 0 OID 24711)
-- Dependencies: 232
-- Data for Name: products; Type: TABLE DATA; Schema: thirst; Owner: postgres
--

INSERT INTO thirst.products VALUES (6, 3, 'Ash dark single-stripe parquet board 14 mm', 8758385, 4456.9, 1);
INSERT INTO thirst.products VALUES (8, 1, 'Laminate Oak smoky white 33 grade 12 mm', 7750282, 1799.33, 3);
INSERT INTO thirst.products VALUES (10, 4, 'Cork floor adhesive coating 32 class 4 mm', 5012543, 5450.59, 5);
INSERT INTO thirst.products VALUES (9, 1, 'Laminate Oak smoky white 33 grade 12 mm', 7028748, 3890.41, 4);
INSERT INTO thirst.products VALUES (7, 3, 'Engineering board Oak French Christmas tree Single strip 12 mm', 8858958, 7330.99, 2);


--
-- TOC entry 5056 (class 0 OID 0)
-- Dependencies: 233
-- Name: history_sale_history_sale_id_seq; Type: SEQUENCE SET; Schema: thirst; Owner: postgres
--

SELECT pg_catalog.setval('thirst.history_sale_history_sale_id_seq', 48, true);


--
-- TOC entry 5057 (class 0 OID 0)
-- Dependencies: 223
-- Name: material_type_material_id_seq; Type: SEQUENCE SET; Schema: thirst; Owner: postgres
--

SELECT pg_catalog.setval('thirst.material_type_material_id_seq', 5, true);


--
-- TOC entry 5058 (class 0 OID 0)
-- Dependencies: 229
-- Name: partners_partners_id_seq; Type: SEQUENCE SET; Schema: thirst; Owner: postgres
--

SELECT pg_catalog.setval('thirst.partners_partners_id_seq', 10, true);


--
-- TOC entry 5059 (class 0 OID 0)
-- Dependencies: 227
-- Name: partners_type_partners_type_id_seq; Type: SEQUENCE SET; Schema: thirst; Owner: postgres
--

SELECT pg_catalog.setval('thirst.partners_type_partners_type_id_seq', 9, true);


--
-- TOC entry 5060 (class 0 OID 0)
-- Dependencies: 225
-- Name: product_type_product_type_id_seq; Type: SEQUENCE SET; Schema: thirst; Owner: postgres
--

SELECT pg_catalog.setval('thirst.product_type_product_type_id_seq', 4, true);


--
-- TOC entry 5061 (class 0 OID 0)
-- Dependencies: 261
-- Name: products_material_id_seq; Type: SEQUENCE SET; Schema: thirst; Owner: postgres
--

SELECT pg_catalog.setval('thirst.products_material_id_seq', 5, true);


--
-- TOC entry 5062 (class 0 OID 0)
-- Dependencies: 231
-- Name: products_product_id_seq; Type: SEQUENCE SET; Schema: thirst; Owner: postgres
--

SELECT pg_catalog.setval('thirst.products_product_id_seq', 10, true);


--
-- TOC entry 4880 (class 2606 OID 24730)
-- Name: history_sale history_sale_pkey; Type: CONSTRAINT; Schema: thirst; Owner: postgres
--

ALTER TABLE ONLY thirst.history_sale
    ADD CONSTRAINT history_sale_pkey PRIMARY KEY (history_sale_id);


--
-- TOC entry 4870 (class 2606 OID 24679)
-- Name: material_type material_type_pkey; Type: CONSTRAINT; Schema: thirst; Owner: postgres
--

ALTER TABLE ONLY thirst.material_type
    ADD CONSTRAINT material_type_pkey PRIMARY KEY (material_id);


--
-- TOC entry 4876 (class 2606 OID 24704)
-- Name: partners partners_pkey; Type: CONSTRAINT; Schema: thirst; Owner: postgres
--

ALTER TABLE ONLY thirst.partners
    ADD CONSTRAINT partners_pkey PRIMARY KEY (partners_id);


--
-- TOC entry 4874 (class 2606 OID 24695)
-- Name: partners_type partners_type_pkey; Type: CONSTRAINT; Schema: thirst; Owner: postgres
--

ALTER TABLE ONLY thirst.partners_type
    ADD CONSTRAINT partners_type_pkey PRIMARY KEY (partners_type_id);


--
-- TOC entry 4872 (class 2606 OID 24688)
-- Name: product_type product_type_pkey; Type: CONSTRAINT; Schema: thirst; Owner: postgres
--

ALTER TABLE ONLY thirst.product_type
    ADD CONSTRAINT product_type_pkey PRIMARY KEY (product_type_id);


--
-- TOC entry 4878 (class 2606 OID 24718)
-- Name: products products_pkey; Type: CONSTRAINT; Schema: thirst; Owner: postgres
--

ALTER TABLE ONLY thirst.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (product_id);


--
-- TOC entry 4884 (class 2606 OID 24731)
-- Name: history_sale history_sale_partners_id_fkey; Type: FK CONSTRAINT; Schema: thirst; Owner: postgres
--

ALTER TABLE ONLY thirst.history_sale
    ADD CONSTRAINT history_sale_partners_id_fkey FOREIGN KEY (partners_id) REFERENCES thirst.partners(partners_id);


--
-- TOC entry 4885 (class 2606 OID 24736)
-- Name: history_sale history_sale_product_id_fkey; Type: FK CONSTRAINT; Schema: thirst; Owner: postgres
--

ALTER TABLE ONLY thirst.history_sale
    ADD CONSTRAINT history_sale_product_id_fkey FOREIGN KEY (product_id) REFERENCES thirst.products(product_id);


--
-- TOC entry 4881 (class 2606 OID 24705)
-- Name: partners partners_partners_type_fkey; Type: FK CONSTRAINT; Schema: thirst; Owner: postgres
--

ALTER TABLE ONLY thirst.partners
    ADD CONSTRAINT partners_partners_type_fkey FOREIGN KEY (partners_type) REFERENCES thirst.partners_type(partners_type_id);


--
-- TOC entry 4882 (class 2606 OID 25011)
-- Name: products products_material_type_fk; Type: FK CONSTRAINT; Schema: thirst; Owner: postgres
--

ALTER TABLE ONLY thirst.products
    ADD CONSTRAINT products_material_type_fk FOREIGN KEY (material_id) REFERENCES thirst.material_type(material_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4883 (class 2606 OID 24719)
-- Name: products products_product_type_fkey; Type: FK CONSTRAINT; Schema: thirst; Owner: postgres
--

ALTER TABLE ONLY thirst.products
    ADD CONSTRAINT products_product_type_fkey FOREIGN KEY (product_type) REFERENCES thirst.product_type(product_type_id);


-- Completed on 2025-02-04 02:36:58

--
-- PostgreSQL database dump complete
--

