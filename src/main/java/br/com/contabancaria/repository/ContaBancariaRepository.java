package br.com.contabancaria.repository;

import br.com.contabancaria.entity.ContaBancaria;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;


public interface ContaBancariaRepository extends CrudRepository<ContaBancaria, Long>, QuerydslPredicateExecutor<ContaBancaria> {

	ContaBancaria findByNumeroConta(String numeroConta);
}
