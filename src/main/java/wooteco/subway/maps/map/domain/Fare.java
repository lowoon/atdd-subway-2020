package wooteco.subway.maps.map.domain;

import java.util.Objects;

public class Fare {
    private final int fare;

    public static Fare base() {
        return new Fare(1250);
    }

    public Fare(int fare) {
        this.fare = fare;
    }

    public Fare calculateFareByDistance(int distance) {
        if (distance <= 10) {
            return new Fare(fare);
        }
        if (distance <= 50) {
            return new Fare(fare + ((distance - 10) / 5) * 100);
        }
        return new Fare(fare + 800 + ((distance - 50) / 8) * 100);
    }

    public Fare calculateExtraFare(Fare extraFare) {
        return new Fare(fare + extraFare.fare);
    }

    public int getFare() {
        return fare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Fare fare1 = (Fare)o;
        return fare == fare1.fare;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fare);
    }
}
