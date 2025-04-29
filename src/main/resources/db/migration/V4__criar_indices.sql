CREATE INDEX idx_enderecos_cidade ON enderecos (cidade);
CREATE INDEX idx_enderecos_cep ON enderecos (cep);

CREATE INDEX idx_psicologos_crp ON psicologos (crp);
CREATE INDEX idx_psicologos_cpf ON psicologos (cpf);
CREATE INDEX idx_psicologos_email ON psicologos (email);
CREATE INDEX idx_psicologos_id_endereco ON psicologos (id_endereco);

CREATE INDEX idx_pacientes_cpf ON pacientes (cpf);
CREATE INDEX idx_pacientes_email ON pacientes (email);
CREATE INDEX idx_pacientes_id_endereco ON pacientes (id_endereco);

CREATE INDEX idx_documentos_id_paciente ON documentos (id_paciente);
CREATE INDEX idx_documentos_id_psicologo ON documentos (id_psicologo);

CREATE INDEX idx_solicitacoes_id_paciente ON solicitacoes (id_paciente);
CREATE INDEX idx_solicitacoes_id_psicologo ON solicitacoes (id_psicologo);

CREATE INDEX idx_consultas_data_hora ON consultas (data_hora);
CREATE INDEX idx_consultas_id_paciente ON consultas (id_paciente);
CREATE INDEX idx_consultas_id_psicologo ON consultas (id_psicologo);

CREATE INDEX idx_vinculos_id_paciente ON vinculos (id_paciente);
CREATE INDEX idx_vinculos_id_psicologo ON vinculos (id_psicologo);

CREATE INDEX idx_avaliacoes_id_psicologo ON avaliacoes (id_psicologo);
CREATE INDEX idx_avaliacoes_id_paciente ON avaliacoes (id_paciente);

CREATE INDEX idx_horarios_psicologos_data ON horarios_psicologos (data);
CREATE INDEX idx_horarios_psicologos_hora ON horarios_psicologos (hora);

CREATE INDEX idx_prontuarios_id_paciente ON prontuarios (id_paciente);
CREATE INDEX idx_prontuarios_id_psicologo ON prontuarios (id_psicologo);

CREATE INDEX idx_atestados_id_documento ON atestados (id_documento);
CREATE INDEX idx_laudos_psicologicos_id_documento ON laudos_psicologicos (id_documento);
CREATE INDEX idx_pareceres_psicologicos_id_documento ON pareceres_psicologicos (id_documento);
CREATE INDEX idx_relatorios_psicologicos_id_documento ON relatorios_psicologicos (id_documento);
CREATE INDEX idx_declaracoes_id_documento ON declaracoes (id_documento);
