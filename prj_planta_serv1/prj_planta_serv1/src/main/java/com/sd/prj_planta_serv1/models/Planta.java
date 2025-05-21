package com.sd.prj_planta_serv1.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "planta")
public class Planta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idplanta;

    @Column(nullable = false, unique = true)
    private String genero;

    @OneToMany(mappedBy = "planta", fetch = FetchType.LAZY)
    Set<Flor> flores = new HashSet<Flor>();

    public long getIdplanta() {
        return idplanta;
    }

    public void setIdplanta(long idplanta) {
        this.idplanta = idplanta;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    //public Set<Flor> getFlores() {
    //    return flores;
    //}

    //public void setFlores(Set<Flor> flores) {
       // this.flores = flores;
    //}
}
