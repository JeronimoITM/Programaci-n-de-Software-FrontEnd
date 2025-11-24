package FrontTiendaRopa.FrontProyecto.DTOs;

import java.time.LocalDateTime;

public record VentaResponseDTO(
        Integer id,
        Integer clienteId,
        String  clienteNombre,
        Integer productoId,
        String  productoNombre,
        Integer cantidad,
        LocalDateTime fechaCompra,
        Double precioUnitario,
        Double total
) {}
