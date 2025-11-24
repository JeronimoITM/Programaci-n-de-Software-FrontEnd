package FrontTiendaRopa.FrontProyecto.DTOs;

import java.time.LocalDate;

public record VentaCreateDTO(
        Integer clienteId,
        Integer productoId,
        Integer cantidad,
        LocalDate fechaVenta
) {}
