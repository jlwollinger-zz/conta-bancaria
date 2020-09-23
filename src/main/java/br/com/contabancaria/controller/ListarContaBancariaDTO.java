package br.com.contabancaria.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

@Getter
@Setter
public class ListarContaBancariaDTO {

	private static final int DEFAULT_PAGE_SIZE = 25;
	private static final int DEFAULT_OFFSET = 0;

	private int pageSize = DEFAULT_PAGE_SIZE;
	private int pageIndex = DEFAULT_OFFSET;
	private String sortField;
	private String sortOrder;
	private String nome;
	private Long agencia;
	private Boolean chequeEspecialLiberado;

	public void allRecords() {
		pageIndex = DEFAULT_OFFSET;
		pageSize = Integer.MAX_VALUE;
	}

	public PageRequest getPage(){
		return PageRequest.of(this.getPageIndex(), this.getPageSize());
	}
}
