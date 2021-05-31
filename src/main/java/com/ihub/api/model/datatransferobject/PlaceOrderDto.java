package com.ihub.api.model.datatransferobject;

import javax.validation.constraints.NotNull;

import com.ihub.api.model.domainobject.MyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h1>PlaceOrderDto</h1>
 *
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-30
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderDto {
	private Long id;
	private @NotNull Long userId;
	private @NotNull Double totalPrice;

	public PlaceOrderDto(MyOrder myOrder) {
		this.setId(myOrder.getId());
		this.setUserId(myOrder.getUserId());
		this.setTotalPrice(myOrder.getTotalPrice());
	}

}