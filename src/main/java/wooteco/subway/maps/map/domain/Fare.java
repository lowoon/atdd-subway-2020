package wooteco.subway.maps.map.domain;

import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Fare implements Comparable<Fare> {
    public static final int BASE_FARE = 1250;
    public static final int FIRST_FARE_CRITERIA = 10;
    public static final int SECOND_FARE_CRITERIA = 50;
    public static final int FIRST_BILLING_CRITERIA = 5;
    public static final int BILLING_AMOUNT = 100;
    public static final int SECOND_BILLING_CRITERIA = 8;
    public static final int SECOND_DEFAULT_BILLING = 800;

    private int fare;

    public static Fare base() {
        return new Fare(BASE_FARE);
    }

    public Fare() {
    }

    public Fare(int fare) {
        this.fare = fare;
    }

    public Fare calculateFareByDistance(int distance) {
        if (distance <= FIRST_FARE_CRITERIA) {
            return new Fare(fare);
        }
        if (distance <= SECOND_FARE_CRITERIA) {
            return new Fare(fare + ((distance - FIRST_FARE_CRITERIA) / FIRST_BILLING_CRITERIA) * BILLING_AMOUNT);
        }
        return new Fare(fare + SECOND_DEFAULT_BILLING
            + ((distance - SECOND_FARE_CRITERIA) / SECOND_BILLING_CRITERIA) * BILLING_AMOUNT);
    }

    public Fare calculateExtraFare(Fare extraFare) {
        return new Fare(fare + extraFare.fare);
    }

    public Fare applyDiscount(Discount discount) {
        return new Fare(discount.applyDiscount(fare));
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
