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

		System.out.println("Creating (and saving) petTypes...");
		PetType dogType = createAndSavePetType("dog");
		PetType catType = createAndSavePetType("cat");
		System.out.println("Created petTypes.");

		System.out.println("Creating owners...");
		Owner maximilian = createOwner("Max", "Schmeling", "Boxgasse 1", "Berlin", "+4754145");
		Owner mimi = createOwner("Mimi", "Rogers", "Holywood Blvd", "Los Angeles", "+112345678");
		System.out.println("Created owners.");

		System.out.println("Creating pets...");
		createPet("Bronko", dogType, maximilian);
		createPet("Cleo", catType, mimi);
		System.out.println("Created pets.");

		ownerService.save(maximilian);
		ownerService.save(mimi);

		System.out.println("Creating vets...");
		Vet vet1 = createVet("Sam", "Axe");
		Vet vet2 = createVet("Karl", "Klammer");
		vetService.save(vet1);
		vetService.save(vet2);
		System.out.println("Created vets.");

	}

	private Pet createPet(String name, PetType petType, Owner owner) {
		Pet pet = new Pet();
		pet.setName(name);
		pet.setBirthday(LocalDate.now());
		pet.setPetType(petType);
		pet.setOwner(owner);
		owner.getPets().add(pet);
		return pet;
	}

	private PetType createAndSavePetType(String name) {
		PetType petType = new PetType();
		petType.setName(name);
		PetType savedPetType = petTypeService.save(petType);
		return savedPetType;
	}

	private Vet createVet(String firstName, String lastName) {
		Vet vet = new Vet();
		vet.setFirstName(firstName);
		vet.setLastName(lastName);
		return vet;
	}

	private Owner createOwner(String firstName, String lastName, String address, String city, String telephone) {
		Owner owner = new Owner();
		owner.setFirstName(firstName);
		owner.setLastName(lastName);
		owner.setAddress(address);
		owner.setCity(city);
		owner.setTelephone(telephone);
		return owner;
	}

}
