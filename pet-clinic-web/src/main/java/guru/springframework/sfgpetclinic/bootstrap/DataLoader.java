package guru.springframework.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;

	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
		super();
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
	}

	@Override
	public void run(String... args) throws Exception {

		PetType dog = createPetType("dog");
		PetType cat = createPetType("cat");

		Owner max = createOwner("Max", "Schmeling", "Boxgasse 1", "Berlin", "+4754145");
		Owner mimi = createOwner("Mimi", "Rogers", "Holywood Blvd", "Los Angeles", "+4754145");

		System.out.println("Loaded owners...");
		Vet vet1 = createVeh("Sam", "Axe");
		Vet vet2 = createVeh("Karl", "Klammer");

		Pet maxsPet = new Pet();
		maxsPet.setName("Bronco");
		maxsPet.setBirthday(LocalDate.now());
		maxsPet.setPetType(dog);
		maxsPet.setOwner(max);
		max.getPets().add(maxsPet);

		Pet mimisPet = new Pet();
		mimisPet.setName("Cleo");
		mimisPet.setBirthday(LocalDate.now());
		mimisPet.setPetType(cat);
		mimisPet.setOwner(mimi);
		mimi.getPets().add(mimisPet);

		System.out.println("Loaded vets...");

	}

	private PetType createPetType(String name) {
		PetType petType = new PetType();
		petType.setName(name);
		PetType savedPetType = petTypeService.save(petType);
		return savedPetType;
	}

	private Vet createVeh(String firstName, String lastName) {
		Vet vet = new Vet();
		vet.setFirstName(firstName);
		vet.setLastName(lastName);
		vetService.save(vet);
		return vet;
	}

	private Owner createOwner(String firstName, String lastName, String address, String city, String telephone) {
		Owner owner = new Owner();
		owner.setFirstName(firstName);
		owner.setLastName(lastName);
		owner.setAddress(address);
		owner.setCity(city);
		owner.setTelephone(telephone);
		ownerService.save(owner);
		return owner;
	}

}
