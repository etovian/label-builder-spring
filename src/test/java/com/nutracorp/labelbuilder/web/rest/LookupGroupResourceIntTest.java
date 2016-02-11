package com.nutracorp.labelbuilder.web.rest;

import com.nutracorp.labelbuilder.Application;
import com.nutracorp.labelbuilder.domain.LookupGroup;
import com.nutracorp.labelbuilder.repository.LookupGroupRepository;
import com.nutracorp.labelbuilder.service.LookupGroupService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the LookupGroupResource REST controller.
 *
 * @see LookupGroupResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class LookupGroupResourceIntTest {

    private static final String DEFAULT_GROUP_NAME = "AAAAA";
    private static final String UPDATED_GROUP_NAME = "BBBBB";

    @Inject
    private LookupGroupRepository lookupGroupRepository;

    @Inject
    private LookupGroupService lookupGroupService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restLookupGroupMockMvc;

    private LookupGroup lookupGroup;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LookupGroupResource lookupGroupResource = new LookupGroupResource();
        ReflectionTestUtils.setField(lookupGroupResource, "lookupGroupService", lookupGroupService);
        this.restLookupGroupMockMvc = MockMvcBuilders.standaloneSetup(lookupGroupResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        lookupGroup = new LookupGroup();
        lookupGroup.setGroupName(DEFAULT_GROUP_NAME);
    }

    @Test
    @Transactional
    public void createLookupGroup() throws Exception {
        int databaseSizeBeforeCreate = lookupGroupRepository.findAll().size();

        // Create the LookupGroup

        restLookupGroupMockMvc.perform(post("/api/lookupGroups")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(lookupGroup)))
                .andExpect(status().isCreated());

        // Validate the LookupGroup in the database
        List<LookupGroup> lookupGroups = lookupGroupRepository.findAll();
        assertThat(lookupGroups).hasSize(databaseSizeBeforeCreate + 1);
        LookupGroup testLookupGroup = lookupGroups.get(lookupGroups.size() - 1);
        assertThat(testLookupGroup.getGroupName()).isEqualTo(DEFAULT_GROUP_NAME);
    }

    @Test
    @Transactional
    public void getAllLookupGroups() throws Exception {
        // Initialize the database
        lookupGroupRepository.saveAndFlush(lookupGroup);

        // Get all the lookupGroups
        restLookupGroupMockMvc.perform(get("/api/lookupGroups?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(lookupGroup.getId().intValue())))
                .andExpect(jsonPath("$.[*].groupName").value(hasItem(DEFAULT_GROUP_NAME.toString())));
    }

    @Test
    @Transactional
    public void getLookupGroup() throws Exception {
        // Initialize the database
        lookupGroupRepository.saveAndFlush(lookupGroup);

        // Get the lookupGroup
        restLookupGroupMockMvc.perform(get("/api/lookupGroups/{id}", lookupGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(lookupGroup.getId().intValue()))
            .andExpect(jsonPath("$.groupName").value(DEFAULT_GROUP_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLookupGroup() throws Exception {
        // Get the lookupGroup
        restLookupGroupMockMvc.perform(get("/api/lookupGroups/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLookupGroup() throws Exception {
        // Initialize the database
        lookupGroupRepository.saveAndFlush(lookupGroup);

		int databaseSizeBeforeUpdate = lookupGroupRepository.findAll().size();

        // Update the lookupGroup
        lookupGroup.setGroupName(UPDATED_GROUP_NAME);

        restLookupGroupMockMvc.perform(put("/api/lookupGroups")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(lookupGroup)))
                .andExpect(status().isOk());

        // Validate the LookupGroup in the database
        List<LookupGroup> lookupGroups = lookupGroupRepository.findAll();
        assertThat(lookupGroups).hasSize(databaseSizeBeforeUpdate);
        LookupGroup testLookupGroup = lookupGroups.get(lookupGroups.size() - 1);
        assertThat(testLookupGroup.getGroupName()).isEqualTo(UPDATED_GROUP_NAME);
    }

    @Test
    @Transactional
    public void deleteLookupGroup() throws Exception {
        // Initialize the database
        lookupGroupRepository.saveAndFlush(lookupGroup);

		int databaseSizeBeforeDelete = lookupGroupRepository.findAll().size();

        // Get the lookupGroup
        restLookupGroupMockMvc.perform(delete("/api/lookupGroups/{id}", lookupGroup.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<LookupGroup> lookupGroups = lookupGroupRepository.findAll();
        assertThat(lookupGroups).hasSize(databaseSizeBeforeDelete - 1);
    }
}
