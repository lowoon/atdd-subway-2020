package wooteco.subway.maps.map.domain;

import java.util.function.Function;

public enum Discount {
    CHILDREN(fare -> (int)((fare - 350) * 0.5)),
    YOUTH(fare -> (int)((fare - 350) * 0.8));

    private final Function<Integer, Integer> discount;

    Discount(Function<Integer, Integer> discount) {
        this.discount = discount;
    }

    public int applyDiscount(int fare) {
        return discount.apply(fare);
    }
}
