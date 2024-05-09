package com.example.ecomanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecomanalyzer.model.Marketplace;

@Repository
public interface MarketplaceRepository extends JpaRepository<Marketplace, String> {

}
