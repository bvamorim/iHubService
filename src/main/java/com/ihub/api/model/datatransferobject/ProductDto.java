package com.ihub.api.model.datatransferobject;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * <h1>ProductDto</h1>
 *
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-30
 */
@Getter
@Setter
@AllArgsConstructor
public class ProductDto {

	private Long id;
	private @NotNull String sku;
	private @NotNull String name;
	private @NotNull double price;
	private LocalDateTime createAt;

}
