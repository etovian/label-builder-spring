package com.nutracorp.labelbuilder.repository;

import com.nutracorp.labelbuilder.domain.Label;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Label entity.
 */
public interface LabelRepository extends JpaRepository<Label,Long> {

    List<Label> findByProductId(String productId);
}
