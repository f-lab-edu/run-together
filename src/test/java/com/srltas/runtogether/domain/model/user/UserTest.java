package com.srltas.runtogether.domain.model.user;

import static com.srltas.runtogether.testutil.TestIdGenerator.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.srltas.runtogether.domain.model.neighborhood.Location;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.domain.model.neighborhood.exception.NeighborhoodDuplicationException;
import com.srltas.runtogether.domain.model.neighborhood.exception.NeighborhoodLimitExceededException;
import com.srltas.runtogether.domain.model.neighborhood.exception.NeighborhoodNotRegisteredException;

class UserTest {

	private final String neighborhoodId1 = generateNeighborhoodId();
	private final User user = new User(generateUserId(), "TestUser", new HashMap<>());
	private final Neighborhood neighborhood1 = new Neighborhood(neighborhoodId1, "Gangnam",
		new Location(37.4979, 127.0276), 5.0);

	@Test
	@DisplayName("성공적으로 동네를 등록한다")
	void testAddNeighborhoodSuccess() {
		user.addNeighborhood(neighborhood1);
		assertThat(user.getUserNeighborhoods().size(), is(1));
	}

	@Test
	@DisplayName("성공적으로 내 동네를 삭제한다")
	void testDeleteUserNeighborhoodSuccess() {
		user.addNeighborhood(neighborhood1);

		user.deleteNeighborhood(neighborhoodId1);

		assertThat(user.getUserNeighborhoods().size(), is(0));
	}

	@Test
	@DisplayName("동네 인증이 정상적으로 동작한다")
	void testVerifyNeighborhoodSuccess() {
		user.addNeighborhood(neighborhood1);

		user.verifiedNeighborhood(neighborhoodId1);

		UserNeighborhood userNeighborhood = user.getUserNeighborhoods().get(neighborhoodId1);
		assertThat(userNeighborhood.isVerified(), is(true));
		assertThat(userNeighborhood.getVerifiedAt(), is(notNullValue()));
	}

	@Test
	@DisplayName("최대 2개의 동네만 등록할 수 있다")
	void testAddNeighborhoodExceedsLimit() {
		Neighborhood neighborhood2 = new Neighborhood(generateNeighborhoodId(), "Seocho",
			new Location(37.4839, 127.0323), 5.0);
		Neighborhood neighborhood3 = new Neighborhood(generateNeighborhoodId(), "Mapo",
			new Location(37.5547, 126.9707), 5.0);


		user.addNeighborhood(neighborhood1);
		user.addNeighborhood(neighborhood2);

		NeighborhoodLimitExceededException exception = assertThrows(
			NeighborhoodLimitExceededException.class, () -> {
				user.addNeighborhood(neighborhood3);
			});

		assertThat(exception.getErrorCode().getCode(), is(-351));
		assertThat(exception.getMessage(), is("내 동네는 최대 2개만 등록할 수 있습니다."));
	}

	@Test
	@DisplayName("동일한 동네를 중복 등록할 수 없다")
	void testAddNeighborhoodDuplicate() {
		user.addNeighborhood(neighborhood1);

		NeighborhoodDuplicationException exception = assertThrows(
			NeighborhoodDuplicationException.class, () -> {
				user.addNeighborhood(neighborhood1);
			});

		assertThat(exception.getErrorCode().getCode(), is(-352));
		assertThat(exception.getMessage(), is("이미 내 동네로 등록된 동네입니다."));
	}

	@Test
	@DisplayName("동네가 등록되지 않은 경우 인증 시 에러가 발생한다")
	void testVerifyNeighborhoodNotRegistered() {
		NeighborhoodNotRegisteredException exception = assertThrows(
			NeighborhoodNotRegisteredException.class, () -> {
				user.verifiedNeighborhood(generateNeighborhoodId());
			});

		assertThat(exception.getErrorCode().getCode(), is(-353));
		assertThat(exception.getMessage(), is("내 동네로 등록되지 않은 동네입니다."));
	}
}
