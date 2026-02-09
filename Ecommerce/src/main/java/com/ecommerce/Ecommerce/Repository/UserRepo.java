package com.ecommerce.Ecommerce.Repository;


import com.ecommerce.Ecommerce.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.lang.ScopedValue;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public Boolean existsByEmail(String email);

    Optional<User> findByPassword(String password);

    public Boolean existsByUserName(String username);

    User findByUsername(String username);

    Optional<User> findByUserName(String username);
}
