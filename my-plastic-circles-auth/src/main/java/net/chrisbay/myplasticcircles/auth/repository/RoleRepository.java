package net.chrisbay.myplasticcircles.auth.repository;

import jakarta.transaction.Transactional;
import net.chrisbay.myplasticcircles.auth.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
