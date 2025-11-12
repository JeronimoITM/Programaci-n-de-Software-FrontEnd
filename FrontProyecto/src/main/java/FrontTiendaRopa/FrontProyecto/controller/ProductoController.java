package FrontTiendaRopa.FrontProyecto.controller;

import FrontTiendaRopa.FrontProyecto.DTOs.ProductoDTO;
import FrontTiendaRopa.FrontProyecto.webservicesclient.ProductoApiClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/front/productos")
public class ProductoController {

    private final ProductoApiClient productoApiClient;

    public ProductoController(ProductoApiClient productoApiClient) {
        this.productoApiClient = productoApiClient;
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listar() {
        return ResponseEntity.ok(productoApiClient.getProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> porId(@PathVariable Integer id) {
        return ResponseEntity.ok(productoApiClient.getProductoById(id));
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@RequestBody ProductoDTO dto) {
        return ResponseEntity.ok(productoApiClient.crearProducto(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable Integer id, @RequestBody ProductoDTO dto) {
        productoApiClient.actualizarProducto(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        productoApiClient.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
