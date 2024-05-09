package com.example.ecomanalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageMetaDTO {
	
	private int pageNumber;
	
    private int pageSize;
    
    private long totalItems;

}
