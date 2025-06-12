package com.sd.prj_controle_equipamentos_ti_serv_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sd.prj_controle_equipamentos_ti_serv_1.models.Equipamento;
import com.sd.prj_controle_equipamentos_ti_serv_1.services.EquipamentoService;

import java.util.List;

@RestController
@RequestMapping("/api/equipamentos")
@CrossOrigin(origins = "*")
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Equipamento> getEquipamentos() {
        return equipamentoService.getEquipamentos();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{pk_num_serie}")
    public Equipamento getEquipamento(@PathVariable String pk_num_serie) {
        return equipamentoService.getEquipamento(pk_num_serie);
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public Equipamento salvarEquipamento(@RequestBody Equipamento equipamento) {
        return equipamentoService.salvarEquipamento(equipamento);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping
    public void deletarEquipamento(@RequestBody Equipamento equipamento) {
        equipamentoService.excluirEquipamento(equipamento);
    }
}