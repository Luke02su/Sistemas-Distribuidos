package com.sd.prj_planta_serv1.controllers;

import com.sd.prj_planta_serv1.models.Planta;
import com.sd.prj_planta_serv1.services.PlantaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plantas")
public class PlantaController {

    @Autowired
    private PlantaService plantaService;

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Planta> getPlantas() {
        return plantaService.getPlantas();
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public Planta salvarPlanta(@RequestBody Planta planta) {
        return plantaService.salvarPlanta(planta);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping
    public void deletarPlanta(@RequestBody Planta planta) {
        plantaService.excluirPlanta(planta);
    }
}
