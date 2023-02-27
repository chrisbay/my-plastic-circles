package net.chrisbay.myplasticcirclesprovider.repository;

import net.chrisbay.myplasticcirclesprovider.model.Disc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscRepository extends JpaRepository<Disc, Integer> {
}
