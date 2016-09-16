CREATE TABLE categoria (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE grupo (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE lembrete (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(80) NOT NULL,
    descricao TEXT NOT NULL,
    codigo_categoria BIGINT(20) NOT NULL,
    codigo_grupo BIGINT(20),
	ordem INTEGER,
	data_cadastro DATE,
	FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo),
    FOREIGN KEY (codigo_grupo) REFERENCES grupo(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria VALUES (0, 'Spring');
INSERT INTO categoria VALUES (0, 'Angular');
INSERT INTO grupo VALUES (0, 'Configuração projeto Eclipse-Spring');
INSERT INTO grupo VALUES (0, 'Configuração projeto Angular');
