package dddpractice.share;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Pair<T, U> {

	private final T left;
	private final U right;
}
