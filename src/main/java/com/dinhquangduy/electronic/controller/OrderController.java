package com.dinhquangduy.electronic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dinhquangduy.electronic.bean.ResultBean;
import com.dinhquangduy.electronic.bean.entity.OrderDetailEntity;
import com.dinhquangduy.electronic.bean.entity.OrderEntity;
import com.dinhquangduy.electronic.config.LogExecutionTime;
import com.dinhquangduy.electronic.services.OrderDetailService;
import com.dinhquangduy.electronic.services.OrderService;
import com.dinhquangduy.electronic.utils.Constants;

/**
 * The Class OrderController.
 */
@Controller
@LogExecutionTime
@RequestMapping("/api/order")
public class OrderController {

    /** The order service. */
    @Autowired
    private OrderService orderService;

    /** The order detail service. */
    @Autowired
    private OrderDetailService orderDetailService;

    /**
     * Gets the all orders.
     *
     * @return the all orders
     * @throws Exception the exception
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getAllOrders() throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = orderService.getAll();
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
    @RequestMapping(value = "/getOrderDetailById/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getOrderDetailByOrderId(@PathVariable Integer id) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = orderDetailService.getByOrderId(id);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/getOrderById/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getOrderByOrderId(@PathVariable Integer id) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = orderService.getOrderById(id);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * Gets the products by cate id.
     *
     * @param userId the user id
     * @return the products by cate id
     * @throws Exception the exception
     */
    @RequestMapping(value = "/getByUserId/{userId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getOrdersByUserId(@PathVariable Integer userId) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = orderService.getOrderByUserId(userId);
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
    public ResponseEntity<ResultBean> deleteById(@PathVariable Integer id) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = orderService.deleteById(id);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * Adds the product.
     *
     * @param order the order
     * @param orderDetails the order details
     * @return the response entity
     * @throws Exception the exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> addOrder(@RequestBody OrderEntity order, @RequestBody List<OrderDetailEntity> orderDetails) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = orderService.addOrder(order, orderDetails);
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
    public ResponseEntity<ResultBean> update(@RequestBody OrderEntity json) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = orderService.updateOrder(json);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
        }

        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * Delete order detail.
     *
     * @param id the id
     * @return the response entity
     * @throws Exception the exception
     */
    @RequestMapping(value = "/deleteOrderDetail/{id}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> deleteOrderDetail(@RequestParam Integer id) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = orderDetailService.deleteOrderDetailById(id);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.STATUS_BAD_REQUEST, e.getMessage());
        }

        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
}
