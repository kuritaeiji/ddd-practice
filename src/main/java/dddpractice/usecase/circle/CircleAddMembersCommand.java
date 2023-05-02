package dddpractice.usecase.circle;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CircleAddMembersCommand {

	private final String name;
	private final List<Long> addingMemberIds;
}
