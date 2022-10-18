package com.ecommerce.eshoping.repositories;

import com.ecommerce.eshoping.models.CrudDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudRepository extends JpaRepository<CrudDetail, Long> {
}
