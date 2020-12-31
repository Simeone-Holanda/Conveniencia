package model.entities;

public class Mercadorias {
	private int codProduct;
	private String nomeProduto;
	private double precoProduto;
	
	public Mercadorias() {}
	
	public Mercadorias(String nomeProduto, double precoProduto) {
		this.nomeProduto = nomeProduto;
		this.precoProduto = precoProduto;
	}
	
	public Mercadorias(int codProduct, String nomeProduto, double precoProduto) {
		this.codProduct = codProduct;
		this.nomeProduto = nomeProduto;
		this.precoProduto = precoProduto;
	}
	public int getCodProduct() {
		return codProduct;
	}
	public void setCodProduct(int codProduct) {
		this.codProduct = codProduct;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public double getPrecoProduto() {
		return precoProduto;
	}
	public void setPrecoProduto(double precoProduto) {
		this.precoProduto = precoProduto;
	}
	@Override
	public String toString() {
		return "Mercadorias [codProduct=" + codProduct + ", nomeProduto=" + nomeProduto + ", precoProduto="
				+ precoProduto + "]";
	}
}
