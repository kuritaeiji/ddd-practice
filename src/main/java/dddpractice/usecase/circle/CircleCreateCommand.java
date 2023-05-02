package dddpractice.usecase.circle;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CircleCreateCommand {

	private final String name;
	private final Long ownerId;
	private final List<Long> memberIds;
}
