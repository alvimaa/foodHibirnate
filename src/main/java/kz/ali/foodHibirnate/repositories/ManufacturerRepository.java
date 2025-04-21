package kz.ali.foodHibirnate.repositories;

import jakarta.transaction.Transactional;
import kz.ali.foodHibirnate.entities.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

}
