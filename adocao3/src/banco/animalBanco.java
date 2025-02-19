package banco;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Animal;
import model.Animal.statusAnimal;


public class animalBanco {
	private DBConnection connection;

	public animalBanco() {
		this.connection = new DBConnection();
	}
	
	public void incluirAnimal(Animal animal) {
	    try  {
	    	String sql = "call incluirAnimal (?, ?, ?)";
	    	PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	    	
            stmt.setString(1, animal.getNomeAnimal());
            stmt.setString(2, animal.getSexoAnimal().toString());
            stmt.setString(3, animal.getRacaAnimal());

            stmt.execute();
            System.out.println("Animal inserido com sucesso!");
            stmt.close();
	        
	    } catch (SQLException e) {
	    	throw new RuntimeException(e);
	    }
	}
	
	public void alterarAnimal(Animal animal){
		try {
			String sql = "call alterarAnimal (?, ?, ?, ?)";
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			
			stmt.setInt(1, animal.getIdAnimal());
			stmt.setString(2, animal.getNomeAnimal());
            stmt.setString(3, animal.getSexoAnimal().toString());
            stmt.setString(4, animal.getRacaAnimal());
            
            stmt.execute();
            System.out.println("Animal alterado com sucesso!");
            stmt.close();
			
		} catch (SQLException e) {
	    	throw new RuntimeException(e);
	    }
	}
	
	public Animal consultarAnimal(Animal animal) {
		try {
			String sql = "call consultarAnimal ("+animal.getIdAnimal()+")";
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs != null && rs.next()) {
				animal.setIdAnimal(rs.getInt("idAnimal"));
				animal.setNomeAnimal(rs.getString("nomeAnimal"));
				animal.setSexoAnimal(rs.getString("sexoAnimal"));
				animal.setRacaAnimal(rs.getString("racaAnimal"));
				String status = rs.getString("statusAnimal");
				animal.setStatus(statusAnimal.valueOf(status));
			    

			}
			return animal;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	public List<Animal> listarAnimal() {
	    List<Animal> animalList = new ArrayList<>();
	    
	    try {
	        String sql = "call listarAnimal()";
	        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();
	        

	        while (rs.next()) {
	            Animal animal = new Animal();
	            animal.setIdAnimal(rs.getInt("idAnimal"));
	            animal.setNomeAnimal(rs.getString("nomeAnimal"));
	            animal.setSexoAnimal(rs.getString("sexoAnimal"));
	            String statusString = rs.getString("statusAnimal");
	            animal.setStatus(Animal.statusAnimal.valueOf(statusString));
	            animalList.add(animal);
	        }
	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return animalList;
	}
	
	public List<Animal> listarAnimaisDisp() {
	    List<Animal> animaisDisponiveis = new ArrayList<>();

	    try {
	        String sql = "CALL listarAnimaisDisp()"; // Chamada da procedure no banco
	        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Animal animal = new Animal(
	                rs.getInt("idAnimal"),
	                rs.getString("nomeAnimal"),
	                rs.getString("sexoAnimal"),
	                rs.getString("racaAnimal")
	            );
	            animal.setStatus(Animal.statusAnimal.valueOf(rs.getString("statusAnimal")));
	            animaisDisponiveis.add(animal);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return animaisDisponiveis;
	}
	
	public boolean reservaAnimal(int idAnimal) {
        boolean sucesso = false;

        try {
            String sql = "CALL reservaAnimal(?)";
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);

            stmt.setInt(1, idAnimal);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                sucesso = true; // Se alguma linha foi atualizada, a reserva foi bem-sucedida
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sucesso;
    }
	
	public void liberarAnimal(Integer idAnimal) {
	    try {
	        // Verifica se o animal com o id existe
	    	String checksql = "CALL verificaIdExiste(?)";
	        PreparedStatement checkStmt = connection.getConnection().prepareStatement(checksql);
	        checkStmt.setInt(1, idAnimal);
	        ResultSet rs = checkStmt.executeQuery();
	        
	        // Se o animal não for encontrado
	        if (rs.next() && rs.getInt(1) == 0) {
	            throw new RuntimeException("Animal com ID " + idAnimal + " não encontrado.");
	        }

	        // Caso o animal exista, chama a procedure de liberarAnimal
	        String sql = "call liberarAnimal(?)";
	        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	        stmt.setInt(1, idAnimal);

	        stmt.execute();
	        System.out.println("Animal com ID " + idAnimal + " liberado com sucesso!");

	        stmt.close();
	        checkStmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Erro ao liberar o animal: " + e.getMessage());
	    }
	}

	public boolean verificaProntuario(Integer idAnimal) {
	    try {
	        String sql = "CALL verificaProntuario(?)";
	        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	        stmt.setInt(1, idAnimal);
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	        	String mensagem = rs.getString("mensagem");
	            System.out.println(rs.getString("mensagem"));
	            if (mensagem != null && mensagem.equals("Animal pronto para ser disponibilizado.")) {
	                return true;
	            }
	        }

	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Erro ao verificar prontuário: " + e.getMessage());
	    }
		return false;
	}

	public void alterarDisponivel(Integer idAnimal) {
	    try {
	        verificaProntuario(idAnimal); // Verifica se o animal está pronto

	        String sql = "CALL alteraDisponivel(?)";
	        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
	        stmt.setInt(1, idAnimal);
	        stmt.execute();

	        System.out.println("Animal com ID " + idAnimal + " alterado para DISPONIVEL!");

	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Erro ao alterar o status: " + e.getMessage());
	    }
	}
	
	public void finalizarAdocao(Integer idAnimal) {
        try {
            String sql = "CALL finalizarAdocao(?)";
            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
            stmt.setInt(1, idAnimal);
            stmt.execute();

            System.out.println("Animal com ID " + idAnimal + " alterado para ADOTADO!");

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao finalizar a adoção: " + e.getMessage());
        }
    }


	


}
