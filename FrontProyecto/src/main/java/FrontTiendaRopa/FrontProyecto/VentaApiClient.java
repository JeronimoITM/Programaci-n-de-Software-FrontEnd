package FrontTiendaRopa.FrontProyecto.webservicesclient;

import FrontTiendaRopa.FrontProyecto.DTOs.VentaCreateDTO;
import FrontTiendaRopa.FrontProyecto.DTOs.VentaResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class VentaApiClient {

    private final RestTemplate restTemplate;

    @Value("${api.producto.base-url}")
    private String baseUrl;  // http://localhost:8090/api

    public VentaApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public VentaResponseDTO crearVenta(VentaCreateDTO dto) {
        String url = baseUrl + "/ventas/crear";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<VentaCreateDTO> req = new HttpEntity<>(dto, headers);
        ResponseEntity<VentaResponseDTO> resp =
                restTemplate.postForEntity(url, req, VentaResponseDTO.class);
        return resp.getBody();
    }

    public List<VentaResponseDTO> listarPorCliente(Integer clienteId) {
        String url = baseUrl + "/ventas/porCliente?clienteId=" + clienteId;
        ResponseEntity<VentaResponseDTO[]> resp =
                restTemplate.getForEntity(url, VentaResponseDTO[].class);
        if (resp.getBody() == null) return Collections.emptyList();
        return Arrays.asList(resp.getBody());
    }
}
