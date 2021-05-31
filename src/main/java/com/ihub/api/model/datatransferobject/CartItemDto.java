package com.ihub.api.model.datatransferobject;

import javax.validation.constraints.NotNull;

import com.ihub.api.model.domainobject.Cart;
import com.ihub.api.model.domainobject.OrderItem;
import com.ihub.api.model.domainobject.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h1>CartItemDto</h1>
 *
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-30
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

	private Long id;
	private @NotNull Long userId;
	private @NotNull Integer quantity;
	private @NotNull Product product;

	public CartItemDto(Cart cart) {
		this.setId(cart.getId());
		this.setUserId(cart.getUser().getId());
		this.setQuantity(cart.getQuantity());
		this.setProduct(cart.getProduct());
	}

	public OrderItem toOrdemItem(Long orderId) {
		return new OrderItem(orderId, this.product.getId(), this.quantity, this.product.getPrice());
	}

}