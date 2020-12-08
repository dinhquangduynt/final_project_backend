package com.dinhquangduy.electronic.services.impl;


import com.dinhquangduy.electronic.bean.ResultBean;
import com.dinhquangduy.electronic.bean.entity.ContactEntity;
import com.dinhquangduy.electronic.config.LogExecutionTime;
import com.dinhquangduy.electronic.dao.ContactDao;
import com.dinhquangduy.electronic.services.ContactService;
import com.dinhquangduy.electronic.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@LogExecutionTime
public class ContactServiceImpl implements ContactService {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);

    /** The mapper. */
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ContactDao contactDao;

    @Override
    public ResultBean getAll() throws Exception {
        List<ContactEntity> responses = contactDao.findAll();
        return new ResultBean(responses, Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean getById(Integer id) throws Exception {
        Optional<ContactEntity> contactOp = contactDao.findById(id);
        if(!contactOp.isPresent()) {
            throw new Exception("Contact by id " + id + " does not exist!");
        }
        return new ResultBean(contactOp.get(), Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean addContact(String json) throws Exception {
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### Start Add Contact###");
        ContactEntity contact = updateEntity(json);
        contactDao.save(contact);
        log.info("### End Add Contact ###");
        log.info("##########################################");
        return new ResultBean(Constants.STATUS_201, Constants.MSG_OK);
    }

    private ContactEntity updateEntity(String json) throws Exception {
        return mapper.readValue(json, ContactEntity.class);
    }

//    @Override
////    public ResultBean updateFeedback(FeedbackEntity entity) throws Exception {
////        Optional<FeedbackEntity> feedbackOp = feedbackDao.findById(entity.getId());
////        if(!feedbackOp.isPresent()) {
////            throw new Exception("Feedback by id " + entity.getId() + " does not exist!");
////        }
////        FeedbackEntity feedback = feedbackDao.save(entity);
////        return new ResultBean(feedback, Constants.STATUS_OK, Constants.MSG_OK);
////    }

    @Override
    public ResultBean deleteById(Integer id) throws Exception {
        Optional<ContactEntity> contactOp = contactDao.findById(id);
        if(!contactOp.isPresent()) {
            throw new Exception("Contact by id " + id + " does not exist!");
        }
        contactDao.deleteById(id);
        return new ResultBean(Constants.STATUS_OK, Constants.MSG_DELETE_OK);
    }
}
