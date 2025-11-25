package FrontTiendaRopa.FrontProyecto.controller;

import FrontTiendaRopa.FrontProyecto.DTOs.ClienteDTO;
import FrontTiendaRopa.FrontProyecto.webservicesclient.ClienteApiClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/front/clientes")
public class ClienteController {

    private final ClienteApiClient clienteApiClient;

    public ClienteController(ClienteApiClient clienteApiClient) {
        this.clienteApiClient = clienteApiClient;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {
        return ResponseEntity.ok(clienteApiClient.getClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> porId(@PathVariable Integer id) {
        return ResponseEntity.ok(clienteApiClient.getClienteById(id));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> crear(@RequestBody ClienteDTO dto) {
        clienteApiClient.crearCliente(dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable Integer id,
                                           @RequestBody ClienteDTO dto) {
        dto.setIdCliente(id);
        clienteApiClient.actualizarCliente(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        clienteApiClient.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
