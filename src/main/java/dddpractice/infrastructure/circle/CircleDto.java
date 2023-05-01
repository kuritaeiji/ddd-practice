package dddpractice.infrastructure.circle;

import java.util.List;

import lombok.Getter;

@Getter
public class CircleDto {

	private Long id;
	private String name;
	private Long ownerId;
	private Long version;
	private List<Long> memberIds;
}
