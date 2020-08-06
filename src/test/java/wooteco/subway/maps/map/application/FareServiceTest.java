package wooteco.subway.maps.map.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.Lists;

import wooteco.subway.common.TestObjectUtils;
import wooteco.subway.maps.line.domain.Line;
import wooteco.subway.maps.line.domain.LineStation;
import wooteco.subway.maps.map.domain.Fare;
import wooteco.subway.maps.map.domain.SubwayPath;
import wooteco.subway.maps.station.domain.Station;

@ExtendWith(MockitoExtension.class)
class FareServiceTest {
    private List<Line> lines;
    private FareService fareService;
    @Mock
    private SubwayPath subwayPath;

    @BeforeEach
    void setUp() {
        Map<Long, Station> stations = new HashMap<>();
        stations.put(1L, TestObjectUtils.createStation(1L, "교대역"));
        stations.put(2L, TestObjectUtils.createStation(2L, "강남역"));
        stations.put(3L, TestObjectUtils.createStation(3L, "양재역"));
        stations.put(4L, TestObjectUtils.createStation(4L, "남부터미널역"));

        Line line1 = TestObjectUtils.createLine(1L, "2호선", "GREEN", 0);
        line1.addLineStation(new LineStation(1L, null, 0, 0));
        line1.addLineStation(new LineStation(2L, 1L, 2, 2));

        Line line2 = TestObjectUtils.createLine(2L, "신분당선", "RED", 0);
        line2.addLineStation(new LineStation(2L, null, 0, 0));
        line2.addLineStation(new LineStation(3L, 2L, 2, 1));

        Line line3 = TestObjectUtils.createLine(3L, "3호선", "ORANGE", 200);
        line3.addLineStation(new LineStation(1L, null, 0, 0));
        line3.addLineStation(new LineStation(4L, 1L, 5, 2));
        line3.addLineStation(new LineStation(3L, 4L, 15, 2));

        lines = Lists.newArrayList(line1, line2, line3);

        fareService = new FareService();
    }

    @Test
    void calculate() {
        // given
        given(subwayPath.calculateDistance()).willReturn(20);

        // when
        Fare fare = fareService.calculateFare(Collections.singletonList(lines.get(2)), subwayPath, null);

        // then
        assertThat(fare.getFare()).isEqualTo(1650);
    }
}
