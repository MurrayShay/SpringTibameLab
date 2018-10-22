package com.tibame.controller;

import com.thoughtworks.xstream.XStream;
import com.tibame.domain.MySQLConnetionInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppInfoController {
    private static final Log logger = LogFactory.getLog(AppInfoController.class);

    @RequestMapping(value = "/readDb_info",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_XML_VALUE)
    public String  readAppInfo(){
        ApplicationContext context
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        MySQLConnetionInfo mySQLConnetionInfo = context.getBean("sqlConnetionInfo", MySQLConnetionInfo.class);
        XStream xStream = new XStream();
        xStream.alias("MySQLConnectionInfo", MySQLConnetionInfo.class);
        String sqlConnetionInfoXml = xStream.toXML(mySQLConnetionInfo);
        System.out.println(sqlConnetionInfoXml);
        return sqlConnetionInfoXml;
    }
}
