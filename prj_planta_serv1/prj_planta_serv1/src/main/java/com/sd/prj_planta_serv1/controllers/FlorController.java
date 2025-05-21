package com.sd.prj_planta_serv1.controllers;

import com.sd.prj_planta_serv1.dtos.FlorRecordDto;
import com.sd.prj_planta_serv1.models.Flor;
import com.sd.prj_planta_serv1.services.FlorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flores")
public class FlorController {

    @Autowired
    private FlorService florService;

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Flor> getFlores() {
        return florService.getFlores();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/(id)")
    public Flor getFlores(@PathVariable Long id) {
        return florService.getFlor(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public Flor salvarPlanta(@RequestBody FlorRecordDto florRecordDto) {
        return florService.salvarFlor(florRecordDto);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping
    public void deletarFlor(@RequestBody Flor flor) {
        florService.excluirFlor(flor);
    }
}
