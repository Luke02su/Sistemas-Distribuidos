package com.sd.prj_planta_serv1.services;

import com.sd.prj_planta_serv1.models.Planta;
import com.sd.prj_planta_serv1.repositories.PlantaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantaService {
    private final PlantaRepository plantaRepository;

    public PlantaService(PlantaRepository plantaRepository) {
        this.plantaRepository = plantaRepository;
    }

    public List<Planta> getPlantas() {
        return plantaRepository.findAll();
    }

    public Planta salvarPlanta(Planta planta){
        return plantaRepository.save(planta);
    }

    public Planta excluirPlanta(Planta planta){
        plantaRepository.deleteById(planta.getIdplanta());
        return planta;
    }
}
