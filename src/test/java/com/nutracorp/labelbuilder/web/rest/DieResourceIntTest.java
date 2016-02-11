package com.nutracorp.labelbuilder.web.rest;

import com.nutracorp.labelbuilder.Application;
import com.nutracorp.labelbuilder.domain.Die;
import com.nutracorp.labelbuilder.repository.DieRepository;
import com.nutracorp.labelbuilder.service.DieService;

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
 * Test class for the DieResource REST controller.
 *
 * @see DieResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DieResourceIntTest {

    private static final String DEFAULT_BRAND_CODE = "AAAAA";
    private static final String UPDATED_BRAND_CODE = "BBBBB";
    private static final String DEFAULT_DIE_NAME = "AAAAA";
    private static final String UPDATED_DIE_NAME = "BBBBB";

    private static final Float DEFAULT_OVERALL_WIDTH = 1F;
    private static final Float UPDATED_OVERALL_WIDTH = 2F;

    private static final Float DEFAULT_OVERALL_HEIGHT = 1F;
    private static final Float UPDATED_OVERALL_HEIGHT = 2F;

    private static final Integer DEFAULT_BP_ROTATION = 1;
    private static final Integer UPDATED_BP_ROTATION = 2;

    private static final Float DEFAULT_FP_PANEL_IMAGE_WIDTH = 1F;
    private static final Float UPDATED_FP_PANEL_IMAGE_WIDTH = 2F;

    private static final Float DEFAULT_FP_IMAGE_HEIGHT = 1F;
    private static final Float UPDATED_FP_IMAGE_HEIGHT = 2F;

    private static final Boolean DEFAULT_HAS_BORDER = false;
    private static final Boolean UPDATED_HAS_BORDER = true;

    private static final Float DEFAULT_BORDER_WIDTH = 1F;
    private static final Float UPDATED_BORDER_WIDTH = 2F;

    private static final Float DEFAULT_BORDER_TOP_INSET = 1F;
    private static final Float UPDATED_BORDER_TOP_INSET = 2F;

    private static final Float DEFAULT_BORDER_LEFT_INSET = 1F;
    private static final Float UPDATED_BORDER_LEFT_INSET = 2F;

    private static final Float DEFAULT_BORDER_RIGHT_INSET = 1F;
    private static final Float UPDATED_BORDER_RIGHT_INSET = 2F;

    private static final Float DEFAULT_BP_MARGIN_TOP = 1F;
    private static final Float UPDATED_BP_MARGIN_TOP = 2F;

    private static final Float DEFAULT_BP_MARGIN_LEFT = 1F;
    private static final Float UPDATED_BP_MARGIN_LEFT = 2F;

    private static final Float DEFAULT_BP_MARGIN_RIGHT = 1F;
    private static final Float UPDATED_BP_MARGIN_RIGHT = 2F;

    private static final Float DEFAULT_SUP_FACT_APS = 1F;
    private static final Float UPDATED_SUP_FACT_APS = 2F;

    private static final Float DEFAULT_SUP_FACT_DV = 1F;
    private static final Float UPDATED_SUP_FACT_DV = 2F;

    private static final Float DEFAULT_NUT_FACT_DV = 1F;
    private static final Float UPDATED_NUT_FACT_DV = 2F;

    private static final Float DEFAULT_MFG_INFO_FONT_SIZE = 1F;
    private static final Float UPDATED_MFG_INFO_FONT_SIZE = 2F;

    private static final Float DEFAULT_USE_BY_FONT_SIZE = 1F;
    private static final Float UPDATED_USE_BY_FONT_SIZE = 2F;

    private static final LocalDate DEFAULT_LAST_MODIFIED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_MODIFIED = LocalDate.now(ZoneId.systemDefault());

    private static final Float DEFAULT_FP_TOP_BUMP_HEIGHT = 1F;
    private static final Float UPDATED_FP_TOP_BUMP_HEIGHT = 2F;

    private static final Float DEFAULT_SUP_FACT_APS_DATA_INSET = 1F;
    private static final Float UPDATED_SUP_FACT_APS_DATA_INSET = 2F;
    private static final String DEFAULT_MFG_INFO_DISPLAY_FORMAT = "AAAAA";
    private static final String UPDATED_MFG_INFO_DISPLAY_FORMAT = "BBBBB";
    private static final String DEFAULT_TEXT_ALIGNMENT = "AAAAA";
    private static final String UPDATED_TEXT_ALIGNMENT = "BBBBB";

    private static final Integer DEFAULT_UPC_FONT_SIZE = 1;
    private static final Integer UPDATED_UPC_FONT_SIZE = 2;

    private static final Boolean DEFAULT_HAS_MINI_FACTS_BOX = false;
    private static final Boolean UPDATED_HAS_MINI_FACTS_BOX = true;

    private static final Float DEFAULT_NUT_FACTS_APS = 1F;
    private static final Float UPDATED_NUT_FACTS_APS = 2F;

    private static final Integer DEFAULT_PEEL_OFF_PANEL_ROTATION = 1;
    private static final Integer UPDATED_PEEL_OFF_PANEL_ROTATION = 2;

    private static final Float DEFAULT_PEEL_OFF_MARGIN_TOP = 1F;
    private static final Float UPDATED_PEEL_OFF_MARGIN_TOP = 2F;

    private static final Float DEFAULT_PEEL_OFF_MARGIN_LEFT = 1F;
    private static final Float UPDATED_PEEL_OFF_MARGIN_LEFT = 2F;

    private static final Float DEFAULT_PEEL_OFF_MARGIN_RIGHT = 1F;
    private static final Float UPDATED_PEEL_OFF_MARGIN_RIGHT = 2F;

    private static final Float DEFAULT_PEEL_OFF_MARGIN_BOTTOM = 1F;
    private static final Float UPDATED_PEEL_OFF_MARGIN_BOTTOM = 2F;

    private static final Boolean DEFAULT_ALLOW_PEEL_OFF = false;
    private static final Boolean UPDATED_ALLOW_PEEL_OFF = true;

    private static final Float DEFAULT_BP_DIVIDER_MARGIN = 1F;
    private static final Float UPDATED_BP_DIVIDER_MARGIN = 2F;

    private static final Float DEFAULT_COLUMN_DIVIDER_MARGIN = 1F;
    private static final Float UPDATED_COLUMN_DIVIDER_MARGIN = 2F;

    private static final Boolean DEFAULT_SUB_NUTRITIONAL_SEPARATORS = false;
    private static final Boolean UPDATED_SUB_NUTRITIONAL_SEPARATORS = true;

    private static final Float DEFAULT_CALORIE_COMPARISON_LOW_WIDTH = 1F;
    private static final Float UPDATED_CALORIE_COMPARISON_LOW_WIDTH = 2F;

    private static final Float DEFAULT_CALORIE_COMPARISON_HIGH_WIDTH = 1F;
    private static final Float UPDATED_CALORIE_COMPARISON_HIGH_WIDTH = 2F;

    private static final Float DEFAULT_CALORIE_COMPARISON_LT_WIDTH = 1F;
    private static final Float UPDATED_CALORIE_COMPARISON_LT_WIDTH = 2F;

    private static final Float DEFAULT_BP_MARGIN_BOTTOM = 1F;
    private static final Float UPDATED_BP_MARGIN_BOTTOM = 2F;

    private static final Float DEFAULT_PEEL_OFF_GLUE_ALLOWANCE = 1F;
    private static final Float UPDATED_PEEL_OFF_GLUE_ALLOWANCE = 2F;

    private static final Boolean DEFAULT_DATA_ONLY = false;
    private static final Boolean UPDATED_DATA_ONLY = true;

    @Inject
    private DieRepository dieRepository;

    @Inject
    private DieService dieService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDieMockMvc;

    private Die die;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DieResource dieResource = new DieResource();
        ReflectionTestUtils.setField(dieResource, "dieService", dieService);
        this.restDieMockMvc = MockMvcBuilders.standaloneSetup(dieResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        die = new Die();
        die.setBrandCode(DEFAULT_BRAND_CODE);
        die.setDieName(DEFAULT_DIE_NAME);
        die.setOverallWidth(DEFAULT_OVERALL_WIDTH);
        die.setOverallHeight(DEFAULT_OVERALL_HEIGHT);
        die.setBpRotation(DEFAULT_BP_ROTATION);
        die.setFpPanelImageWidth(DEFAULT_FP_PANEL_IMAGE_WIDTH);
        die.setFpImageHeight(DEFAULT_FP_IMAGE_HEIGHT);
        die.setHasBorder(DEFAULT_HAS_BORDER);
        die.setBorderWidth(DEFAULT_BORDER_WIDTH);
        die.setBorderTopInset(DEFAULT_BORDER_TOP_INSET);
        die.setBorderLeftInset(DEFAULT_BORDER_LEFT_INSET);
        die.setBorderRightInset(DEFAULT_BORDER_RIGHT_INSET);
        die.setBpMarginTop(DEFAULT_BP_MARGIN_TOP);
        die.setBpMarginLeft(DEFAULT_BP_MARGIN_LEFT);
        die.setBpMarginRight(DEFAULT_BP_MARGIN_RIGHT);
        die.setSupFactAps(DEFAULT_SUP_FACT_APS);
        die.setSupFactDv(DEFAULT_SUP_FACT_DV);
        die.setNutFactDv(DEFAULT_NUT_FACT_DV);
        die.setMfgInfoFontSize(DEFAULT_MFG_INFO_FONT_SIZE);
        die.setUseByFontSize(DEFAULT_USE_BY_FONT_SIZE);
        die.setLastModified(DEFAULT_LAST_MODIFIED);
        die.setFpTopBumpHeight(DEFAULT_FP_TOP_BUMP_HEIGHT);
        die.setSupFactApsDataInset(DEFAULT_SUP_FACT_APS_DATA_INSET);
        die.setMfgInfoDisplayFormat(DEFAULT_MFG_INFO_DISPLAY_FORMAT);
        die.setTextAlignment(DEFAULT_TEXT_ALIGNMENT);
        die.setUpcFontSize(DEFAULT_UPC_FONT_SIZE);
        die.setHasMiniFactsBox(DEFAULT_HAS_MINI_FACTS_BOX);
        die.setNutFactsAps(DEFAULT_NUT_FACTS_APS);
        die.setPeelOffPanelRotation(DEFAULT_PEEL_OFF_PANEL_ROTATION);
        die.setPeelOffMarginTop(DEFAULT_PEEL_OFF_MARGIN_TOP);
        die.setPeelOffMarginLeft(DEFAULT_PEEL_OFF_MARGIN_LEFT);
        die.setPeelOffMarginRight(DEFAULT_PEEL_OFF_MARGIN_RIGHT);
        die.setPeelOffMarginBottom(DEFAULT_PEEL_OFF_MARGIN_BOTTOM);
        die.setAllowPeelOff(DEFAULT_ALLOW_PEEL_OFF);
        die.setBpDividerMargin(DEFAULT_BP_DIVIDER_MARGIN);
        die.setColumnDividerMargin(DEFAULT_COLUMN_DIVIDER_MARGIN);
        die.setSubNutritionalSeparators(DEFAULT_SUB_NUTRITIONAL_SEPARATORS);
        die.setCalorieComparisonLowWidth(DEFAULT_CALORIE_COMPARISON_LOW_WIDTH);
        die.setCalorieComparisonHighWidth(DEFAULT_CALORIE_COMPARISON_HIGH_WIDTH);
        die.setCalorieComparisonLtWidth(DEFAULT_CALORIE_COMPARISON_LT_WIDTH);
        die.setBpMarginBottom(DEFAULT_BP_MARGIN_BOTTOM);
        die.setPeelOffGlueAllowance(DEFAULT_PEEL_OFF_GLUE_ALLOWANCE);
        die.setDataOnly(DEFAULT_DATA_ONLY);
    }

    @Test
    @Transactional
    public void createDie() throws Exception {
        int databaseSizeBeforeCreate = dieRepository.findAll().size();

        // Create the Die

        restDieMockMvc.perform(post("/api/dies")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(die)))
                .andExpect(status().isCreated());

        // Validate the Die in the database
        List<Die> dies = dieRepository.findAll();
        assertThat(dies).hasSize(databaseSizeBeforeCreate + 1);
        Die testDie = dies.get(dies.size() - 1);
        assertThat(testDie.getBrandCode()).isEqualTo(DEFAULT_BRAND_CODE);
        assertThat(testDie.getDieName()).isEqualTo(DEFAULT_DIE_NAME);
        assertThat(testDie.getOverallWidth()).isEqualTo(DEFAULT_OVERALL_WIDTH);
        assertThat(testDie.getOverallHeight()).isEqualTo(DEFAULT_OVERALL_HEIGHT);
        assertThat(testDie.getBpRotation()).isEqualTo(DEFAULT_BP_ROTATION);
        assertThat(testDie.getFpPanelImageWidth()).isEqualTo(DEFAULT_FP_PANEL_IMAGE_WIDTH);
        assertThat(testDie.getFpImageHeight()).isEqualTo(DEFAULT_FP_IMAGE_HEIGHT);
        assertThat(testDie.getHasBorder()).isEqualTo(DEFAULT_HAS_BORDER);
        assertThat(testDie.getBorderWidth()).isEqualTo(DEFAULT_BORDER_WIDTH);
        assertThat(testDie.getBorderTopInset()).isEqualTo(DEFAULT_BORDER_TOP_INSET);
        assertThat(testDie.getBorderLeftInset()).isEqualTo(DEFAULT_BORDER_LEFT_INSET);
        assertThat(testDie.getBorderRightInset()).isEqualTo(DEFAULT_BORDER_RIGHT_INSET);
        assertThat(testDie.getBpMarginTop()).isEqualTo(DEFAULT_BP_MARGIN_TOP);
        assertThat(testDie.getBpMarginLeft()).isEqualTo(DEFAULT_BP_MARGIN_LEFT);
        assertThat(testDie.getBpMarginRight()).isEqualTo(DEFAULT_BP_MARGIN_RIGHT);
        assertThat(testDie.getSupFactAps()).isEqualTo(DEFAULT_SUP_FACT_APS);
        assertThat(testDie.getSupFactDv()).isEqualTo(DEFAULT_SUP_FACT_DV);
        assertThat(testDie.getNutFactDv()).isEqualTo(DEFAULT_NUT_FACT_DV);
        assertThat(testDie.getMfgInfoFontSize()).isEqualTo(DEFAULT_MFG_INFO_FONT_SIZE);
        assertThat(testDie.getUseByFontSize()).isEqualTo(DEFAULT_USE_BY_FONT_SIZE);
        assertThat(testDie.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
        assertThat(testDie.getFpTopBumpHeight()).isEqualTo(DEFAULT_FP_TOP_BUMP_HEIGHT);
        assertThat(testDie.getSupFactApsDataInset()).isEqualTo(DEFAULT_SUP_FACT_APS_DATA_INSET);
        assertThat(testDie.getMfgInfoDisplayFormat()).isEqualTo(DEFAULT_MFG_INFO_DISPLAY_FORMAT);
        assertThat(testDie.getTextAlignment()).isEqualTo(DEFAULT_TEXT_ALIGNMENT);
        assertThat(testDie.getUpcFontSize()).isEqualTo(DEFAULT_UPC_FONT_SIZE);
        assertThat(testDie.getHasMiniFactsBox()).isEqualTo(DEFAULT_HAS_MINI_FACTS_BOX);
        assertThat(testDie.getNutFactsAps()).isEqualTo(DEFAULT_NUT_FACTS_APS);
        assertThat(testDie.getPeelOffPanelRotation()).isEqualTo(DEFAULT_PEEL_OFF_PANEL_ROTATION);
        assertThat(testDie.getPeelOffMarginTop()).isEqualTo(DEFAULT_PEEL_OFF_MARGIN_TOP);
        assertThat(testDie.getPeelOffMarginLeft()).isEqualTo(DEFAULT_PEEL_OFF_MARGIN_LEFT);
        assertThat(testDie.getPeelOffMarginRight()).isEqualTo(DEFAULT_PEEL_OFF_MARGIN_RIGHT);
        assertThat(testDie.getPeelOffMarginBottom()).isEqualTo(DEFAULT_PEEL_OFF_MARGIN_BOTTOM);
        assertThat(testDie.getAllowPeelOff()).isEqualTo(DEFAULT_ALLOW_PEEL_OFF);
        assertThat(testDie.getBpDividerMargin()).isEqualTo(DEFAULT_BP_DIVIDER_MARGIN);
        assertThat(testDie.getColumnDividerMargin()).isEqualTo(DEFAULT_COLUMN_DIVIDER_MARGIN);
        assertThat(testDie.getSubNutritionalSeparators()).isEqualTo(DEFAULT_SUB_NUTRITIONAL_SEPARATORS);
        assertThat(testDie.getCalorieComparisonLowWidth()).isEqualTo(DEFAULT_CALORIE_COMPARISON_LOW_WIDTH);
        assertThat(testDie.getCalorieComparisonHighWidth()).isEqualTo(DEFAULT_CALORIE_COMPARISON_HIGH_WIDTH);
        assertThat(testDie.getCalorieComparisonLtWidth()).isEqualTo(DEFAULT_CALORIE_COMPARISON_LT_WIDTH);
        assertThat(testDie.getBpMarginBottom()).isEqualTo(DEFAULT_BP_MARGIN_BOTTOM);
        assertThat(testDie.getPeelOffGlueAllowance()).isEqualTo(DEFAULT_PEEL_OFF_GLUE_ALLOWANCE);
        assertThat(testDie.getDataOnly()).isEqualTo(DEFAULT_DATA_ONLY);
    }

    @Test
    @Transactional
    public void getAllDies() throws Exception {
        // Initialize the database
        dieRepository.saveAndFlush(die);

        // Get all the dies
        restDieMockMvc.perform(get("/api/dies?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(die.getId().intValue())))
                .andExpect(jsonPath("$.[*].brandCode").value(hasItem(DEFAULT_BRAND_CODE.toString())))
                .andExpect(jsonPath("$.[*].dieName").value(hasItem(DEFAULT_DIE_NAME.toString())))
                .andExpect(jsonPath("$.[*].overallWidth").value(hasItem(DEFAULT_OVERALL_WIDTH.doubleValue())))
                .andExpect(jsonPath("$.[*].overallHeight").value(hasItem(DEFAULT_OVERALL_HEIGHT.doubleValue())))
                .andExpect(jsonPath("$.[*].bpRotation").value(hasItem(DEFAULT_BP_ROTATION)))
                .andExpect(jsonPath("$.[*].fpPanelImageWidth").value(hasItem(DEFAULT_FP_PANEL_IMAGE_WIDTH.doubleValue())))
                .andExpect(jsonPath("$.[*].fpImageHeight").value(hasItem(DEFAULT_FP_IMAGE_HEIGHT.doubleValue())))
                .andExpect(jsonPath("$.[*].hasBorder").value(hasItem(DEFAULT_HAS_BORDER.booleanValue())))
                .andExpect(jsonPath("$.[*].borderWidth").value(hasItem(DEFAULT_BORDER_WIDTH.doubleValue())))
                .andExpect(jsonPath("$.[*].borderTopInset").value(hasItem(DEFAULT_BORDER_TOP_INSET.doubleValue())))
                .andExpect(jsonPath("$.[*].borderLeftInset").value(hasItem(DEFAULT_BORDER_LEFT_INSET.doubleValue())))
                .andExpect(jsonPath("$.[*].borderRightInset").value(hasItem(DEFAULT_BORDER_RIGHT_INSET.doubleValue())))
                .andExpect(jsonPath("$.[*].bpMarginTop").value(hasItem(DEFAULT_BP_MARGIN_TOP.doubleValue())))
                .andExpect(jsonPath("$.[*].bpMarginLeft").value(hasItem(DEFAULT_BP_MARGIN_LEFT.doubleValue())))
                .andExpect(jsonPath("$.[*].bpMarginRight").value(hasItem(DEFAULT_BP_MARGIN_RIGHT.doubleValue())))
                .andExpect(jsonPath("$.[*].supFactAps").value(hasItem(DEFAULT_SUP_FACT_APS.doubleValue())))
                .andExpect(jsonPath("$.[*].supFactDv").value(hasItem(DEFAULT_SUP_FACT_DV.doubleValue())))
                .andExpect(jsonPath("$.[*].nutFactDv").value(hasItem(DEFAULT_NUT_FACT_DV.doubleValue())))
                .andExpect(jsonPath("$.[*].mfgInfoFontSize").value(hasItem(DEFAULT_MFG_INFO_FONT_SIZE.doubleValue())))
                .andExpect(jsonPath("$.[*].useByFontSize").value(hasItem(DEFAULT_USE_BY_FONT_SIZE.doubleValue())))
                .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED.toString())))
                .andExpect(jsonPath("$.[*].fpTopBumpHeight").value(hasItem(DEFAULT_FP_TOP_BUMP_HEIGHT.doubleValue())))
                .andExpect(jsonPath("$.[*].supFactApsDataInset").value(hasItem(DEFAULT_SUP_FACT_APS_DATA_INSET.doubleValue())))
                .andExpect(jsonPath("$.[*].mfgInfoDisplayFormat").value(hasItem(DEFAULT_MFG_INFO_DISPLAY_FORMAT.toString())))
                .andExpect(jsonPath("$.[*].textAlignment").value(hasItem(DEFAULT_TEXT_ALIGNMENT.toString())))
                .andExpect(jsonPath("$.[*].upcFontSize").value(hasItem(DEFAULT_UPC_FONT_SIZE)))
                .andExpect(jsonPath("$.[*].hasMiniFactsBox").value(hasItem(DEFAULT_HAS_MINI_FACTS_BOX.booleanValue())))
                .andExpect(jsonPath("$.[*].nutFactsAps").value(hasItem(DEFAULT_NUT_FACTS_APS.doubleValue())))
                .andExpect(jsonPath("$.[*].peelOffPanelRotation").value(hasItem(DEFAULT_PEEL_OFF_PANEL_ROTATION)))
                .andExpect(jsonPath("$.[*].peelOffMarginTop").value(hasItem(DEFAULT_PEEL_OFF_MARGIN_TOP.doubleValue())))
                .andExpect(jsonPath("$.[*].peelOffMarginLeft").value(hasItem(DEFAULT_PEEL_OFF_MARGIN_LEFT.doubleValue())))
                .andExpect(jsonPath("$.[*].peelOffMarginRight").value(hasItem(DEFAULT_PEEL_OFF_MARGIN_RIGHT.doubleValue())))
                .andExpect(jsonPath("$.[*].peelOffMarginBottom").value(hasItem(DEFAULT_PEEL_OFF_MARGIN_BOTTOM.doubleValue())))
                .andExpect(jsonPath("$.[*].allowPeelOff").value(hasItem(DEFAULT_ALLOW_PEEL_OFF.booleanValue())))
                .andExpect(jsonPath("$.[*].bpDividerMargin").value(hasItem(DEFAULT_BP_DIVIDER_MARGIN.doubleValue())))
                .andExpect(jsonPath("$.[*].columnDividerMargin").value(hasItem(DEFAULT_COLUMN_DIVIDER_MARGIN.doubleValue())))
                .andExpect(jsonPath("$.[*].subNutritionalSeparators").value(hasItem(DEFAULT_SUB_NUTRITIONAL_SEPARATORS.booleanValue())))
                .andExpect(jsonPath("$.[*].calorieComparisonLowWidth").value(hasItem(DEFAULT_CALORIE_COMPARISON_LOW_WIDTH.doubleValue())))
                .andExpect(jsonPath("$.[*].calorieComparisonHighWidth").value(hasItem(DEFAULT_CALORIE_COMPARISON_HIGH_WIDTH.doubleValue())))
                .andExpect(jsonPath("$.[*].calorieComparisonLtWidth").value(hasItem(DEFAULT_CALORIE_COMPARISON_LT_WIDTH.doubleValue())))
                .andExpect(jsonPath("$.[*].bpMarginBottom").value(hasItem(DEFAULT_BP_MARGIN_BOTTOM.doubleValue())))
                .andExpect(jsonPath("$.[*].peelOffGlueAllowance").value(hasItem(DEFAULT_PEEL_OFF_GLUE_ALLOWANCE.doubleValue())))
                .andExpect(jsonPath("$.[*].dataOnly").value(hasItem(DEFAULT_DATA_ONLY.booleanValue())));
    }

    @Test
    @Transactional
    public void getDie() throws Exception {
        // Initialize the database
        dieRepository.saveAndFlush(die);

        // Get the die
        restDieMockMvc.perform(get("/api/dies/{id}", die.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(die.getId().intValue()))
            .andExpect(jsonPath("$.brandCode").value(DEFAULT_BRAND_CODE.toString()))
            .andExpect(jsonPath("$.dieName").value(DEFAULT_DIE_NAME.toString()))
            .andExpect(jsonPath("$.overallWidth").value(DEFAULT_OVERALL_WIDTH.doubleValue()))
            .andExpect(jsonPath("$.overallHeight").value(DEFAULT_OVERALL_HEIGHT.doubleValue()))
            .andExpect(jsonPath("$.bpRotation").value(DEFAULT_BP_ROTATION))
            .andExpect(jsonPath("$.fpPanelImageWidth").value(DEFAULT_FP_PANEL_IMAGE_WIDTH.doubleValue()))
            .andExpect(jsonPath("$.fpImageHeight").value(DEFAULT_FP_IMAGE_HEIGHT.doubleValue()))
            .andExpect(jsonPath("$.hasBorder").value(DEFAULT_HAS_BORDER.booleanValue()))
            .andExpect(jsonPath("$.borderWidth").value(DEFAULT_BORDER_WIDTH.doubleValue()))
            .andExpect(jsonPath("$.borderTopInset").value(DEFAULT_BORDER_TOP_INSET.doubleValue()))
            .andExpect(jsonPath("$.borderLeftInset").value(DEFAULT_BORDER_LEFT_INSET.doubleValue()))
            .andExpect(jsonPath("$.borderRightInset").value(DEFAULT_BORDER_RIGHT_INSET.doubleValue()))
            .andExpect(jsonPath("$.bpMarginTop").value(DEFAULT_BP_MARGIN_TOP.doubleValue()))
            .andExpect(jsonPath("$.bpMarginLeft").value(DEFAULT_BP_MARGIN_LEFT.doubleValue()))
            .andExpect(jsonPath("$.bpMarginRight").value(DEFAULT_BP_MARGIN_RIGHT.doubleValue()))
            .andExpect(jsonPath("$.supFactAps").value(DEFAULT_SUP_FACT_APS.doubleValue()))
            .andExpect(jsonPath("$.supFactDv").value(DEFAULT_SUP_FACT_DV.doubleValue()))
            .andExpect(jsonPath("$.nutFactDv").value(DEFAULT_NUT_FACT_DV.doubleValue()))
            .andExpect(jsonPath("$.mfgInfoFontSize").value(DEFAULT_MFG_INFO_FONT_SIZE.doubleValue()))
            .andExpect(jsonPath("$.useByFontSize").value(DEFAULT_USE_BY_FONT_SIZE.doubleValue()))
            .andExpect(jsonPath("$.lastModified").value(DEFAULT_LAST_MODIFIED.toString()))
            .andExpect(jsonPath("$.fpTopBumpHeight").value(DEFAULT_FP_TOP_BUMP_HEIGHT.doubleValue()))
            .andExpect(jsonPath("$.supFactApsDataInset").value(DEFAULT_SUP_FACT_APS_DATA_INSET.doubleValue()))
            .andExpect(jsonPath("$.mfgInfoDisplayFormat").value(DEFAULT_MFG_INFO_DISPLAY_FORMAT.toString()))
            .andExpect(jsonPath("$.textAlignment").value(DEFAULT_TEXT_ALIGNMENT.toString()))
            .andExpect(jsonPath("$.upcFontSize").value(DEFAULT_UPC_FONT_SIZE))
            .andExpect(jsonPath("$.hasMiniFactsBox").value(DEFAULT_HAS_MINI_FACTS_BOX.booleanValue()))
            .andExpect(jsonPath("$.nutFactsAps").value(DEFAULT_NUT_FACTS_APS.doubleValue()))
            .andExpect(jsonPath("$.peelOffPanelRotation").value(DEFAULT_PEEL_OFF_PANEL_ROTATION))
            .andExpect(jsonPath("$.peelOffMarginTop").value(DEFAULT_PEEL_OFF_MARGIN_TOP.doubleValue()))
            .andExpect(jsonPath("$.peelOffMarginLeft").value(DEFAULT_PEEL_OFF_MARGIN_LEFT.doubleValue()))
            .andExpect(jsonPath("$.peelOffMarginRight").value(DEFAULT_PEEL_OFF_MARGIN_RIGHT.doubleValue()))
            .andExpect(jsonPath("$.peelOffMarginBottom").value(DEFAULT_PEEL_OFF_MARGIN_BOTTOM.doubleValue()))
            .andExpect(jsonPath("$.allowPeelOff").value(DEFAULT_ALLOW_PEEL_OFF.booleanValue()))
            .andExpect(jsonPath("$.bpDividerMargin").value(DEFAULT_BP_DIVIDER_MARGIN.doubleValue()))
            .andExpect(jsonPath("$.columnDividerMargin").value(DEFAULT_COLUMN_DIVIDER_MARGIN.doubleValue()))
            .andExpect(jsonPath("$.subNutritionalSeparators").value(DEFAULT_SUB_NUTRITIONAL_SEPARATORS.booleanValue()))
            .andExpect(jsonPath("$.calorieComparisonLowWidth").value(DEFAULT_CALORIE_COMPARISON_LOW_WIDTH.doubleValue()))
            .andExpect(jsonPath("$.calorieComparisonHighWidth").value(DEFAULT_CALORIE_COMPARISON_HIGH_WIDTH.doubleValue()))
            .andExpect(jsonPath("$.calorieComparisonLtWidth").value(DEFAULT_CALORIE_COMPARISON_LT_WIDTH.doubleValue()))
            .andExpect(jsonPath("$.bpMarginBottom").value(DEFAULT_BP_MARGIN_BOTTOM.doubleValue()))
            .andExpect(jsonPath("$.peelOffGlueAllowance").value(DEFAULT_PEEL_OFF_GLUE_ALLOWANCE.doubleValue()))
            .andExpect(jsonPath("$.dataOnly").value(DEFAULT_DATA_ONLY.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingDie() throws Exception {
        // Get the die
        restDieMockMvc.perform(get("/api/dies/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDie() throws Exception {
        // Initialize the database
        dieRepository.saveAndFlush(die);

		int databaseSizeBeforeUpdate = dieRepository.findAll().size();

        // Update the die
        die.setBrandCode(UPDATED_BRAND_CODE);
        die.setDieName(UPDATED_DIE_NAME);
        die.setOverallWidth(UPDATED_OVERALL_WIDTH);
        die.setOverallHeight(UPDATED_OVERALL_HEIGHT);
        die.setBpRotation(UPDATED_BP_ROTATION);
        die.setFpPanelImageWidth(UPDATED_FP_PANEL_IMAGE_WIDTH);
        die.setFpImageHeight(UPDATED_FP_IMAGE_HEIGHT);
        die.setHasBorder(UPDATED_HAS_BORDER);
        die.setBorderWidth(UPDATED_BORDER_WIDTH);
        die.setBorderTopInset(UPDATED_BORDER_TOP_INSET);
        die.setBorderLeftInset(UPDATED_BORDER_LEFT_INSET);
        die.setBorderRightInset(UPDATED_BORDER_RIGHT_INSET);
        die.setBpMarginTop(UPDATED_BP_MARGIN_TOP);
        die.setBpMarginLeft(UPDATED_BP_MARGIN_LEFT);
        die.setBpMarginRight(UPDATED_BP_MARGIN_RIGHT);
        die.setSupFactAps(UPDATED_SUP_FACT_APS);
        die.setSupFactDv(UPDATED_SUP_FACT_DV);
        die.setNutFactDv(UPDATED_NUT_FACT_DV);
        die.setMfgInfoFontSize(UPDATED_MFG_INFO_FONT_SIZE);
        die.setUseByFontSize(UPDATED_USE_BY_FONT_SIZE);
        die.setLastModified(UPDATED_LAST_MODIFIED);
        die.setFpTopBumpHeight(UPDATED_FP_TOP_BUMP_HEIGHT);
        die.setSupFactApsDataInset(UPDATED_SUP_FACT_APS_DATA_INSET);
        die.setMfgInfoDisplayFormat(UPDATED_MFG_INFO_DISPLAY_FORMAT);
        die.setTextAlignment(UPDATED_TEXT_ALIGNMENT);
        die.setUpcFontSize(UPDATED_UPC_FONT_SIZE);
        die.setHasMiniFactsBox(UPDATED_HAS_MINI_FACTS_BOX);
        die.setNutFactsAps(UPDATED_NUT_FACTS_APS);
        die.setPeelOffPanelRotation(UPDATED_PEEL_OFF_PANEL_ROTATION);
        die.setPeelOffMarginTop(UPDATED_PEEL_OFF_MARGIN_TOP);
        die.setPeelOffMarginLeft(UPDATED_PEEL_OFF_MARGIN_LEFT);
        die.setPeelOffMarginRight(UPDATED_PEEL_OFF_MARGIN_RIGHT);
        die.setPeelOffMarginBottom(UPDATED_PEEL_OFF_MARGIN_BOTTOM);
        die.setAllowPeelOff(UPDATED_ALLOW_PEEL_OFF);
        die.setBpDividerMargin(UPDATED_BP_DIVIDER_MARGIN);
        die.setColumnDividerMargin(UPDATED_COLUMN_DIVIDER_MARGIN);
        die.setSubNutritionalSeparators(UPDATED_SUB_NUTRITIONAL_SEPARATORS);
        die.setCalorieComparisonLowWidth(UPDATED_CALORIE_COMPARISON_LOW_WIDTH);
        die.setCalorieComparisonHighWidth(UPDATED_CALORIE_COMPARISON_HIGH_WIDTH);
        die.setCalorieComparisonLtWidth(UPDATED_CALORIE_COMPARISON_LT_WIDTH);
        die.setBpMarginBottom(UPDATED_BP_MARGIN_BOTTOM);
        die.setPeelOffGlueAllowance(UPDATED_PEEL_OFF_GLUE_ALLOWANCE);
        die.setDataOnly(UPDATED_DATA_ONLY);

        restDieMockMvc.perform(put("/api/dies")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(die)))
                .andExpect(status().isOk());

        // Validate the Die in the database
        List<Die> dies = dieRepository.findAll();
        assertThat(dies).hasSize(databaseSizeBeforeUpdate);
        Die testDie = dies.get(dies.size() - 1);
        assertThat(testDie.getBrandCode()).isEqualTo(UPDATED_BRAND_CODE);
        assertThat(testDie.getDieName()).isEqualTo(UPDATED_DIE_NAME);
        assertThat(testDie.getOverallWidth()).isEqualTo(UPDATED_OVERALL_WIDTH);
        assertThat(testDie.getOverallHeight()).isEqualTo(UPDATED_OVERALL_HEIGHT);
        assertThat(testDie.getBpRotation()).isEqualTo(UPDATED_BP_ROTATION);
        assertThat(testDie.getFpPanelImageWidth()).isEqualTo(UPDATED_FP_PANEL_IMAGE_WIDTH);
        assertThat(testDie.getFpImageHeight()).isEqualTo(UPDATED_FP_IMAGE_HEIGHT);
        assertThat(testDie.getHasBorder()).isEqualTo(UPDATED_HAS_BORDER);
        assertThat(testDie.getBorderWidth()).isEqualTo(UPDATED_BORDER_WIDTH);
        assertThat(testDie.getBorderTopInset()).isEqualTo(UPDATED_BORDER_TOP_INSET);
        assertThat(testDie.getBorderLeftInset()).isEqualTo(UPDATED_BORDER_LEFT_INSET);
        assertThat(testDie.getBorderRightInset()).isEqualTo(UPDATED_BORDER_RIGHT_INSET);
        assertThat(testDie.getBpMarginTop()).isEqualTo(UPDATED_BP_MARGIN_TOP);
        assertThat(testDie.getBpMarginLeft()).isEqualTo(UPDATED_BP_MARGIN_LEFT);
        assertThat(testDie.getBpMarginRight()).isEqualTo(UPDATED_BP_MARGIN_RIGHT);
        assertThat(testDie.getSupFactAps()).isEqualTo(UPDATED_SUP_FACT_APS);
        assertThat(testDie.getSupFactDv()).isEqualTo(UPDATED_SUP_FACT_DV);
        assertThat(testDie.getNutFactDv()).isEqualTo(UPDATED_NUT_FACT_DV);
        assertThat(testDie.getMfgInfoFontSize()).isEqualTo(UPDATED_MFG_INFO_FONT_SIZE);
        assertThat(testDie.getUseByFontSize()).isEqualTo(UPDATED_USE_BY_FONT_SIZE);
        assertThat(testDie.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testDie.getFpTopBumpHeight()).isEqualTo(UPDATED_FP_TOP_BUMP_HEIGHT);
        assertThat(testDie.getSupFactApsDataInset()).isEqualTo(UPDATED_SUP_FACT_APS_DATA_INSET);
        assertThat(testDie.getMfgInfoDisplayFormat()).isEqualTo(UPDATED_MFG_INFO_DISPLAY_FORMAT);
        assertThat(testDie.getTextAlignment()).isEqualTo(UPDATED_TEXT_ALIGNMENT);
        assertThat(testDie.getUpcFontSize()).isEqualTo(UPDATED_UPC_FONT_SIZE);
        assertThat(testDie.getHasMiniFactsBox()).isEqualTo(UPDATED_HAS_MINI_FACTS_BOX);
        assertThat(testDie.getNutFactsAps()).isEqualTo(UPDATED_NUT_FACTS_APS);
        assertThat(testDie.getPeelOffPanelRotation()).isEqualTo(UPDATED_PEEL_OFF_PANEL_ROTATION);
        assertThat(testDie.getPeelOffMarginTop()).isEqualTo(UPDATED_PEEL_OFF_MARGIN_TOP);
        assertThat(testDie.getPeelOffMarginLeft()).isEqualTo(UPDATED_PEEL_OFF_MARGIN_LEFT);
        assertThat(testDie.getPeelOffMarginRight()).isEqualTo(UPDATED_PEEL_OFF_MARGIN_RIGHT);
        assertThat(testDie.getPeelOffMarginBottom()).isEqualTo(UPDATED_PEEL_OFF_MARGIN_BOTTOM);
        assertThat(testDie.getAllowPeelOff()).isEqualTo(UPDATED_ALLOW_PEEL_OFF);
        assertThat(testDie.getBpDividerMargin()).isEqualTo(UPDATED_BP_DIVIDER_MARGIN);
        assertThat(testDie.getColumnDividerMargin()).isEqualTo(UPDATED_COLUMN_DIVIDER_MARGIN);
        assertThat(testDie.getSubNutritionalSeparators()).isEqualTo(UPDATED_SUB_NUTRITIONAL_SEPARATORS);
        assertThat(testDie.getCalorieComparisonLowWidth()).isEqualTo(UPDATED_CALORIE_COMPARISON_LOW_WIDTH);
        assertThat(testDie.getCalorieComparisonHighWidth()).isEqualTo(UPDATED_CALORIE_COMPARISON_HIGH_WIDTH);
        assertThat(testDie.getCalorieComparisonLtWidth()).isEqualTo(UPDATED_CALORIE_COMPARISON_LT_WIDTH);
        assertThat(testDie.getBpMarginBottom()).isEqualTo(UPDATED_BP_MARGIN_BOTTOM);
        assertThat(testDie.getPeelOffGlueAllowance()).isEqualTo(UPDATED_PEEL_OFF_GLUE_ALLOWANCE);
        assertThat(testDie.getDataOnly()).isEqualTo(UPDATED_DATA_ONLY);
    }

    @Test
    @Transactional
    public void deleteDie() throws Exception {
        // Initialize the database
        dieRepository.saveAndFlush(die);

		int databaseSizeBeforeDelete = dieRepository.findAll().size();

        // Get the die
        restDieMockMvc.perform(delete("/api/dies/{id}", die.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Die> dies = dieRepository.findAll();
        assertThat(dies).hasSize(databaseSizeBeforeDelete - 1);
    }
}
