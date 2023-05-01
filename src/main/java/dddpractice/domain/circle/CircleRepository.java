package dddpractice.domain.circle;

import java.util.Optional;

public interface CircleRepository {

	public Optional<Circle> findByName(CircleName name);

	public void insert(Circle circle);

	public void update(Circle circle);
}
