package com.nutracorp.labelbuilder.repository;

import com.nutracorp.labelbuilder.domain.LookupValue;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the LookupValue entity.
 */
public interface LookupValueRepository extends JpaRepository<LookupValue,Long> {

}
