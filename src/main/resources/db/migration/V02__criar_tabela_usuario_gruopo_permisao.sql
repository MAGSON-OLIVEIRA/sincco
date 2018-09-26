CREATE TABLE usuario (
    id integer NOT NULL,
    nome character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    senha character varying(100) NOT NULL,
    cpfCnpj character varying(15) NOT NULL,
    telefoneCelular character varying(20),
    telefoneFixo character varying(20),
    tipoPessoa character varying(15) NOT NULL,
    ativo boolean default true
);

ALTER TABLE public.usuario OWNER TO postgres;

CREATE SEQUENCE usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.usuario_id_seq OWNER TO postgres;

ALTER SEQUENCE usuario_id_seq OWNED BY usuario.id;

ALTER TABLE ONLY usuario ALTER COLUMN id SET DEFAULT nextval('usuario_id_seq'::regclass);

INSERT INTO usuario (id, nome, email, senha, cpfCnpj) VALUES
	(99, 'Admin', 'admin@admin.com', '$2a$10$g.wT4R0Wnfel1jc/k84OXuwZE02BlACSLfWy6TycGPvvEKvIm86SG','00000000000');

SELECT pg_catalog.setval('usuario_id_seq', 1, false);

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);



CREATE TABLE permissao (
    id integer NOT NULL,
    nome character varying(100) NOT NULL
);

ALTER TABLE ONLY permissao
    ADD CONSTRAINT permissao_pkey PRIMARY KEY (id);


ALTER TABLE public.permissao OWNER TO postgres;

CREATE SEQUENCE permissao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.permissao_id_seq OWNER TO postgres;

ALTER SEQUENCE permissao_id_seq OWNED BY permissao.id;

ALTER TABLE ONLY permissao ALTER COLUMN id SET DEFAULT nextval('permissao_id_seq'::regclass);
    
    

CREATE TABLE grupo (
    id integer NOT NULL,
    nome character varying(100) NOT NULL
);

ALTER TABLE ONLY grupo
    ADD CONSTRAINT grupo_pkey PRIMARY KEY (id);

    
    ALTER TABLE public.grupo OWNER TO postgres;

CREATE SEQUENCE grupo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.grupo_id_seq OWNER TO postgres;

ALTER SEQUENCE grupo_id_seq OWNED BY grupo.id;

ALTER TABLE ONLY grupo ALTER COLUMN id SET DEFAULT nextval('grupo_id_seq'::regclass);


CREATE TABLE usuario_grupo (
    id_usuario integer NOT NULL,
    id_grupo integer NOT NULL
);

ALTER TABLE ONLY usuario_grupo
    ADD CONSTRAINT usuario_grupo_pkey PRIMARY KEY (id_usuario, id_grupo);

ALTER TABLE ONLY usuario_grupo
    ADD CONSTRAINT usuario_grupo_usuario_fk FOREIGN KEY (id_usuario) REFERENCES usuario(id);
    
ALTER TABLE ONLY usuario_grupo
    ADD CONSTRAINT usuario_grupo_grupo_fk FOREIGN KEY (id_grupo) REFERENCES grupo(id);

CREATE TABLE grupo_permissao (
    id_grupo integer NOT NULL,
    id_permissao integer NOT NULL,
    PRIMARY KEY (codigo_grupo, codigo_permissao),
    FOREIGN KEY (codigo_grupo) REFERENCES grupo(codigo),
    FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo)
);

ALTER TABLE ONLY grupo_permissao
    ADD CONSTRAINT grupo_permissao_pkey PRIMARY KEY (id_grupo, id_permissao);

ALTER TABLE ONLY grupo_permissao
    ADD CONSTRAINT grupo_permissao_grupo_fk FOREIGN KEY (id_grupo) REFERENCES grupo(id);
    
ALTER TABLE ONLY grupo_permissao
    ADD CONSTRAINT grupo_permissao_permisao_fk FOREIGN KEY (id_permissao) REFERENCES permissao(id);



