package banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import model.Prontuario;

public class prontuarioBanco {
	private DBConnection connection;

	public prontuarioBanco() {
		this.connection = new DBConnection();
	}
	
	public boolean verificaIdExiste(int idAnimal) throws Exception {
        try  {
        	String sql = "call verificaIdExiste (?);";
        	PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            stmt.setInt(1, idAnimal);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total") > 0; // Se o count > 0, o ID existe
            }
        } catch (SQLException e) {
	    	throw new RuntimeException(e);
        }
        return false;
    }

	
	public void inserirProntuario(Prontuario prontuario) {
	    try {
	            String sql = "call inserirProntuario (?, ?, ?, ?, ?);";
	            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	            
	            stmt.setInt(1, prontuario.getIdAnimal());
	            stmt.setString(2, prontuario.getIdadeAnimal());
	            stmt.setFloat(3, prontuario.getPesoAnimal());
	            stmt.setString(4, prontuario.getHigieneAnimal());
	            stmt.setString(5, prontuario.getTipoAnimal());

	            stmt.execute();
	            System.out.println("Prontuário inserido com sucesso!");
	            stmt.close();
	    } catch (SQLException e) {
	    	throw new RuntimeException(e);
	    }
	}
	
	
	public Prontuario consultarProntuario(Prontuario prontuario) {
	    try {
	            String sql = "call consultarProntuario ("+prontuario.getCodProntuario()+");";
	            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery(sql);
	            if(rs != null && rs.next()) {
	            	prontuario.setCodProntuario(rs.getInt("codProntuario"));
	            	prontuario.setIdAnimal(rs.getInt("idAnimal"));
	            	prontuario.setIdadeAnimal(rs.getString("idadeAnimal"));
		            prontuario.setPesoAnimal(rs.getFloat("pesoAnimal"));
		            prontuario.setHigieneAnimal(rs.getString("higieneAnimal"));
		            prontuario.setTipoAnimal(rs.getString("tipoAnimal"));
		            
		            String sqlDoencas = "CALL mostrarDoencasPront(?);";
		            PreparedStatement stmtDoencas = connection.getConnection().prepareStatement(sqlDoencas);
		            stmtDoencas.setInt(1, prontuario.getCodProntuario());
		            ResultSet rsDoencas = stmtDoencas.executeQuery();
		            
		            List<String> nomesDoencas = new ArrayList<>();
		            while (rsDoencas.next()) {
		                nomesDoencas.add(rsDoencas.getString("nomeDoenca"));
		            }
		            prontuario.setNomesDoencas(nomesDoencas);

	            }
	            return prontuario;
	    } catch(SQLException e) {
			e.printStackTrace();
		}
	    return null;
	}
	
	public boolean alterarProntuario(int codProntuario, int idadeAnimal, float pesoAnimal) {
	    String sql = "{CALL alterarProntuario(?, ?, ?)}";
	    try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {
	        stmt.setInt(1, codProntuario);   
	        stmt.setInt(2, idadeAnimal);    
	        stmt.setFloat(3, pesoAnimal);  

	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; 
	    }
	}
	
	public boolean desvincularProntuario(int codProntuario) {
	    try {
	        String sql = "{CALL desvincularProntuario(?)}";
	        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	        stmt.setInt(1, codProntuario);

	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}



}
