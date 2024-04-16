package com.example.payment;

import com.example.payment.dto.OrderCreatedDto;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.concurrent.TimeUnit;

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

    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();
    }

    @Test
    void createOrder() throws Exception {
        // before
        OrderCreatedDto orderCreatedDto = new OrderCreatedDto();
        orderCreatedDto.setId(12L);
        orderCreatedDto.setName("OrderPendingPayment_name");

        // act
        kafkaProducer.sendOrder(orderCreatedDto);

        // verify
        boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);
        assertTrue(messageConsumed);

        OrderCreatedDto receivedMessage = consumer.getReceivedMessage();
        assertEquals(receivedMessage.getId(), orderCreatedDto.getId());
        assertEquals(receivedMessage.getName(),orderCreatedDto.getName());
    }
}
