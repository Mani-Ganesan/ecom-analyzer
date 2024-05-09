package com.example.ecomanalyzer.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.ecomanalyzer.dto.PageMetaDTO;
import com.example.ecomanalyzer.dto.ProducerSellerStateDTO;
import com.example.ecomanalyzer.dto.SellerInfoDTO;
import com.example.ecomanalyzer.dto.SellerPageableResponseDTO;
import com.example.ecomanalyzer.model.Marketplace;
import com.example.ecomanalyzer.model.SellerInfo;
import com.example.ecomanalyzer.repository.SellerInfoRepository;
import com.example.ecomanalyzer.repository.specifications.SellerInfoSpecifications;

@Service
public class SellerInfoService {
	
	@Autowired
	private SellerInfoRepository sellerInfoRepo;
	
	public SellerPageableResponseDTO getSellerInfo(String sellerName, List<UUID> producerIds, List<String> marketplaceIds,
			String sortBy, Pageable pageable) {
		Specification<SellerInfo> specification = SellerInfoSpecifications.withFiltersAndSorting(sellerName, producerIds,
				marketplaceIds, sortBy);
		Page<SellerInfo> sellerInfos = sellerInfoRepo.findAll(specification, pageable);
		List<SellerInfoDTO> sellerDTOs= sellerInfos.getContent().stream().map(this::mapToDTO).collect(Collectors.toList());
		return new SellerPageableResponseDTO(sellerDTOs,
				new PageMetaDTO(sellerInfos.getNumber(), sellerInfos.getSize(), sellerInfos.getTotalElements()));
	}

	private SellerInfoDTO mapToDTO(SellerInfo sellerInfo) {
		if (sellerInfo == null) {
	        return null;
	    }
		
		SellerInfoDTO sellerInfoDTO = new SellerInfoDTO();
		sellerInfoDTO.setSellerName(sellerInfo.getName());
		sellerInfoDTO.setExternalId(sellerInfo.getExternalId());
		
		Marketplace marketplace = sellerInfo.getMarketplace();
	    if (marketplace != null) {
	    	sellerInfoDTO.setMarketplaceId(marketplace.getId());
	    }

	    List<ProducerSellerStateDTO> producerSellerStates = sellerInfo.getSeller().stream()
                .map(seller -> new ProducerSellerStateDTO(
                        seller.getProducer().getId(),
                        seller.getProducer().getName(),
                        seller.getState(),
                        seller.getId()
                ))
                .collect(Collectors.toList());
        sellerInfoDTO.setProducerSellerStates(producerSellerStates);
		return sellerInfoDTO;
	}

}
