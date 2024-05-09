package com.example.ecomanalyzer.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.ecomanalyzer.model.SellerInfo;

@Repository
public interface SellerInfoRepository extends JpaRepository<SellerInfo, UUID>, JpaSpecificationExecutor<SellerInfo> {
	
	Page<SellerInfo> findAll(Specification<SellerInfo> specification, Pageable pageable);

}
