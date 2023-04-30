package dddpractice.domain.share;

import java.util.ArrayList;
import java.util.List;

import dddpractice.share.exception.Fatal;

public abstract class EventEntity {

	private final List<Event> events = new ArrayList<>();

	public List<Event> getEvents() {
		return new ArrayList<>(events);
	}

	public void addEvent(Event event) {
		if (event == null) {
			throw new Fatal("引数イベントがNullになっています");
		}

		events.add(event);
	}
}
