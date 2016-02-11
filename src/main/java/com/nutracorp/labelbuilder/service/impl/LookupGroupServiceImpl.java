package com.nutracorp.labelbuilder.service.impl;

import com.nutracorp.labelbuilder.service.LookupGroupService;
import com.nutracorp.labelbuilder.domain.LookupGroup;
import com.nutracorp.labelbuilder.repository.LookupGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing LookupGroup.
 */
@Service
@Transactional
public class LookupGroupServiceImpl implements LookupGroupService{

    private final Logger log = LoggerFactory.getLogger(LookupGroupServiceImpl.class);
    
    @Inject
    private LookupGroupRepository lookupGroupRepository;
    
    /**
     * Save a lookupGroup.
     * @return the persisted entity
     */
    public LookupGroup save(LookupGroup lookupGroup) {
        log.debug("Request to save LookupGroup : {}", lookupGroup);
        LookupGroup result = lookupGroupRepository.save(lookupGroup);
        return result;
    }

    /**
     *  get all the lookupGroups.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<LookupGroup> findAll() {
        log.debug("Request to get all LookupGroups");
        List<LookupGroup> result = lookupGroupRepository.findAllWithEagerRelationships();
        return result;
    }

    /**
     *  get one lookupGroup by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public LookupGroup findOne(Long id) {
        log.debug("Request to get LookupGroup : {}", id);
        LookupGroup lookupGroup = lookupGroupRepository.findOneWithEagerRelationships(id);
        return lookupGroup;
    }

    /**
     *  delete the  lookupGroup by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete LookupGroup : {}", id);
        lookupGroupRepository.delete(id);
    }
}
