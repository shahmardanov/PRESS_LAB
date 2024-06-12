package com.example.press_lab.mappers;

import com.example.press_lab.entity.Subscription;
import com.example.press_lab.response.subscription.SubscriptionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SubscriptionMapper {

    SubscriptionResponse mapSubscriptionToResponse(Subscription subscription);

}
