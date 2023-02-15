package net.chrisbay.myplasticcirclesprovider.repository;

import net.chrisbay.myplasticcirclesprovider.model.DiscManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscManufacturerRepository extends JpaRepository<DiscManufacturer, Integer> {
}
