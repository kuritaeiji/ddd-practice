package dddpractice.domain.user;

import java.util.Arrays;

import dddpractice.share.exception.Error;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserType {

	STANDARD("1"),
	PREMIUM("2");

	private final String code;

	public static UserType fromCode(String code) {
		return Arrays
				.stream(UserType.values())
				.filter((userType) -> userType.getCode().equals(code))
				.findFirst()
				.orElseThrow(() -> new Error("有効なユーザータイプを選択してください"));
	}
}
