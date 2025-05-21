package com.sd.prj_planta_serv1.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "flor")
public class Flor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idflor;

    @Column(nullable = false)
    private double comprimento_sepala;

    @Column(nullable = false)
    private double largura_sepala;

    @Column(nullable = false)
    private double comprimento_petala;

    @Column(nullable = false)
    private double largura_petala;

    @Column(nullable = false)
    private String cor;

    @Column(nullable = false)
    private String especieTipo;

   // JsonProperty(access = JsonProperty.Acess.WRITE_ONLY)  não busca as outras plantas correlacionadas
    @ManyToOne(fetch = FetchType.EAGER) //.LAZY
    @JoinColumn(name = "idplanta", nullable = false)
    private Planta planta;

    public double getComprimento_sepala() {
        return comprimento_sepala;
    }

    public void setComprimento_sepala(double comprimento_sepala) {
        this.comprimento_sepala = comprimento_sepala;
    }

    public long getIdflor() {
        return idflor;
    }

    public void setIdflor(long idflor) {
        this.idflor = idflor;
    }

    public double getLargura_sepala() {
        return largura_sepala;
    }

    public void setLargura_sepala(double largura_sepala) {
        this.largura_sepala = largura_sepala;
    }

    public double getComprimento_petala() {
        return comprimento_petala;
    }

    public void setComprimento_petala(double comprimento_petala) {
        this.comprimento_petala = comprimento_petala;
    }

    public double getLargura_petala() {
        return largura_petala;
    }

    public void setLargura_petala(double largura_petala) {
        this.largura_petala = largura_petala;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getEspecieTipo() {
        return especieTipo;
    }

    public void setEspecieTipo(String especieTipo) {
        this.especieTipo = especieTipo;
    }

    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }
}
