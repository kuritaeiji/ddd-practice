package dddpractice.domain.user;

import org.springframework.util.StringUtils;

import dddpractice.share.exception.Warning;

public record UserName(String value) {

	public UserName(String value) {
		if (!StringUtils.hasText(value)) {
			throw new Warning("ユーザー名は入力必須です");
		}

		if (value.length() < 3) {
			throw new Warning("ユーザ名は3文字以上入力してください");
		}

		if (value.length() > 20) {
			throw new Warning("ユーザー名は20文字以内で入力してください");
		}

		this.value = value;
	}
}
