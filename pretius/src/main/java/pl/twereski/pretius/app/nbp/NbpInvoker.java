package pl.twereski.pretius.app.nbp;


import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.twereski.pretius.app.nbp.dto.NbpResponse;

import java.net.URI;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Service
public class NbpInvoker {

    public List<NbpResponse> getCurrenciesRate(String url) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<NbpResponse>> responseA = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<NbpResponse>>() {
                });

        return responseA.getBody();

    }
}
