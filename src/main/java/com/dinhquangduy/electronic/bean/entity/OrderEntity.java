package com.dinhquangduy.electronic.bean.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "orders")
public class OrderEntity extends CommonEntity implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Integer id;
    
    @Column(name = "customer_id")
    @JsonProperty("customer_id")
    private Integer customerId;
    
    @Column(name = "payment_method")
    @JsonProperty("payment_method")
    private String paymentMethod;

    
    @Column(name = "payment_status")
    @JsonProperty("payment_status")
    private String paymentStatus;
    
    @Column(name = "status")
    @JsonProperty("status")
    private boolean status;
    
    @Column(name = "customer_name")
    @JsonProperty("customer_name")
    private String customerName;
    
    @Column(name = "customer_address")
    @JsonProperty("customer_adress")
    private String customerAddress;
    
    @Column(name = "customer_email")
    @JsonProperty("customer_email")
    private String customerEmail;
    
    @Column(name = "customer_phone")
    @JsonProperty("customer_phone")
    private String customerPhone;
    
    @Column(name = "customer_message")
    @JsonProperty("customer_message")
    private String customerMassage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerMassage() {
        return customerMassage;
    }

    public void setCustomerMassage(String customerMassage) {
        this.customerMassage = customerMassage;
    }
}
