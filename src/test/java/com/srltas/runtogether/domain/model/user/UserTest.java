package com.srltas.runtogether.domain.model.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.srltas.runtogether.domain.exception.CommonException;
import com.srltas.runtogether.domain.model.neighborhood.Location;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;

class UserTest {

	private final User user = new User(1L, "TestUser");
	private final Neighborhood neighborhood1 = new Neighborhood(101, "Gangnam",
		new Location(37.4979, 127.0276), 5.0);

	@Test
	@DisplayName("성공적으로 동네를 등록한다")
	void testAddNeighborhoodSuccess() {
		// when
		user.addNeighborhood(neighborhood1);

		// then
		assertThat(user.getUserNeighborhoods().size()).isEqualTo(1);
	}

	@Test
	@DisplayName("동네 인증이 정상적으로 동작한다")
	void testVerifyNeighborhoodSuccess() {
		// given
		user.addNeighborhood(neighborhood1);

		// when
		user.verifiedNeighborhood(101);

		// then
		UserNeighborhood userNeighborhood = user.getUserNeighborhoods().stream().findFirst()
			.orElseThrow(() -> new CommonException("동네가 등록되지 않았습니다."));
		assertThat(userNeighborhood.isVerified()).isTrue();
		assertThat(userNeighborhood.getVerifiedAt()).isNotNull();
	}

	@Test
	@DisplayName("최대 2개의 동네만 등록할 수 있다")
	void testAddNeighborhoodExceedsLimit() {
		// given
		Neighborhood neighborhood2 = new Neighborhood(102, "Seocho",
			new Location(37.4839, 127.0323), 5.0);
		Neighborhood neighborhood3 = new Neighborhood(103, "Mapo",
			new Location(37.5547, 126.9707), 5.0);

		// when
		user.addNeighborhood(neighborhood1);
		user.addNeighborhood(neighborhood2);

		// then
		assertThatThrownBy(() -> user.addNeighborhood(neighborhood3))
			.isInstanceOf(CommonException.class)
			.hasMessageContaining("최대 2개의 동네만 등록할 수 있습니다.");
	}

	@Test
	@DisplayName("동일한 동네를 중복 등록할 수 없다")
	void testAddNeighborhoodDuplicate() {
		// given
		user.addNeighborhood(neighborhood1);

		// when & then
		assertThatThrownBy(() -> user.addNeighborhood(neighborhood1))
			.isInstanceOf(CommonException.class)
			.hasMessageContaining("이미 등록된 동네입니다.");
	}

	@Test
	@DisplayName("동네가 등록되지 않은 경우 인증 시 에러가 발생한다")
	void testVerifyNeighborhoodNotRegistered() {
		// when & then
		assertThatThrownBy(() -> user.verifiedNeighborhood(101))
			.isInstanceOf(CommonException.class)
			.hasMessageContaining("해당 동네가 등록되지 않았습니다.");
	}
}
