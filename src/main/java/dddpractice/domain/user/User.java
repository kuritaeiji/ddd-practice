package dddpractice.domain.user;

import dddpractice.domain.share.Email;
import dddpractice.domain.share.Version;
import dddpractice.usecase.user.UserCreateCommand;
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
	private final Version version;

	public static User reconstruct(
			Long id,
			Email email,
			UserName name,
			UserType type,
			Version version) {

		return new User(id, email, name, type, version);
	}

	public static User create(UserCreateCommand command) {
		return new User(
				null,
				new Email(command.getEmail()),
				new UserName(command.getName()),
				UserType.fromCode(command.getTypeCode()),
				Version.init());
	}

	public void changeEmail(String email) {
		this.email = new Email(email);
	}

	public void changeType(String typeCode) {
		this.type = UserType.fromCode(typeCode);
	}
}
