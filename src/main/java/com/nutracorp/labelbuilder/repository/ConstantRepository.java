package com.nutracorp.labelbuilder.repository;

import com.nutracorp.labelbuilder.domain.Constant;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Constant entity.
 */
public interface ConstantRepository extends JpaRepository<Constant,Long> {

}
