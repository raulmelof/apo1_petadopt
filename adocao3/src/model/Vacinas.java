package model;

import java.time.LocalDate;

public class Vacinas {
	private String nomeVacina;
	private LocalDate dataVacina;
	private Integer codProntuario;
	
	
	
	public String getNomeVacina() {
		return nomeVacina;
	}
	public void setNomeVacina(String nomeVacina) {
		this.nomeVacina = nomeVacina;
	}
	public LocalDate getDataVacina() {
		return dataVacina;
	}
	public void setDataVacina(LocalDate dataVacina) {
		this.dataVacina = dataVacina;
	}
	public Integer getCodProntuario() {
		return codProntuario;
	}
	public void setCodProntuario(Integer codProntuario) {
		this.codProntuario = codProntuario;
	}

}
