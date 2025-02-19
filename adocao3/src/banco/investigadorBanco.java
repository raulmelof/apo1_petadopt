package banco;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Investigador;


public class investigadorBanco {
private DBConnection connection;
	
	public investigadorBanco() {
		this.connection = new DBConnection();
	}
	
	public void inserirAut(Investigador invest) {
	    try  {
	    	String sql = "call inserirAut (?, ?)";
	    	PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	    	
	    	stmt.setInt(1, invest.getIdFuncionario());
            stmt.setString(2, invest.getAutInvestigador());

            stmt.execute();
            System.out.println("Autorização inserida com sucesso!");
            stmt.close();
	        
	    } catch (SQLException e) {
	    	throw new RuntimeException(e);
	    }
	}


}
