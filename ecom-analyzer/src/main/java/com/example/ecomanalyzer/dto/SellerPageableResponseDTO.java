package com.example.ecomanalyzer.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SellerPageableResponseDTO {

	private List<SellerInfoDTO> data;
	
    private PageMetaDTO meta;

}
