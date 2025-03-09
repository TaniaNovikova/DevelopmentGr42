package de.ait.javalessons.repositories;

import de.ait.javalessons.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends org.springframework.data.repository.CrudRepository<de.ait.javalessons.model.Car, String> {
}
