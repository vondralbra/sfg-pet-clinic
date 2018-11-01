package guru.springframework.sfgpetclinic.model;

import java.util.Set;

/**
 * Try closes #2
 * 
 * @author Philip
 *
 */
public class Owner extends Person {

	private Set<Pet> pets;
	private String address;
	private String City;
	private String telephone;

	public Set<Pet> getPets() {
		return pets;
	}

	public void setPets(Set<Pet> pets) {
		this.pets = pets;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
