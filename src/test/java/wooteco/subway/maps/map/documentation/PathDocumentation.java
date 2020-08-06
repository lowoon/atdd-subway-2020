package wooteco.subway.maps.map.documentation;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.web.context.WebApplicationContext;

import wooteco.subway.common.documentation.Documentation;
import wooteco.subway.maps.map.application.MapService;
import wooteco.subway.maps.map.dto.PathResponse;
import wooteco.subway.maps.map.ui.MapController;
import wooteco.subway.maps.station.dto.StationResponse;

@WebMvcTest(controllers = {MapController.class})
public class PathDocumentation extends Documentation {
    @Autowired
    private MapController mapController;
    @MockBean
    private MapService mapService;

    @BeforeEach
    public void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocumentation) {
        super.setUp(context, restDocumentation);
    }

    @Test
    void findDistancePath() {
        List<StationResponse> stations = Arrays.asList(
            new StationResponse(1L, "광교중앙역", LocalDateTime.now(), LocalDateTime.now()),
            new StationResponse(2L, "잠실역", LocalDateTime.now(), LocalDateTime.now()));
        PathResponse pathResponse = new PathResponse(stations, 10, 5);
        when(mapService.findPath(any(), any(), any())).thenReturn(pathResponse);

        given().log().all().
            contentType(MediaType.APPLICATION_JSON_VALUE).
            param("source", 1L).
            param("target", 2L).
            param("type", "DISTANCE").
            when().
            get("/paths").
            then().
            log().all().
            apply(document("paths/distance",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                    fieldWithPath("stations").type(JsonFieldType.ARRAY).description("역 목록"),
                    fieldWithPath("stations[0].id").type(JsonFieldType.NUMBER).description("시작역 id"),
                    fieldWithPath("stations[0].name").type(JsonFieldType.STRING).description("시작역 이름"),
                    fieldWithPath("stations[1].id").type(JsonFieldType.NUMBER).description("도착역 id"),
                    fieldWithPath("stations[1].name").type(JsonFieldType.STRING).description("도착역 이름"),
                    fieldWithPath("duration").type(JsonFieldType.NUMBER).description("시간"),
                    fieldWithPath("distance").type(JsonFieldType.NUMBER).description("거리")))).
            extract();
    }

    @Test
    void findDurationPath() {
        List<StationResponse> stations = Arrays.asList(
            new StationResponse(1L, "광교중앙역", LocalDateTime.now(), LocalDateTime.now()),
            new StationResponse(2L, "잠실역", LocalDateTime.now(), LocalDateTime.now()));
        PathResponse pathResponse = new PathResponse(stations, 5, 10);
        when(mapService.findPath(any(), any(), any())).thenReturn(pathResponse);

        given().log().all().
            contentType(MediaType.APPLICATION_JSON_VALUE).
            param("source", 1L).
            param("target", 2L).
            param("type", "DURATION").
            when().
            get("/paths").
            then().
            log().all().
            apply(document("paths/duration",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                    fieldWithPath("stations").type(JsonFieldType.ARRAY).description("역 목록"),
                    fieldWithPath("stations[0].id").type(JsonFieldType.NUMBER).description("시작역 id"),
                    fieldWithPath("stations[0].name").type(JsonFieldType.STRING).description("시작역 이름"),
                    fieldWithPath("stations[1].id").type(JsonFieldType.NUMBER).description("도착역 id"),
                    fieldWithPath("stations[1].name").type(JsonFieldType.STRING).description("도착역 이름"),
                    fieldWithPath("duration").type(JsonFieldType.NUMBER).description("시간"),
                    fieldWithPath("distance").type(JsonFieldType.NUMBER).description("거리")))).
            extract();
    }
}
