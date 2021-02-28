package application;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.PetsDao;
import entity.Pets;

public class Menu {
	private Scanner scanner = new Scanner(System.in);
	private String[] menuOpts = {"Display current pets", "Create new pet", "Update a pet", "Delete a pet"};
	private PetsDao petsDao = new PetsDao();
	
	private void printMenu() {
		System.out.println("------");
		for(int i=0; i < menuOpts.length; i++) {
			System.out.println(( i+1 ) + ") " + menuOpts[i]);
		}
	}
	
	private void displayAllPets() throws SQLException{
		List<Pets> myPets = petsDao.displayAllPets();
		for (Pets p : myPets) {
			System.out.println(p.getPetID() + " - " + p.getPetSpecies() + " - " + p.getPetName());
		}
	}
	
	
	private void createPet() throws SQLException{
		System.out.println("Enter pet species: ");
		String petSpecies = scanner.nextLine();
		System.out.println("Enter pet name: ");
		String petName = scanner.nextLine();
		petsDao.createNewPet(petSpecies,petName);
	}
	
	private void updatePet() throws SQLException{
		System.out.println("Enter ID of pet to update: ");
		String nl = scanner.nextLine();
		Integer petId = null;
		try {
			petId = Integer.parseInt(nl);
		}catch (NumberFormatException e) {
			System.out.println("Please enter a number!");
			return;
		}
		if (petId != null) {
			System.out.println("Enter new species: ");
			String petSpecies = scanner.nextLine();
			if (!petSpecies.isEmpty()) {
				System.out.println("Enter pet name: ");
				String petName = scanner.nextLine();
			if (!petName.isEmpty()) {
				petsDao.updatePet(petId, petSpecies, petName);
			}
		}
	}
	
}
	
	private void deletePet() throws SQLException{
		System.out.println("Enter pet ID to delete");
		int petId = Integer.parseInt(scanner.nextLine());
		petsDao.deletePetByID(petId);
	}
	
	
	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			
			try {
				switch(selection) {
				case "1": displayAllPets();
					break;
				case "2": createPet();
					break;
				case "3": updatePet();
					break;
				case "4": deletePet();
					break;
				default:
					selection = "-1";
					break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				end();
			}
			if (!selection.equals("-1")) {
				System.out.println("Press enter to continue...");
				scanner.nextLine();
			}
			
		} while(!selection.equals("-1"));
		
		end();
	}
	public void end() {
		System.out.println("Goodbye!");
		scanner.close();
		petsDao.close();
	}
	
}
