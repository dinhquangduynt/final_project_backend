package com.dinhquangduy.electronic.bean.response;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductResponse extends CommonResponse implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Integer id;
    
    /** The name. */
    @JsonProperty("name")
    private String name;
    
    /** The alias. */
    @JsonProperty("alias")
    private String alias;
    
    /** The category id. */
    @JsonProperty("category_id")
    private Integer categoryId;
    
    /** The image. */
    @JsonProperty("images")
    private String images;
    
    /** The price. */
    @JsonProperty("price")
    private BigDecimal price = new BigDecimal(0);
    
    
    /** The warranty. */
    @JsonProperty("warranty")
    private Integer warranty;
    
    /** The description. */
    @JsonProperty("description")
    private String description;
    
    /** The content. */
    @JsonProperty("content")
    private String content;
    
    /** The hot flg. */
    @JsonProperty("hotFlg")
    private Boolean hotFlg;
    
    /** The home flg. */
    @JsonProperty("homeFlg")
    private Boolean homeFlg;
    
    /** The quantity. */
    @JsonProperty("quantity")
    private Integer quantity ;
    
    /** The status. */
    @JsonProperty("status")
    private Boolean status;
    
    /** The view count. */
    @JsonProperty("view_count")
    private Integer viewCount ;
    
    /** The rating. */
    @JsonProperty("rating")
    private Float rating ;

    public ProductResponse() {
    }
    
    

    public ProductResponse(Integer id, String name, String alias, Integer categoryId, String image, BigDecimal price, Integer warranty, String description,
            String content, Boolean hotFlg, Boolean homeFlg, Integer quantity, Boolean status, Integer viewCount, Float rating) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.categoryId = categoryId;
        this.images = image;
        this.price = price;
        this.warranty = warranty;
        this.description = description;
        this.content = content;
        this.hotFlg = hotFlg;
        this.homeFlg = homeFlg;
        this.quantity = quantity;
        this.status = status;
        this.viewCount = viewCount;
        this.rating = rating;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getWarranty() {
        return warranty;
    }

    public void setWarranty(Integer warranty) {
        this.warranty = warranty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getHotFlg() {
        return hotFlg;
    }

    public void setHotFlg(Boolean hotFlg) {
        this.hotFlg = hotFlg;
    }

    public Boolean getHomeFlg() {
        return homeFlg;
    }

    public void setHomeFlg(Boolean homeFlg) {
        this.homeFlg = homeFlg;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
    
    
}
