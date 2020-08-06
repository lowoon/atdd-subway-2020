package wooteco.subway.maps.map.ui;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import wooteco.subway.maps.map.application.MapService;
import wooteco.subway.maps.map.domain.PathType;
import wooteco.subway.maps.map.dto.PathResponse;

@ExtendWith(MockitoExtension.class)
public class MapControllerTest {
    @Mock
    private MapService mapService;

    @Test
    void findPath() {
        MapController controller = new MapController(mapService);
        when(mapService.findPath(eq(1L), eq(2L), eq(PathType.DISTANCE), eq(null))).thenReturn(
            new PathResponse());

        ResponseEntity<PathResponse> entity = controller.findPath(1L, 2L, PathType.DISTANCE, null);

        assertThat(entity.getBody()).isNotNull();
    }

    @Test
    void findMap() {
        MapController controller = new MapController(mapService);

        ResponseEntity entity = controller.findMap();

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(mapService).findMap();
    }
}
