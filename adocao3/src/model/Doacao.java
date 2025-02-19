package model;

import java.time.LocalDate;

public class Doacao {
	private Integer codDoacao;
	private LocalDate dataDoacao;
	private String localDoacao;
	private Animal doacaoAnimal;
	private Doador doacaoDoador;
	
	
	public Doacao(LocalDate dataDoacao, String localDoacao) {
		
		this.dataDoacao = dataDoacao;
		this.localDoacao = localDoacao;
	}
	
	public Integer getCodDoacao() {
		return codDoacao;
	}
	public Animal getDoacaoAnimal() {
		return doacaoAnimal;
	}
	public void setDoacaoAnimal(Animal doacaoAnimal) {
		this.doacaoAnimal = doacaoAnimal;
	}
	public Doador getDoacaoDoador() {
		return doacaoDoador;
	}
	public void setDoacaoDoador(Doador doacaoDoador) {
		this.doacaoDoador = doacaoDoador;
	}
	public void setCodDoacao(Integer codDoacao) {
		this.codDoacao = codDoacao;
	}
	public LocalDate getDataDoacao() {
		return dataDoacao;
	}
	public void setDataDoacao(LocalDate dataDoacao) {
		this.dataDoacao = dataDoacao;
	}
	public String getLocalDoacao() {
		return localDoacao;
	}
	public void setLocalDoacao(String localDoacao) {
		this.localDoacao = localDoacao;
	}
}
