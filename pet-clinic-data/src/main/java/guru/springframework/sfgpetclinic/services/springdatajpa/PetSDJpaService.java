package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repository.PetRepository;
import guru.springframework.sfgpetclinic.services.PetService;

@Service
@Profile("springdatajpa")
public class PetSDJpaService implements PetService {

	private final PetRepository PetRepository;

	public PetSDJpaService(PetRepository PetRepository) {
		super();
		this.PetRepository = PetRepository;
	}

	@Override
	public Set<Pet> findAll() {
		Set<Pet> Pets = new HashSet<>();
		PetRepository.findAll().forEach(Pets::add);
		return Pets;
	}

	@Override
	public Pet findById(Long id) {
		return PetRepository.findById(id).orElse(null);
	}

	@Override
	public Pet save(Pet object) {
		return PetRepository.save(object);
	}

	@Override
	public void delete(Pet object) {
		PetRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		PetRepository.deleteById(id);
	}
}
