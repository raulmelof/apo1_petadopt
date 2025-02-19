package banco;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Veterinario;

public class vetBanco {
	private DBConnection connection;
	
	public vetBanco() {
		this.connection = new DBConnection();
	}
	
	public void inserirCrmv(Veterinario vet) {
	    try  {
	    	String sql = "call inserirCrmv (?, ?)";
	    	PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	    	
	    	stmt.setInt(1, vet.getIdFuncionario());
            stmt.setString(2, vet.getCrmvVeterinario());

            stmt.execute();
            System.out.println("Crmv inserido com sucesso!");
            stmt.close();
	        
	    } catch (SQLException e) {
	    	throw new RuntimeException(e);
	    }
	}

}
