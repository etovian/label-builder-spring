package com.nutracorp.labelbuilder.service.impl;

import com.nutracorp.labelbuilder.service.DieService;
import com.nutracorp.labelbuilder.domain.Die;
import com.nutracorp.labelbuilder.repository.DieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Die.
 */
@Service
@Transactional
public class DieServiceImpl implements DieService{

    private final Logger log = LoggerFactory.getLogger(DieServiceImpl.class);
    
    @Inject
    private DieRepository dieRepository;
    
    /**
     * Save a die.
     * @return the persisted entity
     */
    public Die save(Die die) {
        log.debug("Request to save Die : {}", die);
        Die result = dieRepository.save(die);
        return result;
    }

    /**
     *  get all the dies.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Die> findAll() {
        log.debug("Request to get all Dies");
        List<Die> result = dieRepository.findAll();
        return result;
    }

    /**
     *  get one die by id.
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Die findOne(Long id) {
        log.debug("Request to get Die : {}", id);
        Die die = dieRepository.findOne(id);
        return die;
    }

    /**
     *  delete the  die by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete Die : {}", id);
        dieRepository.delete(id);
    }
}
