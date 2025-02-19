package banco;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Doencas;

public class doencaBanco {
	private DBConnection connection;

	public doencaBanco() {
		this.connection = new DBConnection();
	}
	
	public void inserirDoenca(Doencas doenca) {
	    try {
	            String sql = "call inserirDoenca (?, ?, ?, ?);";
	            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	            
	            stmt.setString(1, doenca.getNomeDoenca());
	            stmt.setObject(2, doenca.getDataDoenca());
	            stmt.setString(3, doenca.getTratamentoDoenca());
	            stmt.setInt(4, doenca.getCodProntuario());

	            stmt.execute();
	            System.out.println("Doença inserida com sucesso!");
	            stmt.close();
	    } catch (SQLException e) {
	    	throw new RuntimeException(e);
	    }
	}
	
	public void removerDoenca(Doencas doenca) {
	    try {
	        String sql = "call removerDoenca(?, ?)";
	        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);

	        stmt.setString(1, doenca.getNomeDoenca());
	        stmt.setInt(2, doenca.getCodProntuario());

	        stmt.execute();
	        System.out.println("Doenca " + doenca.getNomeDoenca() + " removido com sucesso!");

	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Erro ao remover a doenca: " + e.getMessage());
	    }
	}
	
	
}


