package dddpractice.domain.circle;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CircleIsDuplicateDomainService {

	private final CircleRepository circleRepository;

	public boolean execute(Circle circle) {
		return circleRepository.findByName(circle.getName()).isPresent();
	}
}
