package com.spring.boot.RabbitTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.boot.entity.Order;
import com.spring.boot.utils.RabbitSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitTest {

    @Autowired
    private RabbitSender rabbitSender;
    
    @Test
    public void sendTest() {
        Order order = new Order();
        order.setId("one");
        order.setName("zhangxuesong");
        try {
            rabbitSender.sendOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
