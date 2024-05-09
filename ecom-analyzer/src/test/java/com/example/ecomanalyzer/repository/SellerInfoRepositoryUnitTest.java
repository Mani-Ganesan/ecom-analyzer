package com.example.ecomanalyzer.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

import com.example.ecomanalyzer.model.SellerInfo;
import com.example.ecomanalyzer.repository.specifications.SellerInfoSpecifications;

@ExtendWith(MockitoExtension.class)
public class SellerInfoRepositoryUnitTest {

    @Mock
    private SellerInfoRepository sellerInfoRepository;

    @InjectMocks
    private SellerInfoSpecifications sellerInfoSpecifications;

    @Test
    public void testFindAll() {
        // Mock input parameters
        String sellerName = "Amanoz us";
        List<UUID> producerIds = new ArrayList<>();
        List<String> marketplaceIds = new ArrayList<>();
        String sortBy = "NAME_ASC";
        int page = 0;
        int size = 1;
        Pageable pageable = PageRequest.of(page, size);

        // Mock sellerInfos
        List<SellerInfo> sellerInfos = new ArrayList<>();
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setName("Amanoz us");
        sellerInfos.add(sellerInfo);

        Page<SellerInfo> sellerInfoPage = new PageImpl<>(sellerInfos);

        // Mock repository method
        Specification<SellerInfo> specification = SellerInfoSpecifications.withFiltersAndSorting(sellerName, producerIds,
                marketplaceIds, sortBy);
        when(sellerInfoRepository.findAll(specification, pageable)).thenReturn(sellerInfoPage);

        // Call repository method
        Page<SellerInfo> result = sellerInfoRepository.findAll(specification, pageable);

        // Verify the result
        assertEquals(1, result.getContent().size());
        SellerInfo resultSellerInfo = result.getContent().get(0);
        assertEquals("Amanoz us", resultSellerInfo.getName());
    }
}
