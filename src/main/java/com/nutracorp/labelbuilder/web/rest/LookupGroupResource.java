package com.nutracorp.labelbuilder.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.nutracorp.labelbuilder.domain.LookupGroup;
import com.nutracorp.labelbuilder.domain.LookupValue;
import com.nutracorp.labelbuilder.service.LookupGroupService;
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
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * REST controller for managing LookupGroup.
 */
@RestController
@RequestMapping("/api")
public class LookupGroupResource {

    private final Logger log = LoggerFactory.getLogger(LookupGroupResource.class);

    @Inject
    private LookupGroupService lookupGroupService;

    /**
     * POST  /lookupGroups -> Create a new lookupGroup.
     */
    @RequestMapping(value = "/lookupGroups",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LookupGroup> createLookupGroup(@RequestBody LookupGroup lookupGroup) throws URISyntaxException {
        log.debug("REST request to save LookupGroup : {}", lookupGroup);
        if (lookupGroup.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("lookupGroup", "idexists", "A new lookupGroup cannot already have an ID")).body(null);
        }
        LookupGroup result = lookupGroupService.save(lookupGroup);
        return ResponseEntity.created(new URI("/api/lookupGroups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("lookupGroup", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /lookupGroups -> Updates an existing lookupGroup.
     */
    @RequestMapping(value = "/lookupGroups",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LookupGroup> updateLookupGroup(@RequestBody LookupGroup lookupGroup) throws URISyntaxException {
        log.debug("REST request to update LookupGroup : {}", lookupGroup);
        if (lookupGroup.getId() == null) {
            return createLookupGroup(lookupGroup);
        }
        LookupGroup result = lookupGroupService.save(lookupGroup);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("lookupGroup", lookupGroup.getId().toString()))
            .body(result);
    }

    /**
     * GET  /lookupGroups -> get all the lookupGroups.
     */
    @RequestMapping(value = "/lookupGroups",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<LookupGroup> getAllLookupGroups() {
        log.debug("REST request to get all LookupGroups");
        return lookupGroupService.findAll();
            }

    /**
     * GET  /lookupGroups/:id -> get the "id" lookupGroup.
     */
    @RequestMapping(value = "/lookupGroups/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<LookupGroup> getLookupGroup(@PathVariable Long id) {
        log.debug("REST request to get LookupGroup : {}", id);
        LookupGroup lookupGroup = lookupGroupService.findOne(id);
        return Optional.ofNullable(lookupGroup)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /lookupGroups/:id -> delete the "id" lookupGroup.
     */
    @RequestMapping(value = "/lookupGroups/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteLookupGroup(@PathVariable Long id) {
        log.debug("REST request to delete LookupGroup : {}", id);
        lookupGroupService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("lookupGroup", id.toString())).build();
    }

    /**
     * get a map of lookup groups
     */
    @RequestMapping(value = "/lookupGroups/map", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public Map<String, Set<LookupValue>> getGroupMap() {
        return lookupGroupService.getGroupMap();
    }
}
