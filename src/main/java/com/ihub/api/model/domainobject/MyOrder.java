package com.ihub.api.model.domainobject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ihub.api.model.datatransferobject.PlaceOrderDto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * <h1>MyOrder</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-29
 */
@Entity
@Table(name = "my_order")
@NoArgsConstructor
@Data
@Getter
@Setter
public class MyOrder implements Serializable {

	private static final long serialVersionUID = -6595884658990448057L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@NonNull
	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "total_price")
	private Double totalPrice;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "my_order_id", referencedColumnName = "id", insertable = false, updatable = false)
	private List<OrderItem> orderItems;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
	private User user;

	public MyOrder(PlaceOrderDto orderDto, Long userId) {
		this.userId = userId;
		this.createdDate = LocalDateTime.now();
		this.totalPrice = orderDto.getTotalPrice();
	}

}
