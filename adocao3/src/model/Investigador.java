package model;

public class Investigador extends Funcionarios {
	private Integer idFuncionario;
	private String autInvestigador;
	
	public Investigador (Integer idFuncionario, String autInvestigador) {
		this.idFuncionario = idFuncionario;
		this.autInvestigador = autInvestigador;
	}
	
	public Integer getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public String getAutInvestigador() {
		return autInvestigador;
	}
	public void setAutInvestigador(String autInvestigador) {
		this.autInvestigador = autInvestigador;
	}

}
