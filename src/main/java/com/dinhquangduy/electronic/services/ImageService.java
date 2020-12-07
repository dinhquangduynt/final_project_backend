package com.dinhquangduy.electronic.services;

import com.dinhquangduy.electronic.bean.ResultBean;

public interface ImageService {
    
    public ResultBean getListImageByTypeAndParentId(String type, Integer parentId) throws Exception;

}
