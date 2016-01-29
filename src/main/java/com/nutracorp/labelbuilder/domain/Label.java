package com.nutracorp.labelbuilder.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Label.
 */
@Entity
@Table(name = "label")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Label implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "version_major")
    private String versionMajor;

    @Column(name = "version_minor")
    private String versionMinor;

    @Column(name = "created")
    private LocalDate created;

    @Column(name = "discussion")
    private String discussion;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "content_count")
    private String contentCount;

    @Column(name = "serving_size")
    private String servingSize;

    @Column(name = "delivery_form")
    private String deliveryForm;

    @Column(name = "dosage_consistency")
    private String dosageConsistency;

    @Column(name = "generic_description")
    private String genericDescription;

    @Column(name = "upc")
    private String upc;

    @Column(name = "warning")
    private String warning;

    @Column(name = "directions")
    private String directions;

    @Column(name = "refrigerated")
    private String refrigerated;

    @Column(name = "is_peel_off")
    private Boolean isPeelOff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getVersionMajor() {
        return versionMajor;
    }

    public void setVersionMajor(String versionMajor) {
        this.versionMajor = versionMajor;
    }

    public String getVersionMinor() {
        return versionMinor;
    }

    public void setVersionMinor(String versionMinor) {
        this.versionMinor = versionMinor;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public String getDiscussion() {
        return discussion;
    }

    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getContentCount() {
        return contentCount;
    }

    public void setContentCount(String contentCount) {
        this.contentCount = contentCount;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public String getDeliveryForm() {
        return deliveryForm;
    }

    public void setDeliveryForm(String deliveryForm) {
        this.deliveryForm = deliveryForm;
    }

    public String getDosageConsistency() {
        return dosageConsistency;
    }

    public void setDosageConsistency(String dosageConsistency) {
        this.dosageConsistency = dosageConsistency;
    }

    public String getGenericDescription() {
        return genericDescription;
    }

    public void setGenericDescription(String genericDescription) {
        this.genericDescription = genericDescription;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getRefrigerated() {
        return refrigerated;
    }

    public void setRefrigerated(String refrigerated) {
        this.refrigerated = refrigerated;
    }

    public Boolean getIsPeelOff() {
        return isPeelOff;
    }

    public void setIsPeelOff(Boolean isPeelOff) {
        this.isPeelOff = isPeelOff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Label label = (Label) o;
        if(label.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, label.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Label{" +
            "id=" + id +
            ", productId='" + productId + "'" +
            ", versionMajor='" + versionMajor + "'" +
            ", versionMinor='" + versionMinor + "'" +
            ", created='" + created + "'" +
            ", discussion='" + discussion + "'" +
            ", productName='" + productName + "'" +
            ", contentCount='" + contentCount + "'" +
            ", servingSize='" + servingSize + "'" +
            ", deliveryForm='" + deliveryForm + "'" +
            ", dosageConsistency='" + dosageConsistency + "'" +
            ", genericDescription='" + genericDescription + "'" +
            ", upc='" + upc + "'" +
            ", warning='" + warning + "'" +
            ", directions='" + directions + "'" +
            ", refrigerated='" + refrigerated + "'" +
            ", isPeelOff='" + isPeelOff + "'" +
            '}';
    }
}
