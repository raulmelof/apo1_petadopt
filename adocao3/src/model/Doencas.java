package model;

import java.time.LocalDate;

public class Doencas {
	private String nomeDoenca;
	private LocalDate dataDoenca;
	private String tratamentoDoenca;
	private Integer codProntuario;
	
	
	public Integer getCodProntuario() {
		return codProntuario;
	}

	public void setCodProntuario(Integer codProntuario) {
		this.codProntuario = codProntuario;
	}
	
	public Doencas() {
		
	}
	
	
	public Doencas (String nomeDoenca, LocalDate dataDoenca, String tratamentoDoenca, Integer codProntuario) {
		this.nomeDoenca = nomeDoenca;
		this.dataDoenca = dataDoenca;
		this.tratamentoDoenca = tratamentoDoenca;
		this.codProntuario = codProntuario;
	}
	
	
	public String getNomeDoenca() {
		return nomeDoenca;
	}
	public void setNomeDoenca(String nomeDoenca) {
		this.nomeDoenca = nomeDoenca;
	}
	public LocalDate getDataDoenca() {
		return dataDoenca;
	}
	public void setDataDoenca(LocalDate dataDoenca) {
		this.dataDoenca = dataDoenca;
	}
	public String getTratamentoDoenca() {
		return tratamentoDoenca;
	}
	public void setTratamentoDoenca(String tratamentoDoenca) {
		this.tratamentoDoenca = tratamentoDoenca;
	}
}
