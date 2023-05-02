package dddpractice.presentation.circle;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CircleCreateRequest {

	private final String name;
	private final Long ownerId;
	private final List<Long> memberIds;
}
