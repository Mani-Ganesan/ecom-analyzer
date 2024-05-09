package com.example.ecomanalyzer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "seller_infos")
@Data
public class SellerInfo {
	
	@Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "marketplace_id")
    private Marketplace marketplace;

    private String name;
    
    private String url;
    
    private String country;
    
    private String externalId;
    
    @OneToMany(mappedBy = "sellerInfo", fetch = FetchType.EAGER)
    private List<Seller> seller = new ArrayList<>();

}
