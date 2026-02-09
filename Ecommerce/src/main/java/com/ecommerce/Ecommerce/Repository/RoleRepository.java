package com.ecommerce.Ecommerce.Repository;

import com.ecommerce.Ecommerce.Models.Role;
import com.ecommerce.Ecommerce.Utility.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(AppRole appRole);
}
