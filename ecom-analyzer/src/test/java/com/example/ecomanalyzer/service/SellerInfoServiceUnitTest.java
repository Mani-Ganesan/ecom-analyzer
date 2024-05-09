package com.example.ecomanalyzer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.example.ecomanalyzer.dto.SellerInfoDTO;
import com.example.ecomanalyzer.dto.SellerPageableResponseDTO;
import com.example.ecomanalyzer.model.Marketplace;
import com.example.ecomanalyzer.model.SellerInfo;
import com.example.ecomanalyzer.repository.SellerInfoRepository;
import com.example.ecomanalyzer.repository.specifications.SellerInfoSpecifications;

@ExtendWith(MockitoExtension.class)
public class SellerInfoServiceUnitTest {

    @Mock
    private SellerInfoRepository sellerInfoRepository;

    @InjectMocks
    private SellerInfoService sellerInfoService;

    @Test
    public void testGetSellerInfo() {
        // Mock input parameters
        String sellerName = "Amanoz us";
        List<UUID> producerIds = new ArrayList<>();
        List<String> marketplaceIds = new ArrayList<>();
        String sortBy = "NAME_ASC";
        int page = 0;
        int size = 1;
        Pageable pageable = PageRequest.of(page, size);

        // Mock sellerInfos
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setName("Amanoz us");
        sellerInfo.setExternalId("123");
        Marketplace marketplace = new Marketplace();
        marketplace.setId("6a5230d8-63dc-4645-8159-757f86acff58");
        sellerInfo.setMarketplace(marketplace);
        sellerInfo.setSeller(new ArrayList<>());
        Page<SellerInfo> sellerInfoPage = new PageImpl<>(Collections.singletonList(sellerInfo));

        // Mock repository method
        Specification<SellerInfo> specification = SellerInfoSpecifications.withFiltersAndSorting(sellerName, producerIds,
				marketplaceIds, sortBy);
        when(sellerInfoRepository.findAll(specification, pageable)).thenReturn(sellerInfoPage);

        // Call service method
        SellerPageableResponseDTO response = sellerInfoService.getSellerInfo(sellerName, producerIds, marketplaceIds, sortBy, pageable);

        // Verify the response
        assertEquals(1, response.getData().size());
        SellerInfoDTO sellerInfoDTO = response.getData().get(0);
        assertEquals("Amanoz us", sellerInfoDTO.getSellerName());
        assertEquals("123", sellerInfoDTO.getExternalId());
        assertEquals("6a5230d8-63dc-4645-8159-757f86acff58", sellerInfoDTO.getMarketplaceId());
        assertEquals(0, sellerInfoDTO.getProducerSellerStates().size());
        assertEquals(0, response.getMeta().getPageNumber());
        assertEquals(1, response.getMeta().getPageSize());
        assertEquals(1, response.getMeta().getTotalItems());
    }
}
