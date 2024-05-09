package com.example.ecomanalyzer.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "producers")
@Data
public class Producer {
	
	@Id
    @GeneratedValue
    private UUID id;

    private String name;
    
    private LocalDateTime createdAt;
	 
}
