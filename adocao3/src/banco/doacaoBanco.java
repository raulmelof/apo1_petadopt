package banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import model.Doacao;

public class doacaoBanco {
	private DBConnection connection;

	public doacaoBanco() {
		this.connection = new DBConnection();
	}
	
	public void inserirDoacao(Doacao doacao) {

	    try {
            String sql = "call inserirDoacao (?, ?);";
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            
            stmt.setObject(1, doacao.getDataDoacao());
            stmt.setString(2, doacao.getLocalDoacao());

            stmt.execute();
            System.out.println("Doação inserida com sucesso!");
            stmt.close();  
	    } catch (SQLException e) {
	    	throw new RuntimeException(e);
	    }
	}
	
	public Doacao consultarDoacao(Doacao doacao) {
		try {
			String sql = "call consultarDoacao ("+doacao.getCodDoacao()+")";
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs != null && rs.next()) {
				doacao.setCodDoacao(rs.getInt("codDoacao"));
				doacao.setDataDoacao(rs.getObject("dataDoacao", LocalDate.class));
				doacao.setLocalDoacao(rs.getString("localDoacao"));
			}
			return doacao;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
