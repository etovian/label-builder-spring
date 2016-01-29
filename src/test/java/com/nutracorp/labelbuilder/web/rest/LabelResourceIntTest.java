package com.nutracorp.labelbuilder.web.rest;

import com.nutracorp.labelbuilder.Application;
import com.nutracorp.labelbuilder.domain.Label;
import com.nutracorp.labelbuilder.repository.LabelRepository;
import com.nutracorp.labelbuilder.service.LabelService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the LabelResource REST controller.
 *
 * @see LabelResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class LabelResourceIntTest {

    private static final String DEFAULT_PRODUCT_ID = "AAAAA";
    private static final String UPDATED_PRODUCT_ID = "BBBBB";
    private static final String DEFAULT_VERSION_MAJOR = "AAAAA";
    private static final String UPDATED_VERSION_MAJOR = "BBBBB";
    private static final String DEFAULT_VERSION_MINOR = "AAAAA";
    private static final String UPDATED_VERSION_MINOR = "BBBBB";

    private static final LocalDate DEFAULT_CREATED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_DISCUSSION = "AAAAA";
    private static final String UPDATED_DISCUSSION = "BBBBB";
    private static final String DEFAULT_PRODUCT_NAME = "AAAAA";
    private static final String UPDATED_PRODUCT_NAME = "BBBBB";
    private static final String DEFAULT_CONTENT_COUNT = "AAAAA";
    private static final String UPDATED_CONTENT_COUNT = "BBBBB";
    private static final String DEFAULT_SERVING_SIZE = "AAAAA";
    private static final String UPDATED_SERVING_SIZE = "BBBBB";
    private static final String DEFAULT_DELIVERY_FORM = "AAAAA";
    private static final String UPDATED_DELIVERY_FORM = "BBBBB";
    private static final String DEFAULT_DOSAGE_CONSISTENCY = "AAAAA";
    private static final String UPDATED_DOSAGE_CONSISTENCY = "BBBBB";
    private static final String DEFAULT_GENERIC_DESCRIPTION = "AAAAA";
    private static final String UPDATED_GENERIC_DESCRIPTION = "BBBBB";
    private static final String DEFAULT_UPC = "AAAAA";
    private static final String UPDATED_UPC = "BBBBB";
    private static final String DEFAULT_WARNING = "AAAAA";
    private static final String UPDATED_WARNING = "BBBBB";
    private static final String DEFAULT_DIRECTIONS = "AAAAA";
    private static final String UPDATED_DIRECTIONS = "BBBBB";
    private static final String DEFAULT_REFRIGERATED = "AAAAA";
    private static final String UPDATED_REFRIGERATED = "BBBBB";

    private static final Boolean DEFAULT_IS_PEEL_OFF = false;
    private static final Boolean UPDATED_IS_PEEL_OFF = true;

    @Inject
    private LabelRepository labelRepository;

    @Inject
    private LabelService labelService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restLabelMockMvc;

    private Label label;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LabelResource labelResource = new LabelResource();
        ReflectionTestUtils.setField(labelResource, "labelService", labelService);
        this.restLabelMockMvc = MockMvcBuilders.standaloneSetup(labelResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        label = new Label();
        label.setProductId(DEFAULT_PRODUCT_ID);
        label.setVersionMajor(DEFAULT_VERSION_MAJOR);
        label.setVersionMinor(DEFAULT_VERSION_MINOR);
        label.setCreated(DEFAULT_CREATED);
        label.setDiscussion(DEFAULT_DISCUSSION);
        label.setProductName(DEFAULT_PRODUCT_NAME);
        label.setContentCount(DEFAULT_CONTENT_COUNT);
        label.setServingSize(DEFAULT_SERVING_SIZE);
        label.setDeliveryForm(DEFAULT_DELIVERY_FORM);
        label.setDosageConsistency(DEFAULT_DOSAGE_CONSISTENCY);
        label.setGenericDescription(DEFAULT_GENERIC_DESCRIPTION);
        label.setUpc(DEFAULT_UPC);
        label.setWarning(DEFAULT_WARNING);
        label.setDirections(DEFAULT_DIRECTIONS);
        label.setRefrigerated(DEFAULT_REFRIGERATED);
        label.setIsPeelOff(DEFAULT_IS_PEEL_OFF);
    }

    @Test
    @Transactional
    public void createLabel() throws Exception {
        int databaseSizeBeforeCreate = labelRepository.findAll().size();

        // Create the Label

        restLabelMockMvc.perform(post("/api/labels")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(label)))
                .andExpect(status().isCreated());

        // Validate the Label in the database
        List<Label> labels = labelRepository.findAll();
        assertThat(labels).hasSize(databaseSizeBeforeCreate + 1);
        Label testLabel = labels.get(labels.size() - 1);
        assertThat(testLabel.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testLabel.getVersionMajor()).isEqualTo(DEFAULT_VERSION_MAJOR);
        assertThat(testLabel.getVersionMinor()).isEqualTo(DEFAULT_VERSION_MINOR);
        assertThat(testLabel.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testLabel.getDiscussion()).isEqualTo(DEFAULT_DISCUSSION);
        assertThat(testLabel.getProductName()).isEqualTo(DEFAULT_PRODUCT_NAME);
        assertThat(testLabel.getContentCount()).isEqualTo(DEFAULT_CONTENT_COUNT);
        assertThat(testLabel.getServingSize()).isEqualTo(DEFAULT_SERVING_SIZE);
        assertThat(testLabel.getDeliveryForm()).isEqualTo(DEFAULT_DELIVERY_FORM);
        assertThat(testLabel.getDosageConsistency()).isEqualTo(DEFAULT_DOSAGE_CONSISTENCY);
        assertThat(testLabel.getGenericDescription()).isEqualTo(DEFAULT_GENERIC_DESCRIPTION);
        assertThat(testLabel.getUpc()).isEqualTo(DEFAULT_UPC);
        assertThat(testLabel.getWarning()).isEqualTo(DEFAULT_WARNING);
        assertThat(testLabel.getDirections()).isEqualTo(DEFAULT_DIRECTIONS);
        assertThat(testLabel.getRefrigerated()).isEqualTo(DEFAULT_REFRIGERATED);
        assertThat(testLabel.getIsPeelOff()).isEqualTo(DEFAULT_IS_PEEL_OFF);
    }

    @Test
    @Transactional
    public void getAllLabels() throws Exception {
        // Initialize the database
        labelRepository.saveAndFlush(label);

        // Get all the labels
        restLabelMockMvc.perform(get("/api/labels?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(label.getId().intValue())))
                .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID.toString())))
                .andExpect(jsonPath("$.[*].versionMajor").value(hasItem(DEFAULT_VERSION_MAJOR.toString())))
                .andExpect(jsonPath("$.[*].versionMinor").value(hasItem(DEFAULT_VERSION_MINOR.toString())))
                .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
                .andExpect(jsonPath("$.[*].discussion").value(hasItem(DEFAULT_DISCUSSION.toString())))
                .andExpect(jsonPath("$.[*].productName").value(hasItem(DEFAULT_PRODUCT_NAME.toString())))
                .andExpect(jsonPath("$.[*].contentCount").value(hasItem(DEFAULT_CONTENT_COUNT.toString())))
                .andExpect(jsonPath("$.[*].servingSize").value(hasItem(DEFAULT_SERVING_SIZE.toString())))
                .andExpect(jsonPath("$.[*].deliveryForm").value(hasItem(DEFAULT_DELIVERY_FORM.toString())))
                .andExpect(jsonPath("$.[*].dosageConsistency").value(hasItem(DEFAULT_DOSAGE_CONSISTENCY.toString())))
                .andExpect(jsonPath("$.[*].genericDescription").value(hasItem(DEFAULT_GENERIC_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].upc").value(hasItem(DEFAULT_UPC.toString())))
                .andExpect(jsonPath("$.[*].warning").value(hasItem(DEFAULT_WARNING.toString())))
                .andExpect(jsonPath("$.[*].directions").value(hasItem(DEFAULT_DIRECTIONS.toString())))
                .andExpect(jsonPath("$.[*].refrigerated").value(hasItem(DEFAULT_REFRIGERATED.toString())))
                .andExpect(jsonPath("$.[*].isPeelOff").value(hasItem(DEFAULT_IS_PEEL_OFF.booleanValue())));
    }

    @Test
    @Transactional
    public void getLabel() throws Exception {
        // Initialize the database
        labelRepository.saveAndFlush(label);

        // Get the label
        restLabelMockMvc.perform(get("/api/labels/{id}", label.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(label.getId().intValue()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID.toString()))
            .andExpect(jsonPath("$.versionMajor").value(DEFAULT_VERSION_MAJOR.toString()))
            .andExpect(jsonPath("$.versionMinor").value(DEFAULT_VERSION_MINOR.toString()))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.discussion").value(DEFAULT_DISCUSSION.toString()))
            .andExpect(jsonPath("$.productName").value(DEFAULT_PRODUCT_NAME.toString()))
            .andExpect(jsonPath("$.contentCount").value(DEFAULT_CONTENT_COUNT.toString()))
            .andExpect(jsonPath("$.servingSize").value(DEFAULT_SERVING_SIZE.toString()))
            .andExpect(jsonPath("$.deliveryForm").value(DEFAULT_DELIVERY_FORM.toString()))
            .andExpect(jsonPath("$.dosageConsistency").value(DEFAULT_DOSAGE_CONSISTENCY.toString()))
            .andExpect(jsonPath("$.genericDescription").value(DEFAULT_GENERIC_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.upc").value(DEFAULT_UPC.toString()))
            .andExpect(jsonPath("$.warning").value(DEFAULT_WARNING.toString()))
            .andExpect(jsonPath("$.directions").value(DEFAULT_DIRECTIONS.toString()))
            .andExpect(jsonPath("$.refrigerated").value(DEFAULT_REFRIGERATED.toString()))
            .andExpect(jsonPath("$.isPeelOff").value(DEFAULT_IS_PEEL_OFF.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingLabel() throws Exception {
        // Get the label
        restLabelMockMvc.perform(get("/api/labels/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLabel() throws Exception {
        // Initialize the database
        labelRepository.saveAndFlush(label);

		int databaseSizeBeforeUpdate = labelRepository.findAll().size();

        // Update the label
        label.setProductId(UPDATED_PRODUCT_ID);
        label.setVersionMajor(UPDATED_VERSION_MAJOR);
        label.setVersionMinor(UPDATED_VERSION_MINOR);
        label.setCreated(UPDATED_CREATED);
        label.setDiscussion(UPDATED_DISCUSSION);
        label.setProductName(UPDATED_PRODUCT_NAME);
        label.setContentCount(UPDATED_CONTENT_COUNT);
        label.setServingSize(UPDATED_SERVING_SIZE);
        label.setDeliveryForm(UPDATED_DELIVERY_FORM);
        label.setDosageConsistency(UPDATED_DOSAGE_CONSISTENCY);
        label.setGenericDescription(UPDATED_GENERIC_DESCRIPTION);
        label.setUpc(UPDATED_UPC);
        label.setWarning(UPDATED_WARNING);
        label.setDirections(UPDATED_DIRECTIONS);
        label.setRefrigerated(UPDATED_REFRIGERATED);
        label.setIsPeelOff(UPDATED_IS_PEEL_OFF);

        restLabelMockMvc.perform(put("/api/labels")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(label)))
                .andExpect(status().isOk());

        // Validate the Label in the database
        List<Label> labels = labelRepository.findAll();
        assertThat(labels).hasSize(databaseSizeBeforeUpdate);
        Label testLabel = labels.get(labels.size() - 1);
        assertThat(testLabel.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testLabel.getVersionMajor()).isEqualTo(UPDATED_VERSION_MAJOR);
        assertThat(testLabel.getVersionMinor()).isEqualTo(UPDATED_VERSION_MINOR);
        assertThat(testLabel.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testLabel.getDiscussion()).isEqualTo(UPDATED_DISCUSSION);
        assertThat(testLabel.getProductName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(testLabel.getContentCount()).isEqualTo(UPDATED_CONTENT_COUNT);
        assertThat(testLabel.getServingSize()).isEqualTo(UPDATED_SERVING_SIZE);
        assertThat(testLabel.getDeliveryForm()).isEqualTo(UPDATED_DELIVERY_FORM);
        assertThat(testLabel.getDosageConsistency()).isEqualTo(UPDATED_DOSAGE_CONSISTENCY);
        assertThat(testLabel.getGenericDescription()).isEqualTo(UPDATED_GENERIC_DESCRIPTION);
        assertThat(testLabel.getUpc()).isEqualTo(UPDATED_UPC);
        assertThat(testLabel.getWarning()).isEqualTo(UPDATED_WARNING);
        assertThat(testLabel.getDirections()).isEqualTo(UPDATED_DIRECTIONS);
        assertThat(testLabel.getRefrigerated()).isEqualTo(UPDATED_REFRIGERATED);
        assertThat(testLabel.getIsPeelOff()).isEqualTo(UPDATED_IS_PEEL_OFF);
    }

    @Test
    @Transactional
    public void deleteLabel() throws Exception {
        // Initialize the database
        labelRepository.saveAndFlush(label);

		int databaseSizeBeforeDelete = labelRepository.findAll().size();

        // Get the label
        restLabelMockMvc.perform(delete("/api/labels/{id}", label.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Label> labels = labelRepository.findAll();
        assertThat(labels).hasSize(databaseSizeBeforeDelete - 1);
    }
}
