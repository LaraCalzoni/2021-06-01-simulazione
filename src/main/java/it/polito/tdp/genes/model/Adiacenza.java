package it.polito.tdp.genes.model;

public class Adiacenza {

	
	private Genes gene1;
	private Genes gene2;
	private double correlazione;
	private double peso;
	
	
	public Adiacenza(Genes gene1, Genes gene2, double correlazione) {
		super();
		this.gene1 = gene1;
		this.gene2 = gene2;
		this.correlazione = correlazione;
		peso = 0.0;
	}
	public Genes getGene1() {
		return gene1;
	}
	public void setGene1(Genes gene1) {
		this.gene1 = gene1;
	}
	public Genes getGene2() {
		return gene2;
	}
	public void setGene2(Genes gene2) {
		this.gene2 = gene2;
	}
	public double getCorrelazione() {
		return correlazione;
	}
	public void setCorrelazione(double correlazione) {
		this.correlazione = correlazione;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	
}
