package wooteco.subway.maps.map.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FareTest {
    @Test
    @DisplayName("거리별 요금 계산")
    void calculateFareBasedOnDistance() {
        Fare fare = new Fare(1250);

        assertAll(
            () -> assertThat(fare.calculateFareByDistance(5).getFare()).isEqualTo(1250),
            () -> assertThat(fare.calculateFareByDistance(20).getFare()).isEqualTo(1450),
            () -> assertThat(fare.calculateFareByDistance(59).getFare()).isEqualTo(2150)
        );
    }
}
