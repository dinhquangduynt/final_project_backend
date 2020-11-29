package com.dinhquangduy.electronic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dinhquangduy.electronic.bean.ResultBean;
import com.dinhquangduy.electronic.config.LogExecutionTime;
import com.dinhquangduy.electronic.services.ProductService;
import com.dinhquangduy.electronic.utils.Constants;

/**
 * The Class ProductController.
 */
@RestController
@LogExecutionTime
@RequestMapping(value = "/api/product")
public class ProductController {

    /** The product service. */
    @Autowired
    private ProductService productService;

    /**
     * Gets the all products.
     *
     * @return the all products
     * @throws Exception the exception
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getAllProducts() throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = productService.getAll();
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * Gets the product by id.
     *
     * @param id the id
     * @return the product by id
     * @throws Exception the exception
     */
    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getProductById(@PathVariable Integer id) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = productService.getById(id);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * Gets the products by cate id.
     *
     * @param cateId the cate id
     * @return the products by cate id
     * @throws Exception the exception
     */
    @RequestMapping(value = "/getByCateId/{cateId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getProductsByCateId(@PathVariable Integer cateId) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = productService.getProductsByCateId(cateId);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * Delete id.
     *
     * @param id the id
     * @return the response entity
     * @throws Exception the exception
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> deleteId(@PathVariable Integer id) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = productService.deleteById(id);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * Adds the product.
     *
     * @param json the json
     * @return the response entity
     * @throws Exception the exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> addProduct(@RequestParam("files") MultipartFile[] files,@RequestParam("json") String json) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = productService.addProduct(json, files);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * Update.
     *
     * @param json the json
     * @return the response entity
     * @throws Exception the exception
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> update(@RequestParam("files") MultipartFile[] files, @RequestParam("json") String json) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = productService.updateProduct(json, files);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
}
