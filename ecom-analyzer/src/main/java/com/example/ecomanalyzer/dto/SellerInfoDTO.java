package com.example.ecomanalyzer.dto;

import java.util.List;

import lombok.Data;

@Data
public class SellerInfoDTO {
	
	private String sellerName;
	
    private String externalId;
    
    private String marketplaceId;
    
    private List<ProducerSellerStateDTO> producerSellerStates;

}
