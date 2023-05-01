package dddpractice.domain.user;

import dddpractice.domain.share.Email;
import dddpractice.domain.share.Version;
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

	public static User create(String email, String name, String typeCode) {
		return new User(null, new Email(email), new UserName(name), UserType.fromCode(typeCode), Version.init());
	}

	public void changeEmail(String email) {
		this.email = new Email(email);
	}

	public void changeType(String typeCode) {
		this.type = UserType.fromCode(typeCode);
	}
}
