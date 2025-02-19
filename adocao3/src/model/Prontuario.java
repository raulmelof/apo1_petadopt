package model;
import java.util.List;

public class Prontuario {
	private String idadeAnimal;
	private Float pesoAnimal;
	private String higieneAnimal;
	private Integer codProntuario;
	private String tipoAnimal;
	private int idAnimal;
	private List<String> nomesDoencas;
	
	public int getIdAnimal() {
		return idAnimal;
	}
	public void setIdAnimal(int idAnimal) {
		this.idAnimal = idAnimal;
	}
	private Doencas prontuarioDoencas;
	
	
	
	public String getIdadeAnimal() {
		return idadeAnimal;
	}
	public void setIdadeAnimal(String idadeAnimal) {
		this.idadeAnimal = idadeAnimal;
	}
	public Float getPesoAnimal() {
		return pesoAnimal;
	}
	public void setPesoAnimal(Float pesoAnimal) {
		this.pesoAnimal = pesoAnimal;
	}
	public String getHigieneAnimal() {
		return higieneAnimal;
	}
	public void setHigieneAnimal(String higieneAnimal) {
		this.higieneAnimal = higieneAnimal;
	}
	public Integer getCodProntuario() {
		return codProntuario;
	}
	public void setCodProntuario(Integer codProntuario) {
		this.codProntuario = codProntuario;
	}
	public Doencas getProntuarioDoencas() {
		return prontuarioDoencas;
	}
	public void setProntuarioDoencas(Doencas prontuarioDoencas) {
		this.prontuarioDoencas = prontuarioDoencas;
	}
	public String getTipoAnimal() {
		return tipoAnimal;
	}
	public void setTipoAnimal(String tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}	
	public List<String> getNomesDoencas() {
        return nomesDoencas;
    }

    public void setNomesDoencas(List<String> nomesDoencas) {
        this.nomesDoencas = nomesDoencas;
    }
}
