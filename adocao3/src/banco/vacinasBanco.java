package banco;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Vacinas;

public class vacinasBanco {
	private DBConnection connection;

	public vacinasBanco() {
		this.connection = new DBConnection();
	}
	
	public void registrarVacinas(Vacinas vacinas) {
	    try {
	            String sql = "call registrarVacinas (?, ?, ?);";
	            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	            
	            stmt.setString(1, vacinas.getNomeVacina());
	            stmt.setObject(2, vacinas.getDataVacina());
	            stmt.setInt(3, vacinas.getCodProntuario());

	            stmt.execute();
	            System.out.println("Vacinas inserido com sucesso!");
	            stmt.close();
	    } catch (SQLException e) {
	    	throw new RuntimeException(e);
	    }
	}

}
