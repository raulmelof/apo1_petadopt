package model;

import java.time.LocalDate;
import java.util.List;



public class Adocao {
	private Integer codAdocao;
	private LocalDate dataAdocao;
	private String localAdocao;
	private Cliente adocaoCliente;
	private Funcionarios adocaoFuncionario;
	private String cpfCliente;
	
	private List<Animal> animais;
	



	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public Adocao(LocalDate dataAdocao, String localAdocao, Integer numReserva) {
		
		this.dataAdocao = dataAdocao;
		this.localAdocao = localAdocao;
	}
	
	public Integer getCodAdocao() {

		return codAdocao;
	}
	public void setCodAdocao(Integer codAdocao) {
		this.codAdocao = codAdocao;
	}
	public LocalDate getDataAdocao() {
		return dataAdocao;
	}
	public void setDataAdocao(LocalDate datedataAdocao) {
		this.dataAdocao = datedataAdocao;
	}
	public String getLocalAdocao() {
		return localAdocao;
	}
	public void setLocalAdocao(String localAdocao) {
		this.localAdocao = localAdocao;
	}
	public Cliente getAdocaoCliente() {
		return adocaoCliente;
	}
	public void setAdocaoCliente(Cliente adocaoCliente) {
		this.adocaoCliente = adocaoCliente;
	}
	public Funcionarios getAdocaoFuncionario() {
		return adocaoFuncionario;
	}
	public void setAdocaoFuncionario(Funcionarios adocaoFuncionario) {
		this.adocaoFuncionario = adocaoFuncionario;
	}
}
