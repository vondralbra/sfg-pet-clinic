package guru.springframework.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.BaseEntity;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import guru.springframework.sfgpetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final SpecialityService specialityService;

	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
			SpecialityService specialityService) {
		super();
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialityService = specialityService;

	}

	@Override
	public void run(String... args) throws Exception {

		int petTypeCount = petTypeService.findAll().size();
		if (petTypeCount == 0) {
			System.out.println("Loading data....");
			loadData();
			System.out.println("Data loaded");
		}

	}

	private void loadData() {
		System.out.println("Creating (and saving) petTypes...");
		PetType dogType = createAndSavePetType("dog");
		PetType catType = createAndSavePetType("cat");
		System.out.println("Created petTypes.");

		printIds(dogType, catType);

		System.out.println("Creating owners...");
		Owner owner1 = createOwner("Max", "Schmeling", "Boxgasse 1", "Berlin", "+4754145");
		Owner owner2 = createOwner("Mimi", "Rogers", "Holywood Blvd", "Los Angeles", "+112345678");
		System.out.println("Created owners.");

		printIds(owner1, owner1);

		System.out.println("Creating pets...");
		Pet pet1 = createPet("Bronko", dogType, owner1);
		Pet pet2 = createPet("Cleo", catType, owner2);
		System.out.println("Created pets.");
		printIds(owner1, owner1, pet1, pet2);

		System.out.println("Saving owners (with their pets)...");
		ownerService.save(owner1);
		ownerService.save(owner2);
		System.out.println("Saved owners.");
		printIds(owner1, owner1, pet1, pet2);

		Speciality speciality1 = createSpeciality("Radiology");
		Speciality speciality2 = createSpeciality("Surgery");
		Speciality speciality3 = createSpeciality("Dentistry");
		printIds(speciality1, speciality2, speciality3);

		System.out.println("Creating vets...");
		Vet vet1 = createVet("Sam", "Axe", speciality1);
		Vet vet2 = createVet("Jessy", "Porter", speciality2);
		System.out.println("Created vets.");
		printIds(vet1, vet2, speciality1, speciality2, speciality3);

		System.out.println("Saving vets...");
		vetService.save(vet1);
		vetService.save(vet2);
		printIds(vet1, vet2, speciality1, speciality2, speciality3);
		System.out.println("Saved vets.");

	}

	private void printIds(BaseEntity... entities) {
		for (int i = 0; i < entities.length; i++) {
			System.out.println(entities[i].getClass().getSimpleName() + " " + entities[i].getId());
		}

	}

	private Speciality createSpeciality(String description) {
		Speciality speciality = new Speciality();
		speciality.setDescription(description);
		return speciality;
	}

	private Pet createPet(String name, PetType petType, Owner owner) {
		Pet pet = new Pet();
		pet.setName(name);
		pet.setBirthdate(LocalDate.now());
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

	private Vet createVet(String firstName, String lastName, Speciality speciality) {
		Vet vet = new Vet();
		vet.setFirstName(firstName);
		vet.setLastName(lastName);
		vet.getSpecialities().add(speciality);
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
