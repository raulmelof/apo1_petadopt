package model;


public class Cliente {
	private String nomeCliente;
	private String enderecoCliente;
	private String cpfCliente;
	private String telCliente;
	private statusCliente status;
	
	public enum statusCliente{
		EM_INVESTIGACAO,
		APROVADO,
		RECUSADO,
		LISTA_NEGRA
	}
	
	public statusCliente getStatus() {
		return status;
	}
	public void setStatus(statusCliente status) {
		this.status = status;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getEnderecoCliente() {
		return enderecoCliente;
	}
	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}
	public String getCpfCliente() {
		return cpfCliente;
	}
	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	public String getTelCliente() {
		return telCliente;
	}
	public void setTelCliente(String telCliente) {
		this.telCliente = telCliente;
	}


}
