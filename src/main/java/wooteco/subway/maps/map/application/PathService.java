package wooteco.subway.maps.map.application;

import java.util.List;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.shortestpath.KShortestPaths;
import org.springframework.stereotype.Service;

import wooteco.subway.maps.line.domain.Line;
import wooteco.subway.maps.map.domain.LineStationEdge;
import wooteco.subway.maps.map.domain.PathType;
import wooteco.subway.maps.map.domain.SubwayGraph;
import wooteco.subway.maps.map.domain.SubwayGraphWithOutWeight;
import wooteco.subway.maps.map.domain.SubwayPath;
import wooteco.subway.maps.map.domain.SubwayPaths;

@Service
public class PathService {
    public SubwayPath findPath(List<Line> lines, Long source, Long target, PathType type) {
        SubwayGraph graph = new SubwayGraph(LineStationEdge.class);
        graph.addVertexWith(lines);
        graph.addEdge(lines, type);

        // 다익스트라 최단 경로 찾기
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
        GraphPath<Long, LineStationEdge> path = dijkstraShortestPath.getPath(source, target);

        return convertSubwayPath(path);
    }

    private SubwayPath convertSubwayPath(GraphPath graphPath) {
        return new SubwayPath((List<LineStationEdge>)graphPath.getEdgeList().stream().collect(Collectors.toList()));
    }

    public SubwayPaths findAllPath(List<Line> lines, Long source, Long target) {
        SubwayGraphWithOutWeight graph = new SubwayGraphWithOutWeight(LineStationEdge.class);
        graph.addVertexWith(lines);
        graph.addEdge(lines);

        return new SubwayPaths((List<GraphPath>)new KShortestPaths(graph, 1000).getPaths(source, target));
    }
}

