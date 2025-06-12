package com.sd.prj_controle_equipamentos_ti_serv_1.dtos;

public record ComputadorRecordDto (
    Long pk_computador,
    String processador,
    String memoria,
    String windows,
    String armazenamento,
    String formatacao,
    String manutencao,
    String fk_num_serie
) {}
