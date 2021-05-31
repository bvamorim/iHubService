package com.ihub.api.model.domainobject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h1>User</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-29
 */
@Entity
@Table(name = "user")
@NoArgsConstructor
@Data
@Getter
@Setter
public class User implements Serializable {

	private static final long serialVersionUID = 5600780143011308925L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String username;

	@Column(unique = true)
	private String email;
	
	@Column
	@JsonIgnore
	private String password;
	
}