package com.tibame.controller;

import com.thoughtworks.xstream.XStream;
import com.tibame.dao.IDao;
import com.tibame.entity.Customers;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class CustomersController {
    private static final Log logger = LogFactory.getLog(CustomersController.class);

    @RequestMapping(value = "/readCustomerById/{cId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_XML_VALUE)
    public String readCustomerSingleData(@PathVariable String cId) {
        String sql="select * from Customers where CustomerID=?";

        ApplicationContext context
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        IDao<Customers> customerDaoImpl = context.getBean("CustomerDaoImpl", IDao.class);
        Customers customers = null;

        try {
            customers = customerDaoImpl.queryForObject(sql,cId);
        } catch (SQLException e) {
            logger.error("執行SQL查詢語法失敗");
        }

        XStream xStream = new XStream();
        xStream.alias("CustomerSingleData", Customers.class);
        String customerSingleData = xStream.toXML(customers);
        logger.trace(customerSingleData);

        return customerSingleData;
    }
}
