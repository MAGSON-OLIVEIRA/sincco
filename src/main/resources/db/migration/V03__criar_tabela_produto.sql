-- inicio --
CREATE TABLE produto (
    id integer NOT NULL,
    descricao character varying(100) NOT NULL,
    sigla character varying(100) NOT NULL
 
);

ALTER TABLE public.produto OWNER TO postgres;

CREATE SEQUENCE produto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.produto_id_seq OWNER TO postgres;

ALTER SEQUENCE produto_id_seq OWNED BY produto.id;

ALTER TABLE ONLY produto ALTER COLUMN id SET DEFAULT nextval('produto_id_seq'::regclass);

SELECT pg_catalog.setval('produto_id_seq', 1, false);

ALTER TABLE ONLY produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);
-- fim --


-- // --

CREATE TABLE moradia (
    id integer NOT NULL,
    tipoMoradia character varying(100) NOT NULL,
    descricao character varying(100) NOT NULL
);

ALTER TABLE public.moradia OWNER TO postgres;

CREATE SEQUENCE moradia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.moradia_id_seq OWNER TO postgres;

ALTER SEQUENCE moradia_id_seq OWNED BY moradia.id;

ALTER TABLE ONLY moradia ALTER COLUMN id SET DEFAULT nextval('moradia_id_seq'::regclass);

SELECT pg_catalog.setval('moradia_id_seq', 1, false);

ALTER TABLE ONLY moradia
    ADD CONSTRAINT moradia_pkey PRIMARY KEY (id);

-- // --

CREATE TABLE leitura (
    id integer NOT NULL,
    descricao character varying(100) NOT NULL,
    sigla character varying(50) NOT NULL
);

ALTER TABLE public.leitura OWNER TO postgres;

CREATE SEQUENCE leitura_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.leitura_id_seq OWNER TO postgres;

ALTER SEQUENCE leitura_id_seq OWNED BY leitura.id;

ALTER TABLE ONLY leitura ALTER COLUMN id SET DEFAULT nextval('leitura_id_seq'::regclass);

SELECT pg_catalog.setval('leitura_id_seq', 1, false);

ALTER TABLE ONLY leitura
    ADD CONSTRAINT leitura_pkey PRIMARY KEY (id);

-- // --

CREATE TABLE tipologia (
    id integer NOT NULL,
    descricao character varying(100) NOT NULL,
    sigla character varying(50) NOT NULL,
	id_moradia integer NOT NULL,
	id_leitura integer not null,
	dataInclusao date
);

ALTER TABLE public.tipologia OWNER TO postgres;

CREATE SEQUENCE tipologia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.tipologia_id_seq OWNER TO postgres;

ALTER SEQUENCE tipologia_id_seq OWNED BY tipologia.id;

ALTER TABLE ONLY tipologia ALTER COLUMN id SET DEFAULT nextval('tipologia_id_seq'::regclass);

SELECT pg_catalog.setval('tipologia_id_seq', 1, false);

ALTER TABLE ONLY tipologia
    ADD CONSTRAINT tipologia_pkey PRIMARY KEY (id);
	

ALTER TABLE ONLY tipologia
    ADD CONSTRAINT tipologia_moradia_fk FOREIGN KEY (id_moradia) REFERENCES moradia(id);
    
ALTER TABLE ONLY tipologia
    ADD CONSTRAINT tipologia_leitura_fk FOREIGN KEY (id_leitura) REFERENCES leitura(id);
    
 -- inicio --

CREATE TABLE cep_servico(
    		   id integer NOT NULL,
    		   uf character varying(2) not null,
               geocodigo character varying(100) ,
               cidade character varying(100),
               logradouro character varying(200),
               cep        character varying(15) NOT NULL,
               latitude NUMERIC(10, 8),
               longtude NUMERIC(10, 8),
               codigo_cidade integer
);

 

ALTER TABLE public.cep_servico OWNER TO postgres;

CREATE SEQUENCE cep_servico_id_seq

    START WITH 1

    INCREMENT BY 1

    NO MINVALUE

    NO MAXVALUE

    CACHE 1;


ALTER TABLE public.cep_servico_id_seq OWNER TO postgres;
ALTER SEQUENCE cep_servico_id_seq OWNED BY cep_servico.id;
ALTER TABLE ONLY cep_servico ALTER COLUMN id SET DEFAULT nextval('cep_servico_id_seq'::regclass);

SELECT pg_catalog.setval('cep_servico_id_seq', 1, false);

ALTER TABLE ONLY cep_servico
    ADD CONSTRAINT cep_servico_pkey PRIMARY KEY (id);
    
ALTER TABLE ONLY cep_servico
    ADD CONSTRAINT cidade_cep_servico_fk FOREIGN KEY (codigo_cidade) REFERENCES cidade(codigo);
    

    
insert into cep_servico(uf, geocodigo, cidade, logradouro, cep, latitude, longtude)
values( 'DF', 'GEO012511', 'Santa Maria', 'Qr 205', '72505402', 10.11545, -52.51215);   
