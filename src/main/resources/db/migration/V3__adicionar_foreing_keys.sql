ALTER TABLE pacientes ADD CONSTRAINT enderecos_pacientes_fk
FOREIGN KEY (id_endereco)
REFERENCES enderecos (id_endereco)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE consultas ADD CONSTRAINT enderecos_consultas_fk
FOREIGN KEY (id_endereco)
REFERENCES enderecos (id_endereco)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE psicologos ADD CONSTRAINT enderecos_psicologos_fk
FOREIGN KEY (id_endereco)
REFERENCES enderecos (id_endereco)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE atestados ADD CONSTRAINT enderecos_atestados_fk
FOREIGN KEY (id_endereco)
REFERENCES enderecos (id_endereco)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE consultas ADD CONSTRAINT psicologos_consultas_fk
FOREIGN KEY (id_psicologo)
REFERENCES psicologos (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE vinculos ADD CONSTRAINT psicologos_vinculo_fk
FOREIGN KEY (id_psicologo)
REFERENCES psicologos (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE avaliacoes ADD CONSTRAINT psicologos_avaliacoes_fk
FOREIGN KEY (id_psicologo)
REFERENCES psicologos (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE prontuarios ADD CONSTRAINT psicologos_prontuario_fk
FOREIGN KEY (id_psicologo)
REFERENCES psicologos (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE horarios_psicologos ADD CONSTRAINT psicologos_horariopsicologo_fk
FOREIGN KEY (id_psicologo)
REFERENCES psicologos (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE solicitacoes ADD CONSTRAINT psicologos_solicitacoes_fk
FOREIGN KEY (id_psicologo)
REFERENCES psicologos (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE documentos ADD CONSTRAINT psicologos_documentos_fk
FOREIGN KEY (id_psicologo)
REFERENCES psicologos (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE consultas ADD CONSTRAINT paciente_consulta_fk
FOREIGN KEY (id_paciente)
REFERENCES pacientes (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE vinculos ADD CONSTRAINT pacientes_vinculo_fk
FOREIGN KEY (id_paciente)
REFERENCES pacientes (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE avaliacoes ADD CONSTRAINT pacientes_avaliacoes_fk
FOREIGN KEY (id_paciente)
REFERENCES pacientes (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE prontuarios ADD CONSTRAINT pacientes_prontuario_fk
FOREIGN KEY (id_paciente)
REFERENCES pacientes (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE solicitacoes ADD CONSTRAINT pacientes_solicitacoes_fk
FOREIGN KEY (id_paciente)
REFERENCES pacientes (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE documentos ADD CONSTRAINT pacientes_documentos_fk
FOREIGN KEY (id_paciente)
REFERENCES pacientes (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE atestados ADD CONSTRAINT documentos_atestados_fk
FOREIGN KEY (id_documento)
REFERENCES documentos (id_documento)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE laudos_psicologicos ADD CONSTRAINT documentos_laudospsicologicos_fk
FOREIGN KEY (id_documento)
REFERENCES documentos (id_documento)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE pareceres_psicologicos ADD CONSTRAINT documentos_parecerpsicologicos_fk
FOREIGN KEY (id_documento)
REFERENCES documentos (id_documento)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE relatorios_psicologicos ADD CONSTRAINT documentos_relatoriopsicologicos_fk
FOREIGN KEY (id_documento)
REFERENCES documentos (id_documento)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE declaracoes ADD CONSTRAINT documentos_declaracoes_fk
FOREIGN KEY (id_documento)
REFERENCES documentos (id_documento)
ON DELETE NO ACTION
ON UPDATE NO ACTION;