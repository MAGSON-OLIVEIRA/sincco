-- // --

CREATE TABLE servico (
    id numeric(20) NOT NULL,
    id_usuario integer not null,
    id_cep_servico integer not null,
    id_tipologia integer not null,
    id_produto integer not null,
    unidadeConsumidora character varying(100) NOT NULL,
    observacao character varying(100),
    dataInicio date,
    dataFim date,
    ativo boolean default true
);

ALTER TABLE public.servico OWNER TO postgres;

CREATE SEQUENCE servico_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.servico_id_seq OWNER TO postgres;
ALTER SEQUENCE servico_id_seq OWNED BY servico.id;
ALTER TABLE ONLY servico ALTER COLUMN id SET DEFAULT nextval('servico_id_seq'::regclass);
SELECT pg_catalog.setval('servico_id_seq', 1, false);

ALTER TABLE ONLY servico
ADD CONSTRAINT servico_pkey PRIMARY KEY (id);

ALTER TABLE ONLY servico
ADD CONSTRAINT servico_usuario_fk FOREIGN KEY (id_usuario) REFERENCES usuario(id);

ALTER TABLE ONLY servico
ADD CONSTRAINT servico_cep_servico_fk FOREIGN KEY (id_cep_servico) REFERENCES cep_servico(id);

ALTER TABLE ONLY servico
ADD CONSTRAINT servico_tipologia_fk FOREIGN KEY (id_tipologia) REFERENCES tipologia(id);

ALTER TABLE ONLY servico
ADD CONSTRAINT servico_produto_fk FOREIGN KEY (id_produto) REFERENCES produto(id);   

-- // --

CREATE TABLE item_servico (
    id numeric(20) NOT NULL,
    id_servico numeric(20) not null,
    medida integer not null,
    quantidadeMoradores integer not null,
    diasMedido integer not null,
    dataCompetencia date
);

ALTER TABLE public.item_servico OWNER TO postgres;

CREATE SEQUENCE item_servico_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.item_servico_id_seq OWNER TO postgres;

ALTER SEQUENCE item_servico_id_seq OWNED BY item_servico.id;

ALTER TABLE ONLY item_servico ALTER COLUMN id SET DEFAULT nextval('item_servico_id_seq'::regclass);

SELECT pg_catalog.setval('item_servico_id_seq', 1, false);

ALTER TABLE ONLY item_servico
    ADD CONSTRAINT item_servico_pkey PRIMARY KEY (id);

 
ALTER TABLE ONLY item_servico
    ADD CONSTRAINT item_servico_servico_fk FOREIGN KEY (id_servico) REFERENCES servico(id);