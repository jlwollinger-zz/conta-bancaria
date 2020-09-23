package br.com.contabancaria.entity;

import br.com.contabancaria.util.NumberFormatUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContaBancariaDTO {

	private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

	private String nome;
	private String numeroContaAgencia;
	private String chequeEspecialLiberado;
	private String valorCorrenteChequeEspecial;
	private String valorChequeEspecialDiaSeguinte;
	private String saldo;

	public static ContaBancariaDTO from(ContaBancaria cb){
		ContaBancariaDTO dto = new ContaBancariaDTO();
		dto.nome = cb.getNome();
		dto.numeroContaAgencia = cb.getNumeroConta() + " / " + cb.getAgencia();
		if(cb.getChequeEspecialLiberado() == 0){
			dto.chequeEspecialLiberado = "Não liberado";
		} else {
			dto.chequeEspecialLiberado = "Liberado";
		}
		dto.valorCorrenteChequeEspecial = NumberFormatUtil.currencyFormat(cb.getChequeEspecial());
		dto.valorChequeEspecialDiaSeguinte = NumberFormatUtil.currencyFormat(cb.getChequeEspecial().multiply(cb.getTaxa().divide(ONE_HUNDRED)).add(cb.getChequeEspecial()));
		dto.saldo = NumberFormatUtil.currencyFormat(cb.getSaldo());
		return dto;
	}

	public static List<ContaBancariaDTO> from(List<ContaBancaria> list){
		List<ContaBancariaDTO> dtos = list
				.stream()
				.map(ContaBancariaDTO::from)
				.collect(Collectors.toList());
		return dtos;
	}
}
