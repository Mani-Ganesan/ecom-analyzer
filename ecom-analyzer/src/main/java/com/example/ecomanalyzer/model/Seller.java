package com.example.ecomanalyzer.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "sellers")
@Data
public class Seller {

	@Id
	@GeneratedValue
	private UUID id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "producer_id")
	private Producer producer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "seller_info_id")
	@JsonIgnore
	private SellerInfo sellerInfo;

	private String state;

}
