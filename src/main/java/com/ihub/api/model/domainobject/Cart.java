package com.ihub.api.model.domainobject;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h1>Cart</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-30
 */
@Entity
@Table(name = "cart")
@NoArgsConstructor
@Data
@Getter
@Setter
public class Cart implements Serializable {

	private static final long serialVersionUID = -424343956595034724L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;

	@JsonIgnore
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	private Integer quantity;

	public Cart(Product product, Integer quantity, User user) {
		this.user = user;
		this.product = product;
		this.quantity = quantity;
		this.createdDate = LocalDateTime.now();
	}

}
