package com.dinhquangduy.electronic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.dinhquangduy.electronic.services.PaypalService;
import com.dinhquangduy.electronic.utils.Constants;
import com.dinhquangduy.electronic.utils.DataUtil;
import com.dinhquangduy.electronic.utils.PaypalPaymentIntent;
import com.dinhquangduy.electronic.utils.PaypalPaymentMethod;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

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
    
    @Autowired
    private PaypalService paypalService;
    
    public static final String URL_PAYPAL_SUCCESS = "api/order/success";
    public static final String URL_PAYPAL_CANCEL = "api/order/cancel";
    
    private Logger log = LoggerFactory.getLogger(getClass());
    

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
            System.out.println(e);
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
    public ResponseEntity<ResultBean> addOrder(@RequestBody String orderJson) throws Exception {
        ResultBean resultBean = null;
        try {
            resultBean = orderService.addOrder(orderJson);
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
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
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
    
    @RequestMapping(value = "/payment", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> pay(HttpServletRequest request,@RequestParam("price") double price ){
            String cancelUrl = DataUtil.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
            String successUrl = DataUtil.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
            try {
                    Payment payment = paypalService.createPayment(
                                    price, 
                                    "USD", 
                                    PaypalPaymentMethod.paypal, 
                                    PaypalPaymentIntent.sale,
                                    "payment description", 
                                    cancelUrl, 
                                    successUrl);
                    for(Links links : payment.getLinks()){
                            if(links.getRel().equals("approval_url")){
                                    return new ResponseEntity<ResultBean>(new ResultBean(links.getHref(), Constants.MSG_OK), HttpStatus.OK);
                            }
                    }
            } catch (PayPalRESTException e) {
                    log.error(e.getMessage());
            }
            return new ResponseEntity<ResultBean>(new ResultBean(null, Constants.MSG_OK), HttpStatus.BAD_GATEWAY);
    }
    
    @RequestMapping(value = URL_PAYPAL_SUCCESS, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
            try {
                    Payment payment = paypalService.executePayment(paymentId, payerId);
                    if(payment.getState().equals("approved")){
                            return "success";
                    }
            } catch (PayPalRESTException e) {
                    log.error(e.getMessage());
            }
            return "redirect:/";
    }
}
