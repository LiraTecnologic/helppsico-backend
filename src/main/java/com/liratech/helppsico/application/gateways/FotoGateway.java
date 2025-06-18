package com.liratech.helppsico.application.gateways;

import org.springframework.web.multipart.MultipartFile;

public interface FotoGateway {
    String salvarLocal(MultipartFile foto);
}
