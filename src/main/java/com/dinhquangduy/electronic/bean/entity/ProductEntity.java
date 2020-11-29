package com.dinhquangduy.electronic.bean.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class ProductEntity extends CommonEntity implements Serializable{
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    /** The name. */
    @Column(name = "name")
    private String name;
    
    /** The alias. */
    @Column(name = "alias")
    private String alias;
    
    /** The category id. */
    @Column(name = "category_id")
    private Integer categoryId;
    
    /** The image. */
    @Column(name = "images")
    private String images;
    
    /** The price. */
    @Column(name = "price")
    private BigDecimal price = new BigDecimal(0);
    
    
    /** The warranty. */
    @Column(name = "warranty")
    private Integer warranty;
    
    /** The description. */
    @Column(name = "description")
    private String description;
    
    /** The content. */
    @Column(name = "content")
    private String content;
    
    /** The hot flg. */
    @Column(name = "hoFlg")
    private Boolean hotFlg;
    
    /** The home flg. */
    @Column(name = "homeFlg")
    private Boolean homeFlg;
    
    /** The quantity. */
    @Column(name = "quantity")
    private Integer quantity ;
    
    /** The status. */
    @Column(name = "status")
    private Boolean status;
    
    /** The view count. */
    @Column(name = "view_count")
    private Integer viewCount = 0 ;
    
    /** The rating. */
    @Column(name = "rating")
    private Float rating ;

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
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the alias.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the alias.
     *
     * @param alias the new alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Gets the category id.
     *
     * @return the category id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the category id.
     *
     * @param categoryId the new category id
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Gets the image.
     *
     * @return the image
     */
    public String getImages() {
        return images;
    }

    /**
     * Sets the image.
     *
     * @param image the new image
     */
    public void setImages(String images) {
        this.images = images;
    }

    /**
     * Gets the price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price.
     *
     * @param price the new price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the warranty.
     *
     * @return the warranty
     */
    public Integer getWarranty() {
        return warranty;
    }

    /**
     * Sets the warranty.
     *
     * @param warranty the new warranty
     */
    public void setWarranty(Integer warranty) {
        this.warranty = warranty;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content.
     *
     * @param content the new content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the hot flg.
     *
     * @return the hot flg
     */
    public Boolean getHotFlg() {
        return hotFlg;
    }

    /**
     * Sets the hot flg.
     *
     * @param hotFlg the new hot flg
     */
    public void setHotFlg(Boolean hotFlg) {
        this.hotFlg = hotFlg;
    }

    /**
     * Gets the home flg.
     *
     * @return the home flg
     */
    public Boolean getHomeFlg() {
        return homeFlg;
    }

    /**
     * Sets the home flg.
     *
     * @param homeFlg the new home flg
     */
    public void setHomeFlg(Boolean homeFlg) {
        this.homeFlg = homeFlg;
    }

    /**
     * Gets the quantity.
     *
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity.
     *
     * @param quantity the new quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
     * Gets the view count.
     *
     * @return the view count
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * Sets the view count.
     *
     * @param viewCount the new view count
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * Gets the rating.
     *
     * @return the rating
     */
    public Float getRating() {
        return rating;
    }

    /**
     * Sets the rating.
     *
     * @param rating the new rating
     */
    public void setRating(Float rating) {
        this.rating = rating;
    }
}
