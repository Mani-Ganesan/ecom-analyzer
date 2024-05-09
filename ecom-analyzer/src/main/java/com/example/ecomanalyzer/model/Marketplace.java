package com.example.ecomanalyzer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "marketplaces")
@Data
public class Marketplace {
	
	@Id
    private String id;

    private String description;

}
