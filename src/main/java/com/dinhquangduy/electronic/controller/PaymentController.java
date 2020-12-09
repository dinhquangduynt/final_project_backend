package com.dinhquangduy.electronic.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dinhquangduy.electronic.bean.ResultBean;
import com.dinhquangduy.electronic.services.PaypalService;
import com.dinhquangduy.electronic.utils.Constants;
import com.dinhquangduy.electronic.utils.DataUtil;
import com.dinhquangduy.electronic.utils.PaypalPaymentIntent;
import com.dinhquangduy.electronic.utils.PaypalPaymentMethod;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller
public class PaymentController {
    public static final String URL_PAYPAL_SUCCESS = "pay/success";
    public static final String URL_PAYPAL_CANCEL = "pay/cancel";
    
    private Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private PaypalService paypalService;
    
    @RequestMapping(value = "/api/payment/add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
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

//    @GetMapping(URL_PAYPAL_CANCEL)
//    public String cancelPay(){
//            return "cancel";
//    }

    @RequestMapping(value = "/pay/success", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
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
