package com.edson.foodapi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.edson.foodapi.domain.exception.BadRequestException;
import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;

	@Embedded
	private Endereco enderecoEntrega;

	@Enumerated(EnumType.STRING)
	private StatusPedido status = StatusPedido.CRIADO;

	@CreationTimestamp
	private OffsetDateTime dataCriacao;

	private OffsetDateTime dataConfirmacao;

	private OffsetDateTime dataCancelamento;

	private OffsetDateTime dataEntrega;

	@ManyToOne(fetch = FetchType.LAZY)
	private FormaPagamento formaPagamento;
	
	@ManyToOne
	private Restaurante restaurante;
	
	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id")
	private Usuario cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();

	public void calcularValorTotal() {
		this.getItens().forEach(ItemPedido::calcularPrecoTotal);

		this.subtotal = getItens().stream()
				.map(ItemPedido::getPrecoTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		this.valorTotal = this.subtotal.add(this.taxaFrete);
	}

	public void confirmar(){
		setStatus(StatusPedido.CONFIRMADO);
		setDataConfirmacao(OffsetDateTime.now());
	}

	public void entregar(){
		setStatus(StatusPedido.ENTREGUE);
		setDataEntrega(OffsetDateTime.now());
	}

	public void cancelar(){
		setStatus(StatusPedido.CANCELADO);
		setDataCancelamento(OffsetDateTime.now());
	}


	private void setStatus(StatusPedido novoStatusPedido){
		if(getStatus().naoPodeAlterarPara(novoStatusPedido)){
			throw new BadRequestException(String.format("O status %s não pode ser alterado para o status de %s",
					getStatus().getDescricao(), novoStatusPedido.getDescricao()));
		}

		this.status = novoStatusPedido;
	}

}
