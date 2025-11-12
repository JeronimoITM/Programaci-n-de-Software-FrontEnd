package FrontTiendaRopa.FrontProyecto.webservicesclient;

import FrontTiendaRopa.FrontProyecto.DTOs.ProductoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductoApiClient {

    private final RestTemplate restTemplate;

    @Value("${api.producto.base-url:http://localhost:8090/api}")
    private String baseUrl;

    public ProductoApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Listar todos los productos
    public List<ProductoDTO> getProductos() {
        String url = baseUrl + "/productos/listar";
        ProductoDTO[] productosArray = restTemplate.getForObject(url, ProductoDTO[].class);
        return Arrays.asList(productosArray);
    }

    // Obtener producto por ID
    public ProductoDTO getProductoById(Integer id) {
        String url = baseUrl + "/productos/listarId?productoId=" + id;
        return restTemplate.getForObject(url, ProductoDTO.class);
    }

    // Crear producto
    public void crearProducto(ProductoDTO producto) {
        String url = baseUrl + "/productos/insertar";
        restTemplate.postForObject(url, producto, Void.class);
    }

    // Actualizar producto
    public void actualizarProducto(ProductoDTO producto) {
        String url = baseUrl + "/productos/actualizar";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProductoDTO> request = new HttpEntity<>(producto, headers);
        restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);
    }

    // Eliminar producto
    public void eliminarProducto(Integer id) {
        String url = baseUrl + "/productos/eliminar/" + id;
        restTemplate.delete(url);
    }
}
