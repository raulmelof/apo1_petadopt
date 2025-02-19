package banco;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Cliente.statusCliente;





public class clienteBanco {
	private DBConnection connection;

	public clienteBanco() {
		this.connection = new DBConnection();
	}
	
	public void inserirCliente(Cliente cliente) {
	    try  {
	    	String sql = "call inserirCliente (?, ?, ?, ?)";
	    	PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	    	
            stmt.setString(1, cliente.getCpfCliente());
            stmt.setString(2, cliente.getNomeCliente());
            stmt.setString(3, cliente.getEnderecoCliente());
            stmt.setString(4, cliente.getTelCliente());


            stmt.execute();
            System.out.println("Cliente inserido com sucesso!");
            stmt.close();
	        
	    } catch (SQLException e) {
	    	throw new RuntimeException(e);
	    }
	}
	
	public void alterarCliente(Cliente cliente){
		try {
			String sql = "call alterarCliente (?, ?, ?, ?)";
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			
            stmt.setString(1, cliente.getCpfCliente());
            stmt.setString(2, cliente.getNomeCliente());
            stmt.setString(3, cliente.getEnderecoCliente());
            stmt.setString(4, cliente.getTelCliente());
            
            stmt.execute();
            System.out.println("Cliente alterado com sucesso!");
            stmt.close();
			
		} catch (SQLException e) {
	    	throw new RuntimeException(e);
	    }
	}
	
	public Cliente consultarCliente(Cliente cliente) {
		try {
			String sql = "call consultarCliente ("+cliente.getCpfCliente()+")";
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs != null && rs.next()) {
				cliente.setCpfCliente(rs.getString("cpfCliente"));
				cliente.setNomeCliente(rs.getString("nomeCliente"));
				cliente.setEnderecoCliente(rs.getString("telCliente"));
				cliente.setTelCliente(rs.getString("enderecoCliente"));
				String status = rs.getString("statusCliente");
				cliente.setStatus(statusCliente.valueOf(status));
			}
			return cliente;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Cliente> listarClientes() {
	    List<Cliente> clientesList = new ArrayList<>();
	    
	    try {
	        String sql = "call listarClientes()";
	        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();
	        

	        while (rs.next()) {
	        	Cliente cliente = new Cliente();
	        	cliente.setCpfCliente(rs.getString("cpfCliente"));
				cliente.setNomeCliente(rs.getString("nomeCliente"));
				cliente.setEnderecoCliente(rs.getString("telCliente"));
				cliente.setTelCliente(rs.getString("enderecoCliente"));
				String statusString = rs.getString("statusCliente");
				cliente.setStatus(Cliente.statusCliente.valueOf(statusString));
				clientesList.add(cliente);
	        }
	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return clientesList;
	}
	
	public void removerCliente(Cliente cliente) {
	    try {
	        String sql = "call removerCliente(?)";
	        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);

	        stmt.setString(1, cliente.getCpfCliente());

	        stmt.execute();
	        System.out.println("Cliente com cpf " + cliente.getCpfCliente() + " removido com sucesso!");

	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Erro ao remover o cliente: " + e.getMessage());
	    }
	}
	
	public void aprovarCliente(String cpfCliente) {
	    try {
	        String sql = "CALL aprovarCliente(?)"; // Nome do procedimento armazenado
	        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	        stmt.setString(1, cpfCliente); // Define o CPF do cliente como parâmetro
	        stmt.execute();

	        System.out.println("Cliente com CPF " + cpfCliente + " foi aprovado!");

	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Erro ao aprovar o cliente: " + e.getMessage());
	    }
	}
	
	public void recusarCliente(String cpfCliente) {
	    try {
	        String sql = "CALL recusarCliente(?)"; // Nome do procedimento armazenado
	        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	        stmt.setString(1, cpfCliente); // Define o CPF do cliente como parâmetro
	        stmt.execute();

	        System.out.println("Cliente com CPF " + cpfCliente + " foi recusado!");

	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Erro ao recusar o cliente: " + e.getMessage());
	    }
	}
	
	public void adcListaNegra(String cpfCliente) {
	    try {
	        String sql = "CALL adcListaNegra(?)"; // Nome do procedimento armazenado
	        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	        stmt.setString(1, cpfCliente); // Define o CPF do cliente como parâmetro
	        stmt.execute();

	        System.out.println("Cliente com CPF " + cpfCliente + " foi adicionado a lista negra!");

	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Erro ao adicionar o cliente: " + e.getMessage());
	    }
	}







}
