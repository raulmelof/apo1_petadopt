package model;

public class Animal {
	
private String nomeAnimal;
private Integer idAnimal;
private String sexoAnimal;
private String racaAnimal;
private Prontuario animalProntuario; // relacionamento 1:1
private statusAnimal status;

public enum statusAnimal {
    DISPONIVEL,
    INDISPONIVEL,
    RESERVADO,
    ADOTADO
}


public Animal () {
	
}

public Animal (int idAnimal, String nomeAnimal, String sexoAnimal, String racaAnimal) {
	this.idAnimal = idAnimal;
    this.nomeAnimal = nomeAnimal;
    this.sexoAnimal = sexoAnimal;
    this.racaAnimal = racaAnimal;
    this.status = statusAnimal.INDISPONIVEL;
}

public String getNomeAnimal() {
return nomeAnimal;
}
public void setNomeAnimal(String nomeAnimal) {
this.nomeAnimal = nomeAnimal;
}
public Integer getIdAnimal() {
return idAnimal;
}
public void setIdAnimal(Integer idAnimal) {
this.idAnimal = idAnimal;
}
public String getSexoAnimal() {
return sexoAnimal;
}
public void setSexoAnimal(String sexoAnimal) {
this.sexoAnimal = sexoAnimal;
}
public String getRacaAnimal() {
return racaAnimal;
}
public void setRacaAnimal(String racaAnimal) {
this.racaAnimal = racaAnimal;
}
public Prontuario getAnimalProntuario() {
return animalProntuario;
}
public void setAnimalProntuario(Prontuario animalProntuario) {
this.animalProntuario = animalProntuario;
}


public statusAnimal getStatus() {
	return status;
}

public void setStatus(statusAnimal status) {
	this.status = status;
}

}
