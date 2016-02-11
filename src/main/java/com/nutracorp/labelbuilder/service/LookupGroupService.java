package com.nutracorp.labelbuilder.service;

import com.nutracorp.labelbuilder.domain.LookupGroup;

import java.util.List;

/**
 * Service Interface for managing LookupGroup.
 */
public interface LookupGroupService {

    /**
     * Save a lookupGroup.
     * @return the persisted entity
     */
    public LookupGroup save(LookupGroup lookupGroup);

    /**
     *  get all the lookupGroups.
     *  @return the list of entities
     */
    public List<LookupGroup> findAll();

    /**
     *  get the "id" lookupGroup.
     *  @return the entity
     */
    public LookupGroup findOne(Long id);

    /**
     *  delete the "id" lookupGroup.
     */
    public void delete(Long id);
}
