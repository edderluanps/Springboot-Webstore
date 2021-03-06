package com.project.webstore.domains;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.project.webstore.domains.enums.EstadoPagamento;
import javax.persistence.Entity;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {
    
    private static final long serialVersionUID = 1L;    

    private Integer numParcelas;

    public PagamentoComCartao() {
    }

    public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedidos pedido, Integer numParcelas) {
        super(id, estado, pedido);

        this.numParcelas = numParcelas;
    }

    public Integer getNumParcelas() {
        return numParcelas;
    }

    public void setNumParcelas(Integer numParcelas) {
        this.numParcelas = numParcelas;
    }

}
