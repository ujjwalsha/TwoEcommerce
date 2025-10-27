package com.ecommerce.Ecommerce.Repository;


import com.ecommerce.Ecommerce.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public Boolean existsByEmail(String email);

    Optional<User> findByPassword(String password);

    public Boolean existsByUsername(String username);

    User findByUsername(String username);
}
