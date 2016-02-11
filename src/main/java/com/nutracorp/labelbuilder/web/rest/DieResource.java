package com.nutracorp.labelbuilder.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.nutracorp.labelbuilder.domain.Die;
import com.nutracorp.labelbuilder.service.DieService;
import com.nutracorp.labelbuilder.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Die.
 */
@RestController
@RequestMapping("/api")
public class DieResource {

    private final Logger log = LoggerFactory.getLogger(DieResource.class);
        
    @Inject
    private DieService dieService;
    
    /**
     * POST  /dies -> Create a new die.
     */
    @RequestMapping(value = "/dies",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Die> createDie(@RequestBody Die die) throws URISyntaxException {
        log.debug("REST request to save Die : {}", die);
        if (die.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("die", "idexists", "A new die cannot already have an ID")).body(null);
        }
        Die result = dieService.save(die);
        return ResponseEntity.created(new URI("/api/dies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("die", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dies -> Updates an existing die.
     */
    @RequestMapping(value = "/dies",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Die> updateDie(@RequestBody Die die) throws URISyntaxException {
        log.debug("REST request to update Die : {}", die);
        if (die.getId() == null) {
            return createDie(die);
        }
        Die result = dieService.save(die);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("die", die.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dies -> get all the dies.
     */
    @RequestMapping(value = "/dies",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Die> getAllDies() {
        log.debug("REST request to get all Dies");
        return dieService.findAll();
            }

    /**
     * GET  /dies/:id -> get the "id" die.
     */
    @RequestMapping(value = "/dies/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Die> getDie(@PathVariable Long id) {
        log.debug("REST request to get Die : {}", id);
        Die die = dieService.findOne(id);
        return Optional.ofNullable(die)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /dies/:id -> delete the "id" die.
     */
    @RequestMapping(value = "/dies/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteDie(@PathVariable Long id) {
        log.debug("REST request to delete Die : {}", id);
        dieService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("die", id.toString())).build();
    }
}
