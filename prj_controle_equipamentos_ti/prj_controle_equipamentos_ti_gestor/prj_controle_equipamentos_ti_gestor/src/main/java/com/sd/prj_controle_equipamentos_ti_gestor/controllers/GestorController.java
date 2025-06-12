package com.sd.prj_controle_equipamentos_ti_gestor.controllers;

import com.sd.prj_controle_equipamentos_ti_gestor.services.SistemasService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gestor")
@CrossOrigin(origins = "*")
public class GestorController {

    @Autowired
    private SistemasService sistemasService;

    @CrossOrigin(origins = "*")
    @GetMapping("/equipamentos/simples")
    public ResponseEntity<String> listarEquipamentos() {
        return sistemasService.getEquipamentos();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/equipamentos")
    public ResponseEntity<List<Map<String, Object>>> listarEquipamentosComEnvio() {
        List<Map<String, Object>> equipamentos = sistemasService.listarEquipamentosComDataDeEnvio();
        return ResponseEntity.ok(equipamentos);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/equipamentos/{pk_num_serie}")
    public ResponseEntity<String> getEquipamento(@PathVariable String pk_num_serie) {
        return sistemasService.getEquipamento(pk_num_serie);
    }    
    
    @CrossOrigin(origins = "*")
    @PostMapping("/equipamentos")
    public ResponseEntity<String> criarEquipamento(@RequestBody String equipamentoJson) {
        return sistemasService.salvarEquipamento(equipamentoJson);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/equipamentos")
    public ResponseEntity<Void> deletarEquipamento(@RequestBody String equipamentoJson) {
        sistemasService.deletarEquipamento(equipamentoJson);
        return ResponseEntity.ok().build();
    }
    
    // ----- COMPUTADORES -----
    @CrossOrigin(origins = "*")
    @GetMapping("/computadores")
    public ResponseEntity<String> listarComputadores() {
        return sistemasService.getComputadores();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/computadores/{pk_computador}")
    public ResponseEntity<String> getComputador(@PathVariable Long pk_computador) {
        return sistemasService.getComputador(pk_computador);
    }   

    @PostMapping("/computadores")
    public ResponseEntity<String> criarComputador(@RequestBody String computadorJson) {
        return sistemasService.salvarComputador(computadorJson);
    }

    @DeleteMapping("/computadores")
    public ResponseEntity<Void> deletarComputador(@RequestBody String computadorJson) {
        sistemasService.deletarComputador(computadorJson);
        return ResponseEntity.ok().build();
    }

    // ----- LOJAS -----
    @PostMapping("/lojas")
    public void criarLoja(@RequestBody String lojaJson) {
        sistemasService.criarLoja(lojaJson);
    }

    @GetMapping("/lojas")
    public void listarLojas() {
        sistemasService.listarLojas();
    }

    @GetMapping("/lojas/{id}")
    public void buscarLoja(@PathVariable("id") int id) {
        sistemasService.buscarLoja(id);
    }

    @PutMapping("/lojas/{id}")
    public void atualizarLoja(@PathVariable("id") int id, @RequestBody String lojaAtualizada) {
        sistemasService.atualizarLoja(id, lojaAtualizada);
    }

    @DeleteMapping("/lojas/{id}")
    public void deletarLoja(@PathVariable("id") int id) {
        sistemasService.deletarLoja(id);
    }

    // ----- ENVIOS -----
    @PostMapping("/envios")
    public void criarEnvio(@RequestBody String envioJson) {
        sistemasService.criarEnvio(envioJson);
    }

    @GetMapping("/envios")
    public void listarEnvios() {
        sistemasService.listarEnvios();
    }

    @GetMapping("/envios/{fk}")
    public void buscarEnvio(@PathVariable("fk") String fkNumSerie) {
        sistemasService.buscarEnvio(fkNumSerie);
    }

    @PutMapping("/envios/{fk}")
    public void atualizarEnvio(@PathVariable("fk") String fkNumSerie, @RequestBody String envioAtualizado) {
        sistemasService.atualizarEnvio(fkNumSerie, envioAtualizado);
    }

    @DeleteMapping("/envios/{fk}")
    public void deletarEnvio(@PathVariable("fk") String fkNumSerie) {
        sistemasService.deletarEnvio(fkNumSerie);
    }
}
