package br.com.contabancaria.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "conta_bancaria")
public class ContaBancaria {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "numero_conta")
	private Long numeroConta;

	@Column(name = "agencia")
	private Long agencia;

	@Column(name = "cheque_especial_liberado")
	private Integer chequeEspecialLiberado;

	@Column(name = "saldo")
	private BigDecimal saldo;

	@Column(name = "cheque_especial")
	private BigDecimal chequeEspecial;

	@Column(name = "taxa")
	private BigDecimal taxa;
}
