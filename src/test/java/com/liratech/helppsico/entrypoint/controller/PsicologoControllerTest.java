package com.liratech.helppsico.entrypoint.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PsicologoControllerTest {

    @Mock
    private PsicologoUseCase useCase;
    @Mock
    private PsicologoMapper mapper;

    @InjectMocks
    private PsicologoController controller;


}
