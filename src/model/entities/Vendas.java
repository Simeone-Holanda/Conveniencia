package model.entities;

public class Vendas {
	private int codProduto;
	private String name;
	private Double preco;
	private Integer quantidade;
	private Double valor;
	
	public Vendas() {}
	
	public Vendas(Integer codProduto,String name, Double preco) {
		this.codProduto = codProduto;
		this.name = name;
		this.preco = preco;
	}
	public Vendas(Integer codProduto, String name, Double preco, Integer quantidade, Double valor) {
		this.codProduto = codProduto;
		this.name = name;
		this.preco = preco;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public Integer getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Integer codProduto) {
		this.codProduto = codProduto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValor() {
		return quantidade * preco;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "codProduto = " + codProduto + ", name = " + name + ", preco = " + preco + ", quantidade = " + quantidade
				+ ", valor = " + valor;
	}
	
	
	
}
