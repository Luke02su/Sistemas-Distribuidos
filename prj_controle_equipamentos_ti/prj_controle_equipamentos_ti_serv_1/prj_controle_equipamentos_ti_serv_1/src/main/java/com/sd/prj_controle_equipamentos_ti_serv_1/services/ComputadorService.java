package com.sd.prj_controle_equipamentos_ti_serv_1.services;

import com.sd.prj_controle_equipamentos_ti_serv_1.dtos.ComputadorRecordDto;
import com.sd.prj_controle_equipamentos_ti_serv_1.models.Computador;
import com.sd.prj_controle_equipamentos_ti_serv_1.models.Equipamento;
import com.sd.prj_controle_equipamentos_ti_serv_1.repositories.ComputadorRepository;
import com.sd.prj_controle_equipamentos_ti_serv_1.repositories.EquipamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputadorService {

    private final ComputadorRepository computadorRepository;
    private final EquipamentoRepository equipamentoRepository;

    public ComputadorService(ComputadorRepository computadorRepository, EquipamentoRepository equipamentoRepository) {
        this.computadorRepository = computadorRepository;
        this.equipamentoRepository = equipamentoRepository;
    }

    public List<Computador> getComputadores() {
        return computadorRepository.findAll();
    }

    public Computador getComputador(Long id) {
        return computadorRepository.findById(id).get();
    }

    public Computador salvarComputador(ComputadorRecordDto dto) {
        Computador computador = new Computador();

        computador.setPk_computador(dto.pk_computador());
        computador.setProcessador(dto.processador());
        computador.setMemoria(dto.memoria());
        computador.setWindows(dto.windows());
        computador.setArmazenamento(dto.armazenamento());
        computador.setFormatacao(dto.formatacao());
        computador.setManutencao(dto.manutencao());

        Equipamento equipamento = equipamentoRepository.findById(dto.fk_num_serie()).orElse(null);
        computador.setEquipamento(equipamento);

        return computadorRepository.save(computador);
    }

    public Computador excluirComputador(Computador computador) {
        computadorRepository.deleteById(computador.getPk_computador());
        return computador;
    }
}
