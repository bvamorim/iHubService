package com.ihub.api.model.domainobject;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h1>OrderItem</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-30
 */
@Entity
@Table(name = "orderitems")
@NoArgsConstructor
@Data
@Getter
@Setter
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 8153162521356506453L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderItemId;

	@Column(name = "productId")
	private @NotNull Long productId;

	@Column(name = "quantity")
	private @NotNull Integer quantity;

	@Column(name = "price")
	private @NotNull Double price;

	@Column(name = "my_order_id")
	private Long myOrderId;

	@Column(name = "created_date")
	private LocalDateTime createdDate;

//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name = "my_order_id",referencedColumnName = "id", insertable = false, updatable = false)
//    private MyOrder myOrder;

	@OneToOne
	@JoinColumn(name = "productId", referencedColumnName = "id", insertable = false, updatable = false)
	private Product product;

	public OrderItem(Long myOrderId, @NotNull Long productId, @NotNull Integer quantity, @NotNull Double price) {
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
		this.myOrderId = myOrderId;
		this.createdDate = LocalDateTime.now();
	}

}