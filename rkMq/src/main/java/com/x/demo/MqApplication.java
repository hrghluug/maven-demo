package com.x.demo;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.text.ParseException;
import java.util.Date;

/**
 * @author xgh 2023/6/30
 */
@SpringBootApplication
public class MqApplication {
    public static void main(String[] args) throws ParseException {
//        ConfigurableApplicationContext run = SpringApplication.run(MqApplication.class, args);
        System.out.println(findBirthDay("352201199911104717"));
    }
    public static Date  findBirthDay(String idCard) throws ParseException {
        String s = idCard.substring(6, 14);
        System.out.println(s);
        return DateUtils.parseDate(s, "yyyyMMdd");
    }
}
