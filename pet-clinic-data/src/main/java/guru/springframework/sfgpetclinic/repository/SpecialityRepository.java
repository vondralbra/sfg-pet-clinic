package guru.springframework.sfgpetclinic.repository;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.sfgpetclinic.model.Speciality;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {

}
