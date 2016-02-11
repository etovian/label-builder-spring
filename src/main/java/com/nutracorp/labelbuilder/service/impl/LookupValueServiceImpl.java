package com.nutracorp.labelbuilder.service.impl;

import com.nutracorp.labelbuilder.service.LookupValueService;
import com.nutracorp.labelbuilder.domain.LookupValue;
import com.nutracorp.labelbuilder.repository.LookupValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing LookupValue.
 */
@Service
@Transactional
public class LookupValueServiceImpl implements LookupValueService{

    private final Logger log = LoggerFactory.getLogger(LookupValueServiceImpl.class);
    
    @Inject
    private LookupValueRepository lookupValueRepository;
    
    /**
     * Save a lookupValue.
     * @return the persisted entity
     */
    public LookupValue save(LookupValue lookupValue) {
        log.debug("Request to save LookupValue : {}", lookupValue);
        LookupValue result = lookupValueRepository.save(lookupValue);
        return result;
    }

    /**
     *  get all the lookupValues.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<LookupValue> findAll() {
        log.debug("Request to get all LookupValues");
        List<LookupValue> result = lookupValueRepository.findAll();
        return result;
    }

    /**
     *  get one lookupValue by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public LookupValue findOne(Long id) {
        log.debug("Request to get LookupValue : {}", id);
        LookupValue lookupValue = lookupValueRepository.findOne(id);
        return lookupValue;
    }

    /**
     *  delete the  lookupValue by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete LookupValue : {}", id);
        lookupValueRepository.delete(id);
    }
}
