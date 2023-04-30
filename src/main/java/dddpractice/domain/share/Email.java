package dddpractice.domain.share;

import org.springframework.util.StringUtils;

import dddpractice.share.exception.Warning;

public record Email(String value) {

	public Email(String value) {
		if (!StringUtils.hasText(value)) {
			throw new Warning("メールアドレスは入力必須です");
		}

		if (value.length() > 255) {
			throw new Warning("メールアドレスは255文字以内です");
		}

		this.value = value;
	}
}
