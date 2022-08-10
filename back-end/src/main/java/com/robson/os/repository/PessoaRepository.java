package com.robson.os.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robson.os.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

	Optional<Pessoa> findByCpf(String cpf);
}
