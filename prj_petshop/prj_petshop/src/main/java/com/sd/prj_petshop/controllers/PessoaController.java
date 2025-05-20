package com.sd.prj_petshop.controllers;

import com.sd.prj_petshop.models.Pessoa;
import com.sd.prj_petshop.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pessoas")

public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> getAllBooks() {
        return pessoaService.getPessoas();
    }

    @PostMapping
    public Pessoa savePessoas(@RequestBody Pessoa pessoa) {
        return pessoaService.savePessoas(pessoa);
    }
}
