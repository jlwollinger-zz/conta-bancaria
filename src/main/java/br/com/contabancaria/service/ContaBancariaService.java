package br.com.contabancaria.service;


import br.com.contabancaria.controller.ListarContaBancariaDTO;
import br.com.contabancaria.entity.ContaBancaria;
import br.com.contabancaria.entity.ContaBancariaDTO;
import br.com.contabancaria.repository.ContaBancariaRepository;
import br.com.contabancaria.util.ListaPaginada;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static br.com.contabancaria.entity.QContaBancaria.contaBancaria;
import static br.com.contabancaria.util.QueryDslUtil.nullSafeEq;

@Service
public class ContaBancariaService {

	private final ContaBancariaRepository contaBancariaRepository;

	public ContaBancariaService(ContaBancariaRepository contaBancariaRepository) {
		this.contaBancariaRepository = contaBancariaRepository;
	}

	public void insert(ContaBancaria cb){
		if(Objects.isNull(cb.getNome())){
			throw new RuntimeException("Nome vazio!");
		} else if(Objects.isNull(cb.getNumeroConta())){
			throw new RuntimeException("Número da conta vazio!");
		} else if(Objects.isNull(cb.getAgencia())){
			throw new RuntimeException("Agência vazio!");
		} else if(Objects.isNull(cb.getChequeEspecial())){
			throw new RuntimeException("Cheque especial vazio!");
		}

		this.contaBancariaRepository.save(cb);
	}

	public ContaBancaria obter(String numeroConta){
		return this.contaBancariaRepository.findByNumeroConta(numeroConta);
	}

	public ContaBancariaDTO detalhar(Long id){
		return ContaBancariaDTO.from(this.contaBancariaRepository.findById(id).get());
	}

	public ListaPaginada<ContaBancariaDTO> list(ListarContaBancariaDTO dto){
		BooleanBuilder bb = new BooleanBuilder();
		bb.and(nullSafeEq(contaBancaria.nome, dto.getNome()));
		bb.and(nullSafeEq(contaBancaria.agencia, dto.getAgencia()));
		if(dto.getChequeEspecialLiberado() != null){
			if(dto.getChequeEspecialLiberado()){
				bb.and(contaBancaria.chequeEspecialLiberado.eq(1));
			} else {
				bb.and(contaBancaria.chequeEspecialLiberado.eq(0));
			}
		}

		Page<ContaBancaria> page = contaBancariaRepository.findAll(bb, dto.getPage());
		return new ListaPaginada<>(page.getTotalElements(), page.getTotalPages(),
				ContaBancariaDTO.from(page.getContent()));
	}

	public void delete(Long id){
		this.contaBancariaRepository.deleteById(id);
	}

}
