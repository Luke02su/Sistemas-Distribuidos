package com.sd.prj_planta_serv1.dtos;

public record FlorRecordDto(long idflor,
                           double comprimento_sepala,
                           double largura_sepala,
                           double comprimento_petala,
                           double largura_petala,
                           String cor,
                           String especieTipo,
                           long idplanta) {
}
