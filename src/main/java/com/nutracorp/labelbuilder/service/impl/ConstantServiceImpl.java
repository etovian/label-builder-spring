package com.nutracorp.labelbuilder.service.impl;

import com.nutracorp.labelbuilder.service.ConstantService;
import com.nutracorp.labelbuilder.domain.Constant;
import com.nutracorp.labelbuilder.repository.ConstantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Constant.
 */
@Service
@Transactional
public class ConstantServiceImpl implements ConstantService{

    private final Logger log = LoggerFactory.getLogger(ConstantServiceImpl.class);
    
    @Inject
    private ConstantRepository constantRepository;
    
    /**
     * Save a constant.
     * @return the persisted entity
     */
    public Constant save(Constant constant) {
        log.debug("Request to save Constant : {}", constant);
        Constant result = constantRepository.save(constant);
        return result;
    }

    /**
     *  get all the constants.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Constant> findAll() {
        log.debug("Request to get all Constants");
        List<Constant> result = constantRepository.findAll();
        return result;
    }

    /**
     *  get one constant by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Constant findOne(Long id) {
        log.debug("Request to get Constant : {}", id);
        Constant constant = constantRepository.findOne(id);
        return constant;
    }

    /**
     *  delete the  constant by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Constant : {}", id);
        constantRepository.delete(id);
    }
}
