package dddpractice.factory.user;

import dddpractice.domain.share.Email;
import dddpractice.domain.share.Version;
import dddpractice.domain.user.User;
import dddpractice.domain.user.UserName;
import dddpractice.domain.user.UserType;

public class TestUserFactory {

	private static Long ID_NUMBER = 0L;
	private static Integer EMAIL_NUMBER = 0;

	public static User create(Long id, String email, String name, UserType userType, Long version) {
		if (id == null) {
			ID_NUMBER++;
			id = ID_NUMBER;
		}

		if (email == null) {
			EMAIL_NUMBER++;
			email = String.format("example%d@example.com", EMAIL_NUMBER);
		}

		if (name == null) {
			name = "テストユーザー";
		}

		if (userType == null) {
			userType = UserType.STANDARD;
		}

		if (version == null) {
			version = 1L;
		}

		return User.reconstruct(id, new Email(email), new UserName(name), userType, new Version(version));
	}
}
