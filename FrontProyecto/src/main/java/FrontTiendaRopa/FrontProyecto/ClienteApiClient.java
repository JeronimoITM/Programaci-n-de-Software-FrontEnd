package FrontTiendaRopa.FrontProyecto.webservicesclient;

import FrontTiendaRopa.FrontProyecto.DTOs.ClienteDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ClienteApiClient {

    private final RestTemplate restTemplate;

    @Value("${api.producto.base-url}")
    private String baseUrl;  // http://localhost:8090/api

    public ClienteApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // LISTAR
    public List<ClienteDTO> getClientes() {
        String url = baseUrl + "/clientes/listar";
        ResponseEntity<ClienteDTO[]> resp =
                restTemplate.getForEntity(url, ClienteDTO[].class);
        if (resp.getBody() == null) return Collections.emptyList();
        return Arrays.asList(resp.getBody());
    }

    // POR ID
    public ClienteDTO getClienteById(Integer id) {
        String url = baseUrl + "/clientes/listarId?clienteId=" + id;
        return restTemplate.getForObject(url, ClienteDTO.class);
    }

    // CREAR
    public void crearCliente(ClienteDTO cliente) {
        String url = baseUrl + "/clientes/insertar";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ClienteDTO> req = new HttpEntity<>(cliente, headers);
        restTemplate.postForEntity(url, req, String.class);
    }

    // ACTUALIZAR
    public void actualizarCliente(ClienteDTO cliente) {
        String url = baseUrl + "/clientes/actualizar";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ClienteDTO> req = new HttpEntity<>(cliente, headers);
        restTemplate.exchange(url, HttpMethod.PUT, req, String.class);
    }

    // ELIMINAR
    public void eliminarCliente(Integer id) {
        String url = baseUrl + "/clientes/eliminar/" + id;
        restTemplate.delete(url);
    }
}
