package com.InditexEnterprice.price.domain.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Getter @Setter
public class Price {
        @Schema(hidden = true)
        private Long priceId;
        private Long brandId;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Long priceList;
        private Long productId;
        private Long priority;
        private float price;
        private String currency;

        private Price(){}


        public static class Builder{
                @Autowired
                private Price price;

                public Builder(LocalDateTime date, Long brandId, Long productId){
                        price =  new Price();
                        price.setBrandId(brandId);
                        price.setProductId(productId);
                        price.setStartDate(date);
                }

                public Builder addPriceList(Long priceList){
                        price.setPriceList(priceList);
                        return this;
                }
                public Builder addEndDate(LocalDateTime date){
                        price.setEndDate(date);
                        return this;
                }
                public Builder addPriority(Long priority){
                        price.setPriority(priority);
                        return this;
                }
                public Builder addPrice(float value){
                        price.setPrice(value);
                        return this;
                }
                public Builder addCurrency(String currency){
                        price.setCurrency(currency);
                        return this;
                }

                public Price build(){
                        return price;
                }

        }


}
