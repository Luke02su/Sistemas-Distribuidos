package com.sd.prj_petshop.services;

import com.sd.prj_petshop.models.Pessoa;
import com.sd.prj_petshop.repositories.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public List<Pessoa> getPessoas() {
        return pessoaRepository.findAll();
        //return List.of(new Pessoa(1, "Lucas", "150.975.886-06", "16/08/2004"));
    }

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa savePessoas(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

}
