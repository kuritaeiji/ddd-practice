package dddpractice.domain.circle;

import org.springframework.util.StringUtils;

import dddpractice.share.exception.Warning;

public record CircleName(String value) {

	public CircleName(String value) {
		if (!StringUtils.hasText(value)) {
			throw new Warning("サークル名は入力必須です");
		}

		if (value.length() < 3) {
			throw new Warning("サークル名には3文字以上入力してください");
		}

		if (value.length() > 20) {
			throw new Warning("サークル名には20文字以内で入力してください");
		}

		this.value = value;
	}
}
