-- inicio --
CREATE TABLE estado (
    codigo integer NOT NULL,
    nome character varying(80) NOT NULL,
    sigla character varying(2) NOT NULL
 
);

ALTER TABLE public.estado OWNER TO postgres;

CREATE SEQUENCE estado_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.estado_codigo_seq OWNER TO postgres;

ALTER SEQUENCE estado_codigo_seq OWNED BY estado.codigo;

ALTER TABLE ONLY estado ALTER COLUMN id SET DEFAULT nextval('estado_codigo_seq'::regclass);

SELECT pg_catalog.setval('estado_codigo_seq', 1, false);

ALTER TABLE ONLY estado
    ADD CONSTRAINT estado_pkey PRIMARY KEY (codigo);
-- fim --


-- / inicio tb cidade / --
CREATE TABLE cidade (
    codigo integer NOT NULL,
    nome character varying(100) NOT NULL,
	codigo_estado integer NOT NULL
);

ALTER TABLE public.cidade OWNER TO postgres;

CREATE SEQUENCE cidade_codigo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.cidade_codigo_seq OWNER TO postgres;

ALTER SEQUENCE cidade_codigo_seq OWNED BY cidade.codigo;

ALTER TABLE ONLY cidade ALTER COLUMN codigo SET DEFAULT nextval('cidade_codigo_seq'::regclass);

SELECT pg_catalog.setval('cidade_codigo_seq', 1, false);

ALTER TABLE ONLY cidade
    ADD CONSTRAINT cidade_pkey PRIMARY KEY (codigo);
	
ALTER TABLE ONLY cidade
    ADD CONSTRAINT cidade_estado_fk FOREIGN KEY (codigo_estado) REFERENCES estado(codigo);
    
    INSERT INTO estado (codigo, nome, sigla) VALUES (1,'Acre', 'AC');
INSERT INTO estado (codigo, nome, sigla) VALUES (2,'Bahia', 'BA');
INSERT INTO estado (codigo, nome, sigla) VALUES (3,'Goiás', 'GO');
INSERT INTO estado (codigo, nome, sigla) VALUES (4,'Minas Gerais', 'MG');
INSERT INTO estado (codigo, nome, sigla) VALUES (5,'Santa Catarina', 'SC');
INSERT INTO estado (codigo, nome, sigla) VALUES (6,'São Paulo', 'SP');


INSERT INTO cidade (nome, codigo_estado) VALUES ('Rio Branco', 1);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Cruzeiro do Sul', 1);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Salvador', 2);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Porto Seguro', 2);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Santana', 2);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Goiânia', 3);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Itumbiara', 3);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Novo Brasil', 3);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Belo Horizonte', 4);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Uberlândia', 4);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Montes Claros', 4);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Florianópolis', 5);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Criciúma', 5);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Camboriú', 5);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Lages', 5);
INSERT INTO cidade (nome, codigo_estado) VALUES ('São Paulo', 6);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Ribeirão Preto', 6);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Campinas', 6);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Santos', 6);
    

