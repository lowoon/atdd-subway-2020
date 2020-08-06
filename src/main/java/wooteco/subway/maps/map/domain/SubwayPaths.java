package wooteco.subway.maps.map.domain;

import java.util.List;

import org.jgrapht.GraphPath;

public class SubwayPaths {
    public SubwayPaths(List<GraphPath> subwayPaths) {
        this.subwayPaths = subwayPaths;
    }

    private List<GraphPath> subwayPaths;

    public List<GraphPath> getSubwayPaths() {
        return subwayPaths;
    }
}
