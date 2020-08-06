package wooteco.subway.maps.map.application;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import wooteco.subway.maps.line.domain.Line;
import wooteco.subway.maps.map.domain.Discount;
import wooteco.subway.maps.map.domain.Fare;
import wooteco.subway.maps.map.domain.SubwayPath;
import wooteco.subway.members.member.domain.LoginMember;

@Service
public class FareService {
    public Fare calculateFare(List<Line> lines, SubwayPath subwayPath, LoginMember loginMember) {
        Fare extraFare = lines.stream().map(Line::getExtraFare)
            .max(Comparator.naturalOrder())
            .orElseGet(() -> new Fare(0));
        Fare fare = Fare.base()
            .calculateFareByDistance(subwayPath.calculateDistance())
            .calculateExtraFare(extraFare);
        if (Objects.nonNull(loginMember)) {
            fare = applyDiscountIfSubject(fare, loginMember);
        }
        return fare;
    }

    private Fare applyDiscountIfSubject(Fare fare, LoginMember loginMember) {
        Fare discounted = fare;
        if (loginMember.isChildren()) {
            discounted = fare.applyDiscount(Discount.CHILDREN);
        }
        if (loginMember.isYouth()) {
            discounted = fare.applyDiscount(Discount.YOUTH);
        }
        return discounted;
    }
}
