package com.nutracorp.labelbuilder.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Die.
 */
@Entity
@Table(name = "die")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Die implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "brand_code")
    private String brandCode;
    
    @Column(name = "die_name")
    private String dieName;
    
    @Column(name = "overall_width")
    private Float overallWidth;
    
    @Column(name = "overall_height")
    private Float overallHeight;
    
    @Column(name = "bp_rotation")
    private Integer bpRotation;
    
    @Column(name = "fp_panel_image_width")
    private Float fpPanelImageWidth;
    
    @Column(name = "fp_image_height")
    private Float fpImageHeight;
    
    @Column(name = "has_border")
    private Boolean hasBorder;
    
    @Column(name = "border_width")
    private Float borderWidth;
    
    @Column(name = "border_top_inset")
    private Float borderTopInset;
    
    @Column(name = "border_left_inset")
    private Float borderLeftInset;
    
    @Column(name = "border_right_inset")
    private Float borderRightInset;
    
    @Column(name = "bp_margin_top")
    private Float bpMarginTop;
    
    @Column(name = "bp_margin_left")
    private Float bpMarginLeft;
    
    @Column(name = "bp_margin_right")
    private Float bpMarginRight;
    
    @Column(name = "sup_fact_aps")
    private Float supFactAps;
    
    @Column(name = "sup_fact_dv")
    private Float supFactDv;
    
    @Column(name = "nut_fact_dv")
    private Float nutFactDv;
    
    @Column(name = "mfg_info_font_size")
    private Float mfgInfoFontSize;
    
    @Column(name = "use_by_font_size")
    private Float useByFontSize;
    
    @Column(name = "last_modified")
    private LocalDate lastModified;
    
    @Column(name = "fp_top_bump_height")
    private Float fpTopBumpHeight;
    
    @Column(name = "sup_fact_aps_data_inset")
    private Float supFactApsDataInset;
    
    @Column(name = "mfg_info_display_format")
    private String mfgInfoDisplayFormat;
    
    @Column(name = "text_alignment")
    private String textAlignment;
    
    @Column(name = "upc_font_size")
    private Integer upcFontSize;
    
    @Column(name = "has_mini_facts_box")
    private Boolean hasMiniFactsBox;
    
    @Column(name = "nut_facts_aps")
    private Float nutFactsAps;
    
    @Column(name = "peel_off_panel_rotation")
    private Integer peelOffPanelRotation;
    
    @Column(name = "peel_off_margin_top")
    private Float peelOffMarginTop;
    
    @Column(name = "peel_off_margin_left")
    private Float peelOffMarginLeft;
    
    @Column(name = "peel_off_margin_right")
    private Float peelOffMarginRight;
    
    @Column(name = "peel_off_margin_bottom")
    private Float peelOffMarginBottom;
    
    @Column(name = "allow_peel_off")
    private Boolean allowPeelOff;
    
    @Column(name = "bp_divider_margin")
    private Float bpDividerMargin;
    
    @Column(name = "column_divider_margin")
    private Float columnDividerMargin;
    
    @Column(name = "sub_nutritional_separators")
    private Boolean subNutritionalSeparators;
    
    @Column(name = "calorie_comparison_low_width")
    private Float calorieComparisonLowWidth;
    
    @Column(name = "calorie_comparison_high_width")
    private Float calorieComparisonHighWidth;
    
    @Column(name = "calorie_comparison_lt_width")
    private Float calorieComparisonLtWidth;
    
    @Column(name = "bp_margin_bottom")
    private Float bpMarginBottom;
    
    @Column(name = "peel_off_glue_allowance")
    private Float peelOffGlueAllowance;
    
    @Column(name = "data_only")
    private Boolean dataOnly;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandCode() {
        return brandCode;
    }
    
    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getDieName() {
        return dieName;
    }
    
    public void setDieName(String dieName) {
        this.dieName = dieName;
    }

    public Float getOverallWidth() {
        return overallWidth;
    }
    
    public void setOverallWidth(Float overallWidth) {
        this.overallWidth = overallWidth;
    }

    public Float getOverallHeight() {
        return overallHeight;
    }
    
    public void setOverallHeight(Float overallHeight) {
        this.overallHeight = overallHeight;
    }

    public Integer getBpRotation() {
        return bpRotation;
    }
    
    public void setBpRotation(Integer bpRotation) {
        this.bpRotation = bpRotation;
    }

    public Float getFpPanelImageWidth() {
        return fpPanelImageWidth;
    }
    
    public void setFpPanelImageWidth(Float fpPanelImageWidth) {
        this.fpPanelImageWidth = fpPanelImageWidth;
    }

    public Float getFpImageHeight() {
        return fpImageHeight;
    }
    
    public void setFpImageHeight(Float fpImageHeight) {
        this.fpImageHeight = fpImageHeight;
    }

    public Boolean getHasBorder() {
        return hasBorder;
    }
    
    public void setHasBorder(Boolean hasBorder) {
        this.hasBorder = hasBorder;
    }

    public Float getBorderWidth() {
        return borderWidth;
    }
    
    public void setBorderWidth(Float borderWidth) {
        this.borderWidth = borderWidth;
    }

    public Float getBorderTopInset() {
        return borderTopInset;
    }
    
    public void setBorderTopInset(Float borderTopInset) {
        this.borderTopInset = borderTopInset;
    }

    public Float getBorderLeftInset() {
        return borderLeftInset;
    }
    
    public void setBorderLeftInset(Float borderLeftInset) {
        this.borderLeftInset = borderLeftInset;
    }

    public Float getBorderRightInset() {
        return borderRightInset;
    }
    
    public void setBorderRightInset(Float borderRightInset) {
        this.borderRightInset = borderRightInset;
    }

    public Float getBpMarginTop() {
        return bpMarginTop;
    }
    
    public void setBpMarginTop(Float bpMarginTop) {
        this.bpMarginTop = bpMarginTop;
    }

    public Float getBpMarginLeft() {
        return bpMarginLeft;
    }
    
    public void setBpMarginLeft(Float bpMarginLeft) {
        this.bpMarginLeft = bpMarginLeft;
    }

    public Float getBpMarginRight() {
        return bpMarginRight;
    }
    
    public void setBpMarginRight(Float bpMarginRight) {
        this.bpMarginRight = bpMarginRight;
    }

    public Float getSupFactAps() {
        return supFactAps;
    }
    
    public void setSupFactAps(Float supFactAps) {
        this.supFactAps = supFactAps;
    }

    public Float getSupFactDv() {
        return supFactDv;
    }
    
    public void setSupFactDv(Float supFactDv) {
        this.supFactDv = supFactDv;
    }

    public Float getNutFactDv() {
        return nutFactDv;
    }
    
    public void setNutFactDv(Float nutFactDv) {
        this.nutFactDv = nutFactDv;
    }

    public Float getMfgInfoFontSize() {
        return mfgInfoFontSize;
    }
    
    public void setMfgInfoFontSize(Float mfgInfoFontSize) {
        this.mfgInfoFontSize = mfgInfoFontSize;
    }

    public Float getUseByFontSize() {
        return useByFontSize;
    }
    
    public void setUseByFontSize(Float useByFontSize) {
        this.useByFontSize = useByFontSize;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }
    
    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }

    public Float getFpTopBumpHeight() {
        return fpTopBumpHeight;
    }
    
    public void setFpTopBumpHeight(Float fpTopBumpHeight) {
        this.fpTopBumpHeight = fpTopBumpHeight;
    }

    public Float getSupFactApsDataInset() {
        return supFactApsDataInset;
    }
    
    public void setSupFactApsDataInset(Float supFactApsDataInset) {
        this.supFactApsDataInset = supFactApsDataInset;
    }

    public String getMfgInfoDisplayFormat() {
        return mfgInfoDisplayFormat;
    }
    
    public void setMfgInfoDisplayFormat(String mfgInfoDisplayFormat) {
        this.mfgInfoDisplayFormat = mfgInfoDisplayFormat;
    }

    public String getTextAlignment() {
        return textAlignment;
    }
    
    public void setTextAlignment(String textAlignment) {
        this.textAlignment = textAlignment;
    }

    public Integer getUpcFontSize() {
        return upcFontSize;
    }
    
    public void setUpcFontSize(Integer upcFontSize) {
        this.upcFontSize = upcFontSize;
    }

    public Boolean getHasMiniFactsBox() {
        return hasMiniFactsBox;
    }
    
    public void setHasMiniFactsBox(Boolean hasMiniFactsBox) {
        this.hasMiniFactsBox = hasMiniFactsBox;
    }

    public Float getNutFactsAps() {
        return nutFactsAps;
    }
    
    public void setNutFactsAps(Float nutFactsAps) {
        this.nutFactsAps = nutFactsAps;
    }

    public Integer getPeelOffPanelRotation() {
        return peelOffPanelRotation;
    }
    
    public void setPeelOffPanelRotation(Integer peelOffPanelRotation) {
        this.peelOffPanelRotation = peelOffPanelRotation;
    }

    public Float getPeelOffMarginTop() {
        return peelOffMarginTop;
    }
    
    public void setPeelOffMarginTop(Float peelOffMarginTop) {
        this.peelOffMarginTop = peelOffMarginTop;
    }

    public Float getPeelOffMarginLeft() {
        return peelOffMarginLeft;
    }
    
    public void setPeelOffMarginLeft(Float peelOffMarginLeft) {
        this.peelOffMarginLeft = peelOffMarginLeft;
    }

    public Float getPeelOffMarginRight() {
        return peelOffMarginRight;
    }
    
    public void setPeelOffMarginRight(Float peelOffMarginRight) {
        this.peelOffMarginRight = peelOffMarginRight;
    }

    public Float getPeelOffMarginBottom() {
        return peelOffMarginBottom;
    }
    
    public void setPeelOffMarginBottom(Float peelOffMarginBottom) {
        this.peelOffMarginBottom = peelOffMarginBottom;
    }

    public Boolean getAllowPeelOff() {
        return allowPeelOff;
    }
    
    public void setAllowPeelOff(Boolean allowPeelOff) {
        this.allowPeelOff = allowPeelOff;
    }

    public Float getBpDividerMargin() {
        return bpDividerMargin;
    }
    
    public void setBpDividerMargin(Float bpDividerMargin) {
        this.bpDividerMargin = bpDividerMargin;
    }

    public Float getColumnDividerMargin() {
        return columnDividerMargin;
    }
    
    public void setColumnDividerMargin(Float columnDividerMargin) {
        this.columnDividerMargin = columnDividerMargin;
    }

    public Boolean getSubNutritionalSeparators() {
        return subNutritionalSeparators;
    }
    
    public void setSubNutritionalSeparators(Boolean subNutritionalSeparators) {
        this.subNutritionalSeparators = subNutritionalSeparators;
    }

    public Float getCalorieComparisonLowWidth() {
        return calorieComparisonLowWidth;
    }
    
    public void setCalorieComparisonLowWidth(Float calorieComparisonLowWidth) {
        this.calorieComparisonLowWidth = calorieComparisonLowWidth;
    }

    public Float getCalorieComparisonHighWidth() {
        return calorieComparisonHighWidth;
    }
    
    public void setCalorieComparisonHighWidth(Float calorieComparisonHighWidth) {
        this.calorieComparisonHighWidth = calorieComparisonHighWidth;
    }

    public Float getCalorieComparisonLtWidth() {
        return calorieComparisonLtWidth;
    }
    
    public void setCalorieComparisonLtWidth(Float calorieComparisonLtWidth) {
        this.calorieComparisonLtWidth = calorieComparisonLtWidth;
    }

    public Float getBpMarginBottom() {
        return bpMarginBottom;
    }
    
    public void setBpMarginBottom(Float bpMarginBottom) {
        this.bpMarginBottom = bpMarginBottom;
    }

    public Float getPeelOffGlueAllowance() {
        return peelOffGlueAllowance;
    }
    
    public void setPeelOffGlueAllowance(Float peelOffGlueAllowance) {
        this.peelOffGlueAllowance = peelOffGlueAllowance;
    }

    public Boolean getDataOnly() {
        return dataOnly;
    }
    
    public void setDataOnly(Boolean dataOnly) {
        this.dataOnly = dataOnly;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Die die = (Die) o;
        if(die.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, die.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Die{" +
            "id=" + id +
            ", brandCode='" + brandCode + "'" +
            ", dieName='" + dieName + "'" +
            ", overallWidth='" + overallWidth + "'" +
            ", overallHeight='" + overallHeight + "'" +
            ", bpRotation='" + bpRotation + "'" +
            ", fpPanelImageWidth='" + fpPanelImageWidth + "'" +
            ", fpImageHeight='" + fpImageHeight + "'" +
            ", hasBorder='" + hasBorder + "'" +
            ", borderWidth='" + borderWidth + "'" +
            ", borderTopInset='" + borderTopInset + "'" +
            ", borderLeftInset='" + borderLeftInset + "'" +
            ", borderRightInset='" + borderRightInset + "'" +
            ", bpMarginTop='" + bpMarginTop + "'" +
            ", bpMarginLeft='" + bpMarginLeft + "'" +
            ", bpMarginRight='" + bpMarginRight + "'" +
            ", supFactAps='" + supFactAps + "'" +
            ", supFactDv='" + supFactDv + "'" +
            ", nutFactDv='" + nutFactDv + "'" +
            ", mfgInfoFontSize='" + mfgInfoFontSize + "'" +
            ", useByFontSize='" + useByFontSize + "'" +
            ", lastModified='" + lastModified + "'" +
            ", fpTopBumpHeight='" + fpTopBumpHeight + "'" +
            ", supFactApsDataInset='" + supFactApsDataInset + "'" +
            ", mfgInfoDisplayFormat='" + mfgInfoDisplayFormat + "'" +
            ", textAlignment='" + textAlignment + "'" +
            ", upcFontSize='" + upcFontSize + "'" +
            ", hasMiniFactsBox='" + hasMiniFactsBox + "'" +
            ", nutFactsAps='" + nutFactsAps + "'" +
            ", peelOffPanelRotation='" + peelOffPanelRotation + "'" +
            ", peelOffMarginTop='" + peelOffMarginTop + "'" +
            ", peelOffMarginLeft='" + peelOffMarginLeft + "'" +
            ", peelOffMarginRight='" + peelOffMarginRight + "'" +
            ", peelOffMarginBottom='" + peelOffMarginBottom + "'" +
            ", allowPeelOff='" + allowPeelOff + "'" +
            ", bpDividerMargin='" + bpDividerMargin + "'" +
            ", columnDividerMargin='" + columnDividerMargin + "'" +
            ", subNutritionalSeparators='" + subNutritionalSeparators + "'" +
            ", calorieComparisonLowWidth='" + calorieComparisonLowWidth + "'" +
            ", calorieComparisonHighWidth='" + calorieComparisonHighWidth + "'" +
            ", calorieComparisonLtWidth='" + calorieComparisonLtWidth + "'" +
            ", bpMarginBottom='" + bpMarginBottom + "'" +
            ", peelOffGlueAllowance='" + peelOffGlueAllowance + "'" +
            ", dataOnly='" + dataOnly + "'" +
            '}';
    }
}
