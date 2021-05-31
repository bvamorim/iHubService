package com.ihub.api.model.datatransferobject;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h1>OrderItemsDto</h1>
 *
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-30
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemsDto {

	private @NotNull double price;
	private @NotNull int quantity;
	private @NotNull long MyOrderId;
	private @NotNull long productId;

}