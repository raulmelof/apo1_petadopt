package banco;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import model.Adocao;


public class adocaoBanco {
	private DBConnection connection;

	public adocaoBanco() {
		this.connection = new DBConnection();
	}
	
	
	public void inserirAdocao(Adocao adocao) {
	    try {
	        String sql = "CALL inserirAdocao (?, ?, ?)";
	        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);

	        stmt.setObject(1, adocao.getDataAdocao());
	        stmt.setString(2, adocao.getLocalAdocao());
	        stmt.setString(3, adocao.getCpfCliente());

	        stmt.execute();
	        System.out.println("Adoção inserida com sucesso!");
	        stmt.close();
	    } catch (SQLException e) {
	        throw new RuntimeException("Erro ao inserir adoção: " + e.getMessage(), e);
	    }
	}
	
	public Adocao consultarAdocao(Adocao adocao) {
	    try {
	        String sql = "CALL consultarAdocao(?)";
	        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	        stmt.setInt(1, adocao.getCodAdocao());
	        ResultSet rs = stmt.executeQuery();

	        if (rs != null && rs.next()) {
	            adocao.setCodAdocao(rs.getInt("codAdocao"));
	            adocao.setDataAdocao(rs.getObject("dataAdocao", LocalDate.class));
	            adocao.setLocalAdocao(rs.getString("localAdocao"));
	            adocao.setCpfCliente(rs.getString("cpfCliente"));
	        }

	        return adocao;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Erro ao consultar adoção: " + e.getMessage());
	    }
	}
}
