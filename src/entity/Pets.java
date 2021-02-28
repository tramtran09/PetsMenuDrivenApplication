package entity;

public class Pets {
	private int petID;
	private String petName;
	private String petSpecies;
	
	public Pets(int petID, String petName, String petSpecies) {
		this.setPetID (petID);
		this.setPetName (petName);
		this.setPetSpecies (petSpecies);
	}

	public int getPetID() {
		return petID;
	}

	public void setPetID(int petID) {
		this.petID = petID;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getPetSpecies() {
		return petSpecies;
	}

	public void setPetSpecies(String petSpecies) {
		this.petSpecies = petSpecies;
	}
}
