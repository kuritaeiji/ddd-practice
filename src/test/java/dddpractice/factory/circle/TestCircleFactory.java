package dddpractice.factory.circle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dddpractice.domain.circle.Circle;
import dddpractice.domain.circle.CircleName;
import dddpractice.domain.share.Version;

public class TestCircleFactory {

	private static Long ID_NUMBER = 0L;
	private static Integer NAME_NUMBER = 0;

	public static Circle create(Long id, String name, Long ownerId, List<Long> memberIds, Long version) {
		if (id == null) {
			ID_NUMBER++;
			id = ID_NUMBER;
		}

		if (name == null) {
			NAME_NUMBER++;
			name = "サークル名" + NAME_NUMBER;
		}

		if (ownerId == null) {
			ID_NUMBER++;
			ownerId = ID_NUMBER;
		}

		if (memberIds == null) {
			memberIds = new ArrayList<Long>(Arrays.asList(ID_NUMBER + 1, ID_NUMBER + 2));
			ID_NUMBER += 2;
		}

		if (version == null) {
			version = 1L;
		}

		return Circle.reconstruct(id, new CircleName(name), ownerId, memberIds, new Version(version));
	}
}
