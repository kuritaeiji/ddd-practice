package dddpractice.usecase.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserCreateCommand {

	private final String email;
	private final String name;
	private final String typeCode;
}
