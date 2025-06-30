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
public class GestorController {

    @Autowired
    private SistemasService sistemasService;

    @GetMapping("/equipamentos/simples")
    public ResponseEntity<String> listarEquipamentos() {
        return sistemasService.getEquipamentos();
    }

    @GetMapping("/equipamentos")
    public ResponseEntity<List<Map<String, Object>>> listarEquipamentosComEnvio() {
        List<Map<String, Object>> equipamentos = sistemasService.listarEquipamentosComDataDeEnvio();
        return ResponseEntity.ok(equipamentos);
    }

    @GetMapping("/equipamentos/{pk_num_serie}")
    public ResponseEntity<String> getEquipamento(@PathVariable String pk_num_serie) {
        return sistemasService.getEquipamento(pk_num_serie);
    }    
    
    @PostMapping("/equipamentos")
    public ResponseEntity<String> criarEquipamento(@RequestBody String equipamentoJson) {
        return sistemasService.salvarEquipamento(equipamentoJson);
    }

    @DeleteMapping("/equipamentos")
    public ResponseEntity<Void> deletarEquipamento(@RequestBody String equipamentoJson) {
        sistemasService.deletarEquipamento(equipamentoJson);
        return ResponseEntity.ok().build();
    }
    
    // ----- COMPUTADORES -----
    @GetMapping("/computadores")
    public ResponseEntity<String> listarComputadores() {
        return sistemasService.getComputadores();
    }

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
    public ResponseEntity<String> listarLojas() {
        return sistemasService.getLojas();
    }

    @GetMapping("/lojas/{pk_loja}")
    public ResponseEntity<String> buscarLoja(@PathVariable("pk_loja") Long pk_loja) {
        return sistemasService.buscarLoja(pk_loja);
    }

    @PutMapping("/lojas/{pk_loja}")
    public ResponseEntity<String> atualizarEnvio(@PathVariable("pk_loja") Long pk_loja, @RequestBody String lojaAtualizada) {
        return sistemasService.atualizarLoja(pk_loja, lojaAtualizada);
    }

    @DeleteMapping("/lojas/{pk_loja}")
    public ResponseEntity<Void> deletarLoja(@PathVariable("pk_loja") Long pk_loja) {
        return sistemasService.deletarLoja(pk_loja);
    }

    // ----- ENVIOS -----
    @PostMapping("/envios")
    public void criarEnvio(@RequestBody String envioJson) {
        sistemasService.criarEnvio(envioJson);
    }

    @GetMapping("/envios")
    public ResponseEntity<String> listarEnvios() {
        return sistemasService.getEnvios();
    }
    
    @GetMapping("/envios/{fk_num_serie}")
    public ResponseEntity<String> buscarEnvio(@PathVariable("fk_num_serie") String fk_num_serie) {
        return sistemasService.buscarEnvio(fk_num_serie);
    }

    @PutMapping("/envios/{fk_num_serie}")
    public ResponseEntity<String> atualizarEnvio(@PathVariable("fk_num_serie") String fk_num_serie, @RequestBody String envioAtualizado) {
        return sistemasService.atualizarEnvio(fk_num_serie, envioAtualizado);
    }

    @DeleteMapping("/envios/{fk_num_serie}")
    public ResponseEntity<Void> deletarEnvio(@PathVariable("fk_num_serie") String fk_num_serie) {
        return sistemasService.deletarEnvio(fk_num_serie);
    }
}
