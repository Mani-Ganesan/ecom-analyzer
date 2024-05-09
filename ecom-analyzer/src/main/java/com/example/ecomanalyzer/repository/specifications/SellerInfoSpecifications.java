package com.example.ecomanalyzer.repository.specifications;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.example.ecomanalyzer.model.SellerInfo;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

public class SellerInfoSpecifications {

	public static Specification<SellerInfo> withFiltersAndSorting(String sellerName, List<UUID> producerIds,
			List<String> marketplaceIds, String sortBy) {
		return (root, query, criteriaBuilder) -> {
			Predicate predicate = null;

			// Apply filters
			if (sellerName != null) {
				predicate = criteriaBuilder.like(root.get("name"), "%" + sellerName + "%");
			}

			if (producerIds != null && !producerIds.isEmpty()) {
				Predicate producerPredicate = root.join("seller", JoinType.INNER).get("producer").get("id")
						.in(producerIds);
				predicate = predicate != null ? criteriaBuilder.and(predicate, producerPredicate) : producerPredicate;
			}

			if (marketplaceIds != null && !marketplaceIds.isEmpty()) {
				Predicate marketplacePredicate = root.get("marketplace").get("id").in(marketplaceIds);
				predicate = predicate != null ? criteriaBuilder.and(predicate, marketplacePredicate)
						: marketplacePredicate;
			}

			// Sorting
			if (sortBy != null) {
				switch (sortBy) {
				case "NAME_ASC":
					query.orderBy(criteriaBuilder.asc(root.get("name")));
					break;
				case "NAME_DESC":
					query.orderBy(criteriaBuilder.desc(root.get("name")));
					break;
				case "MARKETPLACE_ID_ASC":
					query.orderBy(criteriaBuilder.asc(root.get("marketplace").get("id")));
					break;
				case "MARKETPLACE_ID_DESC":
					query.orderBy(criteriaBuilder.desc(root.get("marketplace").get("id")));
					break;
				case "SELLER_INFO_EXTERNAL_ID_ASC":
					query.orderBy(criteriaBuilder.asc(root.get("externalId")));
					break;
				case "SELLER_INFO_EXTERNAL_ID_DESC":
					query.orderBy(criteriaBuilder.desc(root.get("externalId")));
					break;
				}
			}

			return predicate;
		};
	}
}
