package com.project.webstore.domains;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ItemPedido implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private ItemPedidoPK id = new ItemPedidoPK();
    
    private Double desconto;
    private Integer Qtd;
    private Double preco;

    public ItemPedido() {
    }

    public ItemPedido(Pedidos pedido, Products produto, Double desconto, Integer Qtd, Double preco) {
        id.setPedido(pedido);
        id.setProduto(produto);
        this.desconto = desconto;
        this.Qtd = Qtd;
        this.preco = preco;
    }
    
    public Pedidos getPedidos(){
        return id.getPedido();
    }

    public Products getProduto() {
        return id.getProduto();
    }

    public ItemPedidoPK getId() {
        return id;
    }

    public void setId(ItemPedidoPK id) {
        this.id = id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQtd() {
        return Qtd;
    }

    public void setQtd(Integer Qtd) {
        this.Qtd = Qtd;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemPedido other = (ItemPedido) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
