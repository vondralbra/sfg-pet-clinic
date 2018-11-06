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
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.VisitService;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final SpecialityService specialityService;
	private final VisitService visitService;

	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
			SpecialityService specialityService, VisitService visitService) {
		super();
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialityService = specialityService;
		this.visitService = visitService;

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
		Owner max = createOwner("Max", "Schmeling", "Boxgasse 1", "Berlin", "+4754145");
		Owner fiona = createOwner("Fiona", "Rogers", "Holywood Blvd", "Los Angeles", "+112345678");
		System.out.println("Created owners.");

		printIds(max, max);

		System.out.println("Creating pets...");
		Pet maxDog = createPet("Bronko", dogType, max);
		Pet fionasCat = createPet("Cleo", catType, fiona);
		System.out.println("Created pets.");
		printIds(max, max, maxDog, fionasCat);

		System.out.println("Saving owners (with their pets)...");
		ownerService.save(max);
		ownerService.save(fiona);
		System.out.println("Saved owners.");
		printIds(max, fiona, maxDog, fionasCat);

		Visit visit = new Visit();
		visit.setDate(LocalDate.now());
		visit.setDescription("Sneezy Kitty");
		visit.setPet(fionasCat);
		visitService.save(visit);
		printIds(visit);
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
