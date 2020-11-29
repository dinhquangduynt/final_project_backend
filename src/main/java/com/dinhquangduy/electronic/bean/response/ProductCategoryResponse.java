package com.dinhquangduy.electronic.bean.response;

import java.io.Serializable;

public class ProductCategoryResponse extends CommonResponse implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer id;

    /** The name. */
    private String name;

    /** The alias. */
    private String alias;

    /** The image. */
    private String images;

    /** The description. */
    private String description;

    /** The status. */
    private Boolean status;

    public ProductCategoryResponse() {
    }

    public ProductCategoryResponse(Integer id, String name, String alias, String image, String description, Boolean status) {
        super();
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.images = image;
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

    public String getImages() {
        return images;
    }

    public void setImage(String images) {
        this.images = images;
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
