package br.com.contabancaria.util;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ListaPaginada<T> {

	private long total;

	private int pages;

	private Collection<T> itens;

	public ListaPaginada() {
	}

	public ListaPaginada(Page<T> page) {
		this(page.getTotalElements(), page.getTotalPages(), page.getContent());
	}

	public ListaPaginada(long total, int pages, Collection<T> itens) {
		this.total = total;
		this.pages = pages;
		this.itens = itens;
	}

}
