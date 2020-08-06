package wooteco.subway.maps.map.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountTest {
    @Test
    @DisplayName("나이별 할인")
    void discount() {
        assertAll(
            () -> assertThat(Discount.CHILDREN.applyDiscount(1250)).isEqualTo(450),
            () -> assertThat(Discount.YOUTH.applyDiscount(1250)).isEqualTo(720)
        );
    }
}
