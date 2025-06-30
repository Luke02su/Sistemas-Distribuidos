package com.sd.prj_controle_equipamentos_ti_serv_1.controllers;

import com.sd.prj_controle_equipamentos_ti_serv_1.dtos.ComputadorRecordDto;
import com.sd.prj_controle_equipamentos_ti_serv_1.models.Computador;
import com.sd.prj_controle_equipamentos_ti_serv_1.services.ComputadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/computadores")
public class ComputadorController {

    @Autowired
    private ComputadorService computadorService;

    //@CrossOrigin(origins = "*")
    @GetMapping
    public List<Computador> getComputadores() {
        return computadorService.getComputadores();
    }

   // @CrossOrigin(origins = "*")
    @GetMapping("/{pk_computador}")
        public Computador getComputador(@PathVariable Long pk_computador) {
        return computadorService.getComputador(pk_computador);
    }

    //@CrossOrigin(origins = "*")
    @PostMapping
    public Computador salvarComputador(@RequestBody ComputadorRecordDto computadorRecordDto) {
        return computadorService.salvarComputador(computadorRecordDto);
    }

    //@CrossOrigin(origins = "*")
    @DeleteMapping
    public void deletarComputador(@RequestBody Computador computador) {
        computadorService.excluirComputador(computador);
    }
}
