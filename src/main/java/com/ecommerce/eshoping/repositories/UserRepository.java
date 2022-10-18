package com.ecommerce.eshoping.repositories;

import com.ecommerce.eshoping.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
