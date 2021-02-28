package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Pets;

public class PetsDao {
	private Connection connection;
	private final String ADD_PET_QUERY = "INSERT INTO pets (species, name) VALUES(?,?)";
	private final String UPDATE_PET = "UPDATE pets (species, name) WHERE id =(id) VALUES (?,?,?)";
	private final String DELETE_PET = "DELETE FROM pets WHERE id = ?";
	
	public PetsDao() {
		connection = DBConnection.getInstance().getConnection();
		
	}
	public List<Pets> displayAllPets() throws SQLException {
		List<Pets> out = new ArrayList<>();
		
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery("select * from pets");
		
		while (rs.next()) {
			out.add(new Pets (rs.getInt("id"), rs.getString("species"), rs.getString("name")));
		}
		return out;
	}
	
	public void createNewPet(String petSpecies, String petName) throws SQLException{
		PreparedStatement ps = connection.prepareStatement(ADD_PET_QUERY);
		ps.setString(1, petSpecies);
		ps.setString(2, petName);
		ps.executeUpdate();
	}
	
	public void updatePet(int petId, String petName, String petSpecies) throws SQLException{
		PreparedStatement ps = connection.prepareStatement (UPDATE_PET);
		ps.setInt (1,petId);
		ps.setString (2,petSpecies);
		ps.setString (3,petName);
		ps.executeUpdate();
	}
	
	public void deletePetByID (int petID) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_PET);
		ps.setInt(1, petID);
		ps.executeUpdate();
	}
	public void close() {
		DBConnection.getInstance().closeConnection();
	}
	
}
