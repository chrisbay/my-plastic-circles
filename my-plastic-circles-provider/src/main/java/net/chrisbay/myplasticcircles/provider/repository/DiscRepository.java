package net.chrisbay.myplasticcircles.provider.repository;

import net.chrisbay.myplasticcircles.provider.model.Disc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscRepository extends JpaRepository<Disc, Integer> {
}
