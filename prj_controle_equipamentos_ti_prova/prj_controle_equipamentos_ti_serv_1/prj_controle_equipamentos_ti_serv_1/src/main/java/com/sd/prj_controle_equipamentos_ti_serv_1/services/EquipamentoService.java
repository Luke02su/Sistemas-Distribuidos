package com.sd.prj_controle_equipamentos_ti_serv_1.services;

import com.sd.prj_controle_equipamentos_ti_serv_1.models.Equipamento;
import com.sd.prj_controle_equipamentos_ti_serv_1.repositories.EquipamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipamentoService {
    private final EquipamentoRepository equipamentoRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentoRepository.findAll();
    }

    public Equipamento getEquipamento(String pk) {
        return equipamentoRepository.findById(pk).get();
    }    

    public Equipamento salvarEquipamento(Equipamento equipamento){
        return equipamentoRepository.save(equipamento);
    }

    public Equipamento excluirEquipamento(Equipamento equipamento){
        equipamentoRepository.deleteById(equipamento.getPk_num_serie());
        return equipamento;
    }
}
