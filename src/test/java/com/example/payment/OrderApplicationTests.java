package com.example.payment;

import com.example.payment.dto.OrderCreatedMessage;
import com.example.payment.dto.PaymentExecutedMessage;
import com.example.payment.dto.PaymentRejectedMessage;
import com.example.payment.entity.PaymentEntity;
import com.example.payment.kafka.KafkaProducerService;
import com.example.payment.repository.PaymentRepository;
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
    PaymentRepository paymentRepository;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private KafkaPaymentConsumer paymentConsumer;

    @Autowired
    private KafkaOrderProducer kafkaOrderProducer;

    // To disable second @KafkaListener
//    @MockBean
//    KafkaConsumerService kafkaConsumerService;


    @Autowired
    private KafkaProducerService kafkaProducerService;

    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();
    }

    @Test
    void orderReceiveTest() throws InterruptedException {
        // setup
        OrderCreatedMessage orderCreatedMessage = new OrderCreatedMessage();
        orderCreatedMessage.setOrderId(15L);
        orderCreatedMessage.setOrderDescription("OrderPendingPayment_name");

        // act
        kafkaOrderProducer.sendOrder(orderCreatedMessage);
        sleep(5000);

        // verify
        PaymentEntity lastOrder = paymentRepository.findFirstByOrderByIdDesc();
        log.info("Last record={}", lastOrder);
        boolean messageConsumed = paymentConsumer.getLatch().await(10, TimeUnit.SECONDS);
        assertTrue(messageConsumed);

        PaymentExecutedMessage executedMessage = paymentConsumer.getExecutedMessage();
        log.info("Execute message: {}", executedMessage);

        PaymentRejectedMessage rejectedMessage = paymentConsumer.getRejectedMessage();
        log.info("Rejected message: {}", rejectedMessage);
    }
}
