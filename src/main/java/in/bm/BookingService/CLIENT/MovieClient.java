package in.bm.BookingService.CLIENT;

import in.bm.BookingService.REQUESTDTO.InternalShowRequestDTO;
import in.bm.BookingService.RESPONSEDTO.InternalShowResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class MovieClient {
    private final WebClient.Builder webClientBuilder;

    public InternalShowResponse validateShow(InternalShowRequestDTO dto) {
        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8081/internal/shows/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(InternalShowResponse.class)
                .onErrorMap(e -> new RuntimeException("Show validation failed: " + e.getMessage(), e)).block();
    }
}
