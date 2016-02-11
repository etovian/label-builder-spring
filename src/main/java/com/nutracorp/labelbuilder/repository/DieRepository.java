package com.nutracorp.labelbuilder.repository;

import com.nutracorp.labelbuilder.domain.Die;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Die entity.
 */
public interface DieRepository extends JpaRepository<Die,Long> {

}
