package model;

public class Veterinario extends Funcionarios {
	private Integer idFuncionario;
	private String crmvVeterinario;
	
	public Veterinario (Integer idFuncionario, String crmvVeterinario) {
		this.idFuncionario = idFuncionario;
		this.crmvVeterinario = crmvVeterinario;
	}

	public String getCrmvVeterinario() {
		return crmvVeterinario;
	}

	public void setCrmvVeterinario(String crmvVeterinario) {
		this.crmvVeterinario = crmvVeterinario;
	}
	
	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
}
