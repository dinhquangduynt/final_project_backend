package com.dinhquangduy.electronic.bean.response;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommonResponse  implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @JsonProperty("create_date")
    private Date createDate;
    
    @JsonProperty("create_by")
    private Integer createBy;
    
    @JsonProperty("update_date")
    private Date updateDate;
    
    @JsonProperty("update_by")
    private Integer updateBy;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
}
