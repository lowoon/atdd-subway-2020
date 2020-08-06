package wooteco.subway.maps.map.domain;

import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Fare implements Comparable<Fare> {
    private int fare;

    public static Fare base() {
        return new Fare(1250);
    }

    public Fare() {
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

    public void setFare(int fare) {
        this.fare = fare;
    }

    @Override
    public int compareTo(Fare o) {
        return this.fare - o.fare;
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
