package com.ihub.api.model.datatransferobject;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h1>CartDto</h1>
 *
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-30
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

	private List<CartItemDto> cartItems;
	private Double totalCost;

}