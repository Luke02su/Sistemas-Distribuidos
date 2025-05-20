package com.sd.prj_petshop.repositories;

import com.sd.prj_petshop.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
