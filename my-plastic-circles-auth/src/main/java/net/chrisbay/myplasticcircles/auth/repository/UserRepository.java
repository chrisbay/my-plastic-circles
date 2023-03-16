package net.chrisbay.myplasticcircles.auth.repository;

import jakarta.transaction.Transactional;
import net.chrisbay.myplasticcircles.auth.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByUsername(String username);

}
