package com.ihub.api.model.datatransferobject;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h1>AddToCartDto</h1>
 *
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-15
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartDto {

	private Long id;
	private @NotNull Long productId;
	private @NotNull Integer quantity;

}