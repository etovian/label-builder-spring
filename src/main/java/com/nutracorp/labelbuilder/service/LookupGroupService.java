package com.nutracorp.labelbuilder.service;

import com.nutracorp.labelbuilder.domain.LookupGroup;
import com.nutracorp.labelbuilder.domain.LookupValue;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Service Interface for managing LookupGroup.
 */
public interface LookupGroupService {

    /**
     * Save a lookupGroup.
     * @return the persisted entity
     */
    LookupGroup save(LookupGroup lookupGroup);

    /**
     *  get all the lookupGroups.
     *  @return the list of entities
     */
    List<LookupGroup> findAll();

    /**
     *  get the "id" lookupGroup.
     *  @return the entity
     */
    LookupGroup findOne(Long id);

    /**
     *  delete the "id" lookupGroup.
     */
    void delete(Long id);

    /**
     * get a map of lookup groups
     */
    Map<String, Set<LookupValue>> getGroupMap();
}
