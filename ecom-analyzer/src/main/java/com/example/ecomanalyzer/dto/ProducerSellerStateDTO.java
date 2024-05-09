package com.example.ecomanalyzer.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProducerSellerStateDTO {
	
	private UUID producerId;
	
    private String producerName;
    
    private String sellerState;
    
    private UUID sellerId;

}
