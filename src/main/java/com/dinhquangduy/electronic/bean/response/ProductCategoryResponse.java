package com.dinhquangduy.electronic.bean.response;

import java.io.Serializable;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductCategoryResponse extends CommonResponse implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @JsonProperty("id")
    private Integer id;

    /** The name. */
    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    /** The alias. */
    @Column(name = "alias")
    @JsonProperty("alias")
    private String alias;

    /** The image. */
    @Column(name = "image")
    @JsonProperty("image")
    private String image;

    /** The description. */
    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    /** The status. */
    @Column(name = "status")
    @JsonProperty("status")
    private Boolean status;

    public ProductCategoryResponse() {
    }

    public ProductCategoryResponse(Integer id, String name, String alias, String image, String description, Boolean status) {
        super();
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.image = image;
        this.description = description;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
