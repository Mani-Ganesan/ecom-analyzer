package com.example.ecomanalyzer.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.ecomanalyzer.controller.SellerInfoController;
import com.example.ecomanalyzer.dto.PageMetaDTO;
import com.example.ecomanalyzer.dto.SellerInfoDTO;
import com.example.ecomanalyzer.dto.SellerPageableResponseDTO;
import com.example.ecomanalyzer.service.SellerInfoService;

@WebMvcTest(SellerInfoController.class)
public class SellerInfoControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SellerInfoService sellerInfoService;

	@Test
	public void testGetSellerInfo() throws Exception {
		UUID producerId = UUID.randomUUID();
		UUID marketplaceId = UUID.randomUUID();

		SellerInfoDTO sellerInfoDTO = new SellerInfoDTO();
		sellerInfoDTO.setSellerName("Amanoz us");
		sellerInfoDTO.setExternalId("123");
		sellerInfoDTO.setMarketplaceId(marketplaceId.toString());
		sellerInfoDTO.setProducerSellerStates(Collections.emptyList());

		SellerPageableResponseDTO responseDTO = new SellerPageableResponseDTO(Collections.singletonList(sellerInfoDTO),
				new PageMetaDTO(0, 1, 1));

		when(sellerInfoService.getSellerInfo(any(), any(), any(), any(), any())).thenReturn(responseDTO);

		mockMvc.perform(get("/sellerInfo/getSellerInfo").param("page", "0").param("size", "1")
				.param("sortBy", "NAME_ASC").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data[0].sellerName").value("Amanoz us"))
				.andExpect(jsonPath("$.data[0].externalId").value("123"))
				.andExpect(jsonPath("$.data[0].marketplaceId").value(marketplaceId.toString()))
				.andExpect(jsonPath("$.meta.pageNumber").value(0)).andExpect(jsonPath("$.meta.pageSize").value(1))
				.andExpect(jsonPath("$.meta.totalItems").value(1));
	}
}
