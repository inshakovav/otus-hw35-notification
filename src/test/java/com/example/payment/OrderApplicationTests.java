package com.example.payment;

import com.example.payment.dto.OrderCreatedMessage;
import com.example.payment.kafka.KafkaProducerService;
import com.example.payment.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
@ActiveProfiles("local")
class OrderApplicationTests {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private KafkaConsumer consumer;

    // To disable second @KafkaListener
//    @MockBean
//    KafkaConsumerService kafkaConsumerService;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();
    }

//    @Test
//    void createOrder() throws Exception {
//        // before
//        OrderCreatedMessage orderCreatedMessage = new OrderCreatedMessage();
//        orderCreatedMessage.setOrderId(12L);
//        orderCreatedMessage.setOrderName("OrderPendingPayment_name");
//
//        // act
//        kafkaProducer.sendOrder(orderCreatedMessage);
//
//        // verify
//        boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);
//        assertTrue(messageConsumed);
//
//        OrderCreatedMessage receivedMessage = consumer.getReceivedMessage();
//        assertEquals(receivedMessage.getOrderId(), orderCreatedMessage.getOrderId());
//        assertEquals(receivedMessage.getOrderName(), orderCreatedMessage.getOrderName());
//    }

    @Test
    void orderReceiveTest() throws InterruptedException {
        OrderCreatedMessage orderCreatedMessage = new OrderCreatedMessage();
        orderCreatedMessage.setOrderId(15L);
        orderCreatedMessage.setOrderDescription("OrderPendingPayment_name");
        kafkaProducerService.sendOrder(orderCreatedMessage);
        sleep(5000);
//        boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);
//        assertTrue(messageConsumed);
    }
}
