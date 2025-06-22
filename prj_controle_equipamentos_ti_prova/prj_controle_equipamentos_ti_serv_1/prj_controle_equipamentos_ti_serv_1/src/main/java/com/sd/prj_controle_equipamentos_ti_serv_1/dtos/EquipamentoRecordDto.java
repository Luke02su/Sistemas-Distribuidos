package com.sd.prj_controle_equipamentos_ti_serv_1.dtos;

public record EquipamentoRecordDto(
    String pk_num_serie,
    String placa,
    String tipo,
    String modelo,
    int localizacao_atual,
    String enviado
) {}
