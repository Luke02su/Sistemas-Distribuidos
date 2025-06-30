package com.sd.prj_controle_equipamentos_ti_gestor.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SistemasService {
private RestTemplate restTemplate;

    private String url_serv1_equipamentos;
    private String url_serv1_computadores;
    private String url_serv2_lojas;
    private String url_serv2_envios;

    public SistemasService() {
        restTemplate = new RestTemplate();
        this.url_serv1_equipamentos = "http://localhost:8080/api/equipamentos";
        this.url_serv1_computadores = "http://localhost:8080/api/computadores";
        this.url_serv2_lojas = "http://localhost:5000/api/lojas";
        this.url_serv2_envios = "http://localhost:5000/api/envios";
    }

    //API equipamentos
    public ResponseEntity<String> getEquipamentos() {
        ResponseEntity<String> response = restTemplate.getForEntity(url_serv1_equipamentos, String.class);
        return response;
    }

    public ResponseEntity<String> getEquipamento(String pk_num_serie) {
        String url = url_serv1_equipamentos + "/" + pk_num_serie;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response;
    }
    
    public ResponseEntity<String> salvarEquipamento(String equipamentoJson) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(equipamentoJson, headers);
        return restTemplate.postForEntity(url_serv1_equipamentos, request, String.class);
    }
    
    public void deletarEquipamento(String equipamentoJson) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(equipamentoJson, headers);
        restTemplate.exchange(url_serv1_equipamentos, HttpMethod.DELETE, request, String.class);
    }

    //API computadores
    public ResponseEntity<String> getComputadores() {
        ResponseEntity<String> response = restTemplate.getForEntity(url_serv1_computadores, String.class);
        return response;
    }

    public ResponseEntity<String> getComputador(Long pk_computador) {
        String url = url_serv1_computadores + "/" + pk_computador;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response;
    }

    public ResponseEntity<String> salvarComputador(String computadorJson) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(computadorJson, headers);
        return restTemplate.postForEntity(url_serv1_computadores, request, String.class);
    }

    public void deletarComputador(String computadorJson) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(computadorJson, headers);
        restTemplate.exchange(url_serv1_computadores, HttpMethod.DELETE, request, String.class);
    }

    //API loja
    public void criarLoja(String jsonLoja) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jsonLoja, headers);
        restTemplate.postForEntity(url_serv2_lojas, request, String.class);
    }

    public ResponseEntity<String> getLojas() {
        return restTemplate.getForEntity(url_serv2_lojas, String.class);
    }

    public ResponseEntity<String> buscarLoja(Long pk_loja) {
        String url = url_serv2_lojas + "/" + pk_loja;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response;
    }

    public ResponseEntity<String> atualizarLoja(Long pk_loja, String jsonLojaAtualizada) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jsonLojaAtualizada, headers);
        String url = url_serv2_lojas + "/" + pk_loja;
        ResponseEntity<String> pythonResponse = restTemplate.exchange(
            url,
            HttpMethod.PUT,
            request,
            String.class 
        );
        return ResponseEntity.status(pythonResponse.getStatusCode()).body(pythonResponse.getBody());
    }

    public ResponseEntity<Void> deletarLoja(Long pk_loja) {
        System.out.println(url_serv2_lojas + "/" + pk_loja);
        restTemplate.delete(url_serv2_lojas + "/" + pk_loja);
        return ResponseEntity.noContent().build();
    }

    public void criarEnvio(String jsonEnvio) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jsonEnvio, headers);
        restTemplate.postForEntity(url_serv2_envios, request, String.class);
    }
    
    public ResponseEntity<String> getEnvios() {
        return restTemplate.getForEntity(url_serv2_envios, String.class);
    }
    
    public ResponseEntity<String> buscarEnvio(String fk_num_serie) {
        String url = url_serv2_envios + "/" + fk_num_serie;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response;
    }
    
    
    public ResponseEntity<String> atualizarEnvio(String fk_num_serie, String jsonEnvioAtualizado) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jsonEnvioAtualizado, headers);
        String url = url_serv2_envios + "/" + fk_num_serie;
        ResponseEntity<String> pythonResponse = restTemplate.exchange(
            url,
            HttpMethod.PUT,
            request,
            String.class 
        );
        return ResponseEntity.status(pythonResponse.getStatusCode()).body(pythonResponse.getBody());
    }

    public ResponseEntity<Void> deletarEnvio(String fk_num_serie) {
        System.out.println(url_serv2_envios + "/" + fk_num_serie);
        restTemplate.delete(url_serv2_envios + "/" + fk_num_serie);
        return ResponseEntity.noContent().build();
    }

    public List<Map<String, Object>> listarEquipamentosComDataDeEnvio() {
        try {
            // Busca equipamentos do serviço 1
            ResponseEntity<String> respEquip = restTemplate.getForEntity(url_serv1_equipamentos, String.class);
            String jsonEquipamentos = respEquip.getBody();

            // Busca envios do serviço 2
            String jsonEnvios = restTemplate.getForObject(url_serv2_envios, String.class);

            ObjectMapper mapper = new ObjectMapper();

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> equipamentos = mapper.readValue(jsonEquipamentos, List.class);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> envios = mapper.readValue(jsonEnvios, List.class);

            // Mapeia envios pelo fk_num_serie para facilitar busca
            Map<String, Map<String, Object>> mapaEnvios = new HashMap<>();
            for (Map<String, Object> envio : envios) {
                String fkNumSerie = (String) envio.get("fk_num_serie");
                mapaEnvios.put(fkNumSerie, envio);
            }

            // Para cada equipamento, adiciona o campo data_envio do envio relacionado
            for (Map<String, Object> equipamento : equipamentos) {
                String numSerie = (String) equipamento.get("pk_num_serie");
                Map<String, Object> envioRelacionado = mapaEnvios.get(numSerie);
                if (envioRelacionado != null) {
                    equipamento.put("data_envio", envioRelacionado.get("data_envio"));
                } else {
                    equipamento.put("data_envio", null);
                }
            }

            return equipamentos;

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    
}
