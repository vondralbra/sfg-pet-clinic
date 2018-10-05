package guru.springframework.sfgpetclinic.model;

import java.time.LocalDate;

public class Pet extends BaseEntity {

	private PetType petTYpe;
	private LocalDate birthday;
	private Owner owner;

	public PetType getPetTYpe() {
		return petTYpe;
	}

	public void setPetTYpe(PetType petTYpe) {
		this.petTYpe = petTYpe;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
}
