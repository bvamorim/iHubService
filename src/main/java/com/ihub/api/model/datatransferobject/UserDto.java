package com.ihub.api.model.datatransferobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * <h1>UserDto</h1>
 *
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-15
 */
@Getter
@Setter
@AllArgsConstructor
public class UserDto {

	private String username;
	private String email;
	private String password;

}