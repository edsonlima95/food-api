package com.edson.foodapi.domain.model;

import lombok.Getter;

import java.util.List;

@Getter
public enum StatusPedido {

	CRIADO("criado"),
	CONFIRMADO("confirmado", CRIADO),
	ENTREGUE("entregue", CONFIRMADO),
	CANCELADO("cancelado", CRIADO);

	public final String descricao;

	public final List<StatusPedido> statusPedidos;

	StatusPedido(String descricao, StatusPedido... statusPedidos) {
		this.descricao = descricao;
		this.statusPedidos = List.of(statusPedidos);
	}

	public boolean naoPodeAlterarPara(StatusPedido novoStatus) {
		return !novoStatus.statusPedidos.contains(this);
	}
}