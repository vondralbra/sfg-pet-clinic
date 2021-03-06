package guru.springframework.sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;

	public DataLoader(OwnerService ownerService, VetService vetService) {
		super();
		this.ownerService = ownerService;
		this.vetService = vetService;
	}

	@Override
	public void run(String... args) throws Exception {

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
