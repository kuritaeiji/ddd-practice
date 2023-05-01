package dddpractice.infrastructure.user;

import lombok.Getter;

@Getter
public class UserDto {

	private Long id;
	private String email;
	private String name;
	private String type;
	private Long version;
}
