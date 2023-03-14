package net.chrisbay.myplasticcircles.provider.repository;

import net.chrisbay.myplasticcircles.provider.model.DiscManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscManufacturerRepository extends JpaRepository<DiscManufacturer, Integer> {
}
