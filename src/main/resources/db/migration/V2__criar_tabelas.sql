CREATE TABLE enderecos (
                id_endereco CHAR(36) NOT NULL,
                cidade VARCHAR(100) NOT NULL,
                cep VARCHAR(12) NOT NULL,
                rua VARCHAR(100) NOT NULL,
                numero INT NOT NULL,
                estado VARCHAR(100) NOT NULL,
                PRIMARY KEY (id_endereco)
);


CREATE TABLE psicologos (
                id_psicologo CHAR(36) NOT NULL,
                nome VARCHAR(100) NOT NULL,
                crp VARCHAR(20) NOT NULL,
                cpf VARCHAR(14) NOT NULL,
                email VARCHAR(70) NOT NULL,
                telefone VARCHAR(20) NOT NULL,
                data_nascimento DATE NOT NULL,
                biografia TEXT NOT NULL,
                senha VARCHAR(50) NOT NULL,
                foto_url VARCHAR(100) NOT NULL,
                genero VARCHAR(20) NOT NULL,
                id_endereco CHAR(36) NOT NULL,
                PRIMARY KEY (id)
);


CREATE TABLE horarios_psicologos (
                id_horario_psicologo CHAR(36) NOT NULL,
                id_psicologo CHAR(36) NOT NULL,
                data DATE NOT NULL,
                hora TIME NOT NULL,
                PRIMARY KEY (id)
);


CREATE TABLE pacientes (
                id_paciente CHAR(36) NOT NULL,
                nome VARCHAR(150) NOT NULL,
                cpf VARCHAR(14) NOT NULL,
                email VARCHAR(150) NOT NULL,
                telefone VARCHAR(20) NOT NULL,
                data_nascimento DATE NOT NULL,
                senha VARCHAR(50) NOT NULL,
                genero VARCHAR(9) NOT NULL,
                foto_url VARCHAR(100) NOT NULL,
                id_endereco CHAR(36) NOT NULL,
                PRIMARY KEY (id)
);


CREATE TABLE documentos (
                id_documento CHAR(36) NOT NULL,
                id_paciente CHAR(36) NOT NULL,
                id_psicologo CHAR(36) NOT NULL,
                data_emissao DATE NOT NULL,
                data_validade DATE NOT NULL,
                assinatura_psicologo VARCHAR(255) NOT NULL,
                PRIMARY KEY (id_documento)
);


CREATE TABLE declaracoes (
                id_documento CHAR(36) NOT NULL,
                motivo VARCHAR(255) NOT NULL,
                descricao VARCHAR(255) NOT NULL,
                finalidade VARCHAR(255) NOT NULL,
                PRIMARY KEY (id_documento)
);


CREATE TABLE relatorios_psicologicos (
                id_documento CHAR(36) NOT NULL,
                solicitante VARCHAR(60) NOT NULL,
                objetivo VARCHAR(70) NOT NULL,
                historico VARCHAR(70) NOT NULL,
                procedimentos_utilizados VARCHAR(70) NOT NULL,
                descricao_resultados VARCHAR(70) NOT NULL,
                conclusao VARCHAR(255) NOT NULL,
                recomendacoes VARCHAR(100) NOT NULL,
                sigilo VARCHAR(100) NOT NULL,
                PRIMARY KEY (id_documento)
);


CREATE TABLE pareceres_psicologicos (
                id_documento CHAR(36) NOT NULL,
                solicitante VARCHAR(60) NOT NULL,
                objetivo VARCHAR(70) NOT NULL,
                conclusao VARCHAR(255) NOT NULL,
                sigilo VARCHAR(100) NOT NULL,
                contextualizacao VARCHAR(255) NOT NULL,
                fundamentacao VARCHAR(255) NOT NULL,
                analise_do_caso VARCHAR(255) NOT NULL,
                PRIMARY KEY (id_documento)
);


CREATE TABLE laudos_psicologicos (
                id_documento CHAR(36) NOT NULL,
                solicitante VARCHAR(60) NOT NULL,
                objetivo VARCHAR(70) NOT NULL,
                historico VARCHAR(70) NOT NULL,
                procedimentos_utilizados VARCHAR(70) NOT NULL,
                descricao_resultados VARCHAR(70) NOT NULL,
                conclusao VARCHAR(255) NOT NULL,
                resposta_demanda VARCHAR(255) NOT NULL,
                recomendacoes VARCHAR(100) NOT NULL,
                sigilo VARCHAR(100) NOT NULL,
                PRIMARY KEY (id_documento)
);


CREATE TABLE atestados (
                id_documento CHAR(36) NOT NULL,
                data_atendimento DATE NOT NULL,
                descricao VARCHAR(255) NOT NULL,
                descricao_estado_psicologico VARCHAR(255) NOT NULL,
                periodo_afastamento VARCHAR(50) NOT NULL,
                finalidade VARCHAR(255) NOT NULL,
                id_endereco CHAR(36) NOT NULL,
                PRIMARY KEY (id_documento)
);


CREATE TABLE solicitacoes (
                id_solicitacao CHAR(36) NOT NULL,
                id_paciente CHAR(36) NOT NULL,
                id_psicologo CHAR(36) NOT NULL,
                tipo_documento VARCHAR(27) NOT NULL,
                PRIMARY KEY (id)
);


CREATE TABLE prontuarios (
                id_prontuario CHAR(36) NOT NULL,
                id_psicologo CHAR(36) NOT NULL,
                id_paciente CHAR(36) NOT NULL,
                titulo VARCHAR(50) NOT NULL,
                conteudo VARCHAR(255) NOT NULL,
                PRIMARY KEY (id)
);


CREATE TABLE avaliacoes (
                id_avaliacao CHAR(36) NOT NULL,
                id_psicologo CHAR(36) NOT NULL,
                id_paciente CHAR(36) NOT NULL,
                nota DECIMAL NOT NULL,
                comentario VARCHAR(255) NOT NULL,
                PRIMARY KEY (id)
);


CREATE TABLE vinculos (
                id_vinculo CHAR(36) NOT NULL,
                id_paciente CHAR(36) NOT NULL,
                id_psicologo CHAR(36) NOT NULL,
                status VARCHAR(8) NOT NULL,
                PRIMARY KEY (id)
);


CREATE TABLE consultas (
                id_consulta CHAR(36) NOT NULL,
                id_psicologo CHAR(36) NOT NULL,
                id_paciente CHAR(36) NOT NULL,
                data_hora DATETIME NOT NULL,
                valor DECIMAL NOT NULL,
                finalizada BOOLEAN NOT NULL,
                id_endereco CHAR(36) NOT NULL,
                PRIMARY KEY (id)
);
