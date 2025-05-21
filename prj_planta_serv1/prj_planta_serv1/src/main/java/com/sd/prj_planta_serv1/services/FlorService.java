package com.sd.prj_planta_serv1.services;

import com.sd.prj_planta_serv1.dtos.FlorRecordDto;
import com.sd.prj_planta_serv1.models.Flor;
import com.sd.prj_planta_serv1.models.Planta;
import com.sd.prj_planta_serv1.repositories.PlantaRepository;
import com.sd.prj_planta_serv1.repositories.FlorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlorService {
    private final FlorRepository florRepository;
    private final PlantaRepository plantaRepository;

    public FlorService(FlorRepository florRepository, PlantaRepository plantaRepository) {
        this.florRepository = florRepository;
        this.plantaRepository = plantaRepository;
    }

    public List<Flor> getFlores() {
        return florRepository.findAll();
    }

    public Flor getFlor(Long id) {
        return florRepository.findById(id).get();
    }

    public Flor salvarFlor(FlorRecordDto florRecordDto){
        Flor flor = new Flor();

        flor.setIdflor(florRecordDto.idflor());
        flor.setComprimento_sepala(florRecordDto.largura_petala());
        flor.setLargura_sepala(florRecordDto.comprimento_petala());
        flor.setComprimento_petala(florRecordDto.largura_petala());
        flor.setLargura_petala(florRecordDto.largura_petala());
        flor.setCor(florRecordDto.cor());
        flor.setEspecieTipo(florRecordDto.especieTipo());
        flor.setPlanta(plantaRepository.findById(florRecordDto.idplanta()).get());

        return florRepository.save(flor);
    }

    public Flor excluirFlor(Flor flor){
        florRepository.deleteById(flor.getIdflor());
        return flor;
    }
}
