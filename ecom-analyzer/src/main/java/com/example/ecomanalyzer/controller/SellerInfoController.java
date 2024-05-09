package com.example.ecomanalyzer.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecomanalyzer.dto.SellerPageableResponseDTO;
import com.example.ecomanalyzer.service.SellerInfoService;

@RestController
@RequestMapping("sellerInfo")
public class SellerInfoController {

	@Autowired
	private SellerInfoService sellerInfoService;

	@GetMapping("getSellerInfo")
	public ResponseEntity<SellerPageableResponseDTO> getSellerinfo(@RequestParam(required = false) String sellerName,
			@RequestParam(required = false) List<UUID> producerIds,
			@RequestParam(required = false) List<String> marketplaceIds, @RequestParam int page, @RequestParam int size,
			@RequestParam String sortBy) {
		Pageable pageable = PageRequest.of(page, size);
		SellerPageableResponseDTO response = sellerInfoService.getSellerInfo(sellerName, producerIds, marketplaceIds,
				sortBy, pageable);
		return ResponseEntity.ok(response);
	}

}
