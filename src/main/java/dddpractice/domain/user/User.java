package dddpractice.domain.user;

import dddpractice.domain.share.Email;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

	@EqualsAndHashCode.Include
	private final Long id;
	private Email email;
	private UserName name;
	private UserType type;

	public static User reconstruct(
			Long id,
			Email email,
			UserName name,
			UserType type) {

		return new User(id, email, name, type);
	}

	public static User create(String email, String name, String typeCode) {
		return new User(null, new Email(email), new UserName(name), UserType.fromCode(typeCode));
	}

	public void changeName(String name) {
		this.name = new UserName(name);
	}

	public void changeType(String typeCode) {
		this.type = UserType.fromCode(typeCode);
	}
}
