package com.nutracorp.labelbuilder.service;

import com.nutracorp.labelbuilder.domain.LookupValue;

import java.util.List;

/**
 * Service Interface for managing LookupValue.
 */
public interface LookupValueService {

    /**
     * Save a lookupValue.
     * @return the persisted entity
     */
    public LookupValue save(LookupValue lookupValue);

    /**
     *  get all the lookupValues.
     *  @return the list of entities
     */
    public List<LookupValue> findAll();

    /**
     *  get the "id" lookupValue.
     *  @return the entity
     */
    public LookupValue findOne(Long id);

    /**
     *  delete the "id" lookupValue.
     */
    public void delete(Long id);
}
