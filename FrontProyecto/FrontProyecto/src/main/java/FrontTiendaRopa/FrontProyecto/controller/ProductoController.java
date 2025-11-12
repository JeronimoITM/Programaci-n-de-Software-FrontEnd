package FrontTiendaRopa.FrontProyecto.controller;

import FrontTiendaRopa.FrontProyecto.DTOs.ProductoDTO;
import FrontTiendaRopa.FrontProyecto.webservicesclient.ProductoApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/front/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoApiClient productoApiClient;

    // ----------- LISTAR TODOS -----------
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarProductos() {
        List<ProductoDTO> productos = productoApiClient.getProductos();
        if (productos == null || productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    // ----------- BUSCAR POR ID -----------
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable("id") Integer id) {
        ProductoDTO producto = productoApiClient.getProductoById(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    // ----------- CREAR PRODUCTO -----------
    @PostMapping
    public ResponseEntity<String> crearProducto(@RequestBody ProductoDTO producto) {
        productoApiClient.crearProducto(producto);
        return ResponseEntity.ok("Producto creado correctamente");
    }

    // ----------- ACTUALIZAR PRODUCTO -----------
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarProducto(@PathVariable("id") Integer id,
                                                     @RequestBody ProductoDTO producto) {
        // Asignamos el ID recibido al DTO
        producto.setProductoId(id);
        productoApiClient.actualizarProducto(producto);
        return ResponseEntity.ok("Producto actualizado correctamente");
    }

    // ----------- ELIMINAR PRODUCTO -----------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable("id") Integer id) {
        productoApiClient.eliminarProducto(id);
        return ResponseEntity.ok("Producto eliminado correctamente");
    }
}
