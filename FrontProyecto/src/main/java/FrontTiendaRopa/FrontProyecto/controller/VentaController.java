package FrontTiendaRopa.FrontProyecto.controller;

import FrontTiendaRopa.FrontProyecto.DTOs.VentaCreateDTO;
import FrontTiendaRopa.FrontProyecto.DTOs.VentaResponseDTO;
import FrontTiendaRopa.FrontProyecto.webservicesclient.VentaApiClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/front/ventas")
public class VentaController {

    private final VentaApiClient ventaApiClient;

    public VentaController(VentaApiClient ventaApiClient) {
        this.ventaApiClient = ventaApiClient;
    }

    @PostMapping
    public ResponseEntity<VentaResponseDTO> crear(@RequestBody VentaCreateDTO dto) {
        return ResponseEntity.ok(ventaApiClient.crearVenta(dto));
    }

    @GetMapping("/porCliente/{clienteId}")
    public ResponseEntity<List<VentaResponseDTO>> porCliente(@PathVariable Integer clienteId) {
        return ResponseEntity.ok(ventaApiClient.listarPorCliente(clienteId));
    }
}
