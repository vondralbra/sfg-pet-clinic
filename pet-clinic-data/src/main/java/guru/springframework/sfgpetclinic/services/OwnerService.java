package guru.springframework.sfgpetclinic.services;

import java.util.Set;

import guru.springframework.sfgpetclinic.model.Owner;

public interface OwnerService {
	Owner findById(Long id);

	Owner findByLastName(String lastName);

	Owner save(Owner owner);

	Set<Owner> findAll();
}
