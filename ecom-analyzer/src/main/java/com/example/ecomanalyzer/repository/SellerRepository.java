package com.example.ecomanalyzer.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecomanalyzer.model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, UUID>{

}
