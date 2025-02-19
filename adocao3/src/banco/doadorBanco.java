package banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.Doador;




public class doadorBanco {
	private DBConnection connection;

	public doadorBanco() {
		this.connection = new DBConnection();
	}

	public void inserirDoador(Doador doador) {
	    try  {
	    	String sql = "call inserirDoador (?, ?, ?, ?)";
	    	PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	   
	    	
            stmt.setString(1, doador.getCpfDoador());
            stmt.setString(2, doador.getNomeDoador());
            stmt.setString(3, doador.getTelDoador());
            stmt.setString(4, doador.getEnderecoDoador());

            stmt.execute();
            System.out.println("Doador inserido com sucesso!");
            stmt.close();
	        
	    } catch (SQLException e) {
	    	throw new RuntimeException(e);
	    }
	}
	
	public void alterarDoador(Doador doador){
		try {
			String sql = "call alterarDoador (?, ?, ?, ?)";
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			
			stmt.setString(1, doador.getCpfDoador());
			stmt.setString(2, doador.getNomeDoador());
            stmt.setString(3, doador.getTelDoador());
            stmt.setString(4, doador.getEnderecoDoador());
            
            stmt.execute();
            System.out.println("Doador alterado com sucesso!");
            stmt.close();
			
		} catch (SQLException e) {
	    	throw new RuntimeException(e);
	    }
	}
	
	public Doador consultarDoador(Doador doador) {
		try {
			String sql = "call consultarDoador ("+doador.getCpfDoador()+")";
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs != null && rs.next()) {
				doador.setCpfDoador(rs.getString("cpfDoador"));
				doador.setNomeDoador(rs.getString("nomeDoador"));
				doador.setTelDoador(rs.getString("telDoador"));
				doador.setEnderecoDoador(rs.getString("enderecoDoador"));
			}
			return doador;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Doador> listarDoadores() {
	    List<Doador> doadoresList = new ArrayList<>();
	    
	    try {
	        String sql = "call listarDoadores()";
	        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();
	        

	        while (rs.next()) {
	            Doador doador = new Doador();
	            doador.setCpfDoador(rs.getString("cpfDoador"));
				doador.setNomeDoador(rs.getString("nomeDoador"));
				doador.setTelDoador(rs.getString("telDoador"));
				doador.setEnderecoDoador(rs.getString("enderecoDoador"));
	            doadoresList.add(doador);
	        }
	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return doadoresList;
	}
	
	public void removerDoador(Doador doador) {
	    try {
	        String sql = "call removerDoador(?)";
	        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);

	        stmt.setString(1, doador.getCpfDoador());

	        stmt.execute();
	        System.out.println("Doador com cpf " + doador.getCpfDoador() + " removido com sucesso!");

	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Erro ao remover o doador: " + e.getMessage());
	    }
	}


}
