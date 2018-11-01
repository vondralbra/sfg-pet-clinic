package guru.springframework.sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
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

		PetType dog = new PetType();
		dog.setName("dog");
		PetType savedPetType = petTypeService.save(dog);

		PetType cat = new PetType();
		cat.setName("cat");
		PetType savedPetType2 = petTypeService.save(cat);

		Owner owner1 = createOwner("Max", "Schmeling");
		ownerService.save(owner1);
		Owner owner2 = createOwner("Mimi", "Rogers");
		ownerService.save(owner2);

		System.out.println("Loaded owners...");
		Vet vet1 = createVeh("Sam", "Axe");
		vetService.save(vet1);
		Vet vet2 = createVeh("Karl", "Klammer");
		vetService.save(vet2);
		System.out.println("Loaded vets...");

	}

	private Vet createVeh(String firstName, String lastName) {
		Vet vet = new Vet();
		vet.setFirstName(firstName);
		vet.setLastName(lastName);
		return vet;
	}

	private Owner createOwner(String firstName, String lastName) {
		Owner owner = new Owner();
		owner.setFirstName(firstName);
		owner.setLastName(lastName);

		return owner;
	}

}
