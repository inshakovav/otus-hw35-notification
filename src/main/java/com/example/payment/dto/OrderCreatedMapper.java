package com.example.payment.dto;

import com.example.payment.entity.PaymentEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface OrderCreatedMapper {
    PaymentEntity OrderCreatedToDb(OrderCreatedMessage message);
}
