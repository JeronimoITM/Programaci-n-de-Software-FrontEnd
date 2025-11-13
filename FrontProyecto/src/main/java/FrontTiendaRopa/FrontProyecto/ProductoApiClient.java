package FrontTiendaRopa.FrontProyecto.webservicesclient;

import FrontTiendaRopa.FrontProyecto.DTOs.ProductoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ProductoApiClient {

    private final RestTemplate restTemplate;

    @Value("${api.producto.base-url}")
    private String baseUrl; // p.ej. http://localhost:8090/api

    public ProductoApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // ==== LISTAR ====
    public List<ProductoDTO> getProductos() {
        String url = baseUrl + "/productos";
        ResponseEntity<ProductoDTO[]> resp = restTemplate.getForEntity(url, ProductoDTO[].class);
        if (resp.getBody() == null) return Collections.emptyList();
        return Arrays.asList(resp.getBody());
    }

    // ==== OBTENER POR ID ====
    public ProductoDTO getProductoById(Integer id) {
        String url = baseUrl + "/productos/" + id;
        return restTemplate.getForObject(url, ProductoDTO.class);
    }

    // ==== CREAR ====
    public ProductoDTO crearProducto(ProductoDTO producto) {
        String url = baseUrl + "/productos";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProductoDTO> request = new HttpEntity<>(producto, headers);
        return restTemplate.postForObject(url, request, ProductoDTO.class);
    }

    // ==== ACTUALIZAR ====
    public void actualizarProducto(Integer id, ProductoDTO producto) {
        String url = baseUrl + "/productos/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProductoDTO> request = new HttpEntity<>(producto, headers);
        restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);
    }

    // ==== ELIMINAR ====
    public void eliminarProducto(Integer id) {
        String url = baseUrl + "/productos/" + id;
        restTemplate.delete(url);
    }
}
