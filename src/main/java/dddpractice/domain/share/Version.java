package dddpractice.domain.share;

public record Version(Long value) {

	public static Version init() {
		return new Version(0L);
	}
}
