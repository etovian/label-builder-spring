package com.nutracorp.labelbuilder.repository;

import com.nutracorp.labelbuilder.domain.LookupGroup;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the LookupGroup entity.
 */
public interface LookupGroupRepository extends JpaRepository<LookupGroup,Long> {

    @Query("select distinct lookupGroup from LookupGroup lookupGroup left join fetch lookupGroup.lookupValuess")
    List<LookupGroup> findAllWithEagerRelationships();

    @Query("select lookupGroup from LookupGroup lookupGroup left join fetch lookupGroup.lookupValuess where lookupGroup.id =:id")
    LookupGroup findOneWithEagerRelationships(@Param("id") Long id);

}
