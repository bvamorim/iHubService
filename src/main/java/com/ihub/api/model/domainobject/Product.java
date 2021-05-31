package com.ihub.api.model.domainobject;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * <h1>Product</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-29
 */
@Entity
@Table(name = "product")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Getter
@Setter
public class Product implements Serializable {

	private static final long serialVersionUID = -2804442291303773910L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String sku;

	@NonNull
	@Column
	private String name;

	@NonNull
	@Column
	private Double price;

	@NonNull
	@Column(name = "create_at")
	private LocalDateTime createAt = LocalDateTime.now();

}
