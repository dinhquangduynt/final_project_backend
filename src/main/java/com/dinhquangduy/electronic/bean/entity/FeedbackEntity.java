package com.dinhquangduy.electronic.bean.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class FeedbackEntity.
 */
@Entity
@Table(name = "feedbacks")
public class FeedbackEntity extends CommonEntity implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** The message. */
    @Column(name = "message")
    private String message;

    /** The email. */
    @Column(name = "email")
    private String email;

    /** The status. */
    @Column(name = "status")
    private Boolean status;

    /** The customer id. */
    @Column(name = "customer_id")
    private String customerId;

    /** The product id. */
    @Column(name = "product_id")
    private String productId;

    /** The rate. */
    @Column(name = "rate")
    private Float rate;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * Gets the customer id.
     *
     * @return the customer id
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer id.
     *
     * @param customerId the new customer id
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the product id.
     *
     * @return the product id
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Sets the product id.
     *
     * @param productId the new product id
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * Gets the rate.
     *
     * @return the rate
     */
    public Float getRate() {
        return rate;
    }

    /**
     * Sets the rate.
     *
     * @param rate the new rate
     */
    public void setRate(Float rate) {
        this.rate = rate;
    }
}
