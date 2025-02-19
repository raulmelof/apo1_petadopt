package banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Funcionarios;

public class funcionarioBanco {
	private DBConnection connection;
	
	public funcionarioBanco() {
		this.connection = new DBConnection();
	}
	
	public void inserirFunc(Funcionarios funcionario) {
	    try  {
	    	String sql = "call inserirFunc (?, ?)";
	    	PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	    	
            stmt.setString(1, funcionario.getNomeFuncionario());
            stmt.setString(2, funcionario.getDepFuncionario());

            stmt.execute();
            System.out.println("Funcionário inserido com sucesso!");
            stmt.close();
	        
	    } catch (SQLException e) {
	    	throw new RuntimeException(e);
	    }
	}

	public void alterarFunc(Funcionarios funcionario){
		try {
			String sql = "call alterarFunc (?, ?, ?)";
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			
			stmt.setInt(1, funcionario.getIdFuncionario());
			stmt.setString(2, funcionario.getNomeFuncionario());
            stmt.setString(3, funcionario.getDepFuncionario());
            
            stmt.execute();
            System.out.println("Funcionário alterado com sucesso!");
            stmt.close();
			
		} catch (SQLException e) {
	    	throw new RuntimeException(e);
	    }
	}
	
	public Funcionarios consultarFunc(Funcionarios funcionario) {
		try {
			String sql = "call consultarFunc ("+funcionario.getIdFuncionario()+")";
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs != null && rs.next()) {
				funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
				funcionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
				funcionario.setDepFuncionario((rs.getString("depFuncionario")));
			}
			return funcionario;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Funcionarios> listarFuncs() {
	    List<Funcionarios> funcionariosList = new ArrayList<>();
	    
	    try {
	        String sql = "call listarFuncs()";
	        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();
	        

	        while (rs.next()) {
	            Funcionarios funcionario = new Funcionarios();
	            funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
	            funcionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
	            funcionario.setDepFuncionario(rs.getString("depFuncionario"));
	            funcionariosList.add(funcionario);
	        }
	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return funcionariosList;
	}


}
