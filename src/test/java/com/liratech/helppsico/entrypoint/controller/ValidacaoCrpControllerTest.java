package com.liratech.helppsico.entrypoint.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.builders.ValidacaoCrpBuilder;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.infrastructure.repositories.ValidacaoCrpRepository;
import com.liratech.helppsico.validators.ValidacaoCrpValidator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
@ActiveProfiles("test")
public class ValidacaoCrpControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final ValidacaoCrpMapper mapperEntry;
    private final com.liratech.helppsico.infrastructure.mapper.ValidacaoCrpMapper mapperInfra;

    @MockitoSpyBean
    private final ValidacaoCrpRepository repository;

    private ValidacaoCrpDto validacaoEntrada;
    private ValidacaoCrp validacaoDomain;

    public ValidacaoCrpControllerTest(MockMvc mockMvc, ObjectMapper objectMapper, ValidacaoCrpMapper mapperEntry,
                                      com.liratech.helppsico.infrastructure.mapper.ValidacaoCrpMapper mapperInfra,
                                      ValidacaoCrpRepository repository) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.mapperEntry = mapperEntry;
        this.mapperInfra = mapperInfra;
        this.repository = repository;
    }

    @BeforeEach
    void inicializarAtributos(){
        this.validacaoEntrada = ValidacaoCrpBuilder.criarValidacaoCrpDto();
        this.validacaoDomain = mapperEntry.paraDomain(validacaoEntrada);
    }

    @Test
    void testeCriarValidacaoCrp() throws Exception{
        ValidacaoCrp validacaoCrp = ValidacaoCrpBuilder.criarValidacao();
        Psicologo psicologo = PsicologoBuilder.criarPsicologo();
        psicologo.setId(validacaoCrp.getPsicologo().getId());

        Mockito.when(psicologoUseCase.consultarPorId(Mockito.any())).thenReturn(psicologo);
        Mockito.when(gateway.salvar(captor.capture())).thenReturn(validacaoCrp);

        ValidacaoCrp resultado = useCase.criar(validacaoCrp);

        ValidacaoCrp capturado = captor.getValue();
        ValidacaoCrpValidator.validaValidacaoCrpDomain(validacaoCrp, capturado);
        ValidacaoCrpValidator.validaValidacaoCrpDomain(validacaoCrp, resultado);
    }

    @Test
    void testeValidarVerificaoCrp() throws Exception{
        ValidacaoCrp validacaoCrp = ValidacaoCrpBuilder.criarValidacao();
        UUID id = validacaoCrp.getId();

        Mockito.when(gateway.buscarPorId(id)).thenReturn(Optional.of(validacaoCrp));
        Mockito.when(gateway.salvar(Mockito.any())).thenReturn(validacaoCrp);

        ValidacaoCrp resultado = useCase.validar(validacaoCrp);

        ValidacaoCrpValidator.validaValidacaoCrpDomain(validacaoCrp, resultado);
        Mockito.verify(gateway, Mockito.times(1)).buscarPorId(id);
        Mockito.verify(gateway, Mockito.times(1)).salvar(Mockito.any());
    }

    @Test
    void testeBuscarValidacaoCrpPorIdNaoEncontrada() {
        UUID id = UUID.randomUUID();

        Mockito.when(gateway.buscarPorId(id)).thenReturn(Optional.empty());

        ValidacaoCrpNaoEncontradaException exception = Assertions.assertThrows(
                ValidacaoCrpNaoEncontradaException.class, () -> useCase.buscarPorId(id)
        );

        Assertions.assertEquals(MENSAGEM_VALIDACAO_CRP_NAO_ENCONTRADA, exception.getMessage());
        Mockito.verify(gateway, Mockito.times(1)).buscarPorId(id);
    }

    @Test
    void testeListarValidacaoCrp() throws Exception {
        int page = 0;
        int size = 10;
        String sort = "nome,asc";

        Pageable pageable = PageRequest.of(page,size);

        Page<ValidacaoCrp> paginaDomain = ValidacaoCrpBuilder.criarValidacaoCrp();
        Page<ValidacaCrpEntity> paginaEntity = paginaDomain.map(mapperInfra::paraEntity);

        Mockito.when(repository.findAll(pageable)).thenReturn(paginaEntity);

        ResultActions resultado = mockMvc.perform(get("/validacao-crp")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("sort", sort))
                .andExpect(status().isOk());

        ValidacaoCrpValidator.validaPageResponse(resultado);
    }
}