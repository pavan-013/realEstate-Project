package com.resp.dto;
import java.util.Set;

import com.resp.model.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
	
	private long id;
    private String username;
    private long mobilenumber;
    private String email;
    private String password;
    private Set<Role> roles;  // Assuming roles are represented as Strings (e.g., "ROLE_USER"
	private String token;
}
