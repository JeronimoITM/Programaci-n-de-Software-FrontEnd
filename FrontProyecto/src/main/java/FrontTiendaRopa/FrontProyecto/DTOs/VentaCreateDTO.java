package FrontTiendaRopa.FrontProyecto.DTOs;

public record VentaCreateDTO(
        Integer clienteId,
        Integer productoId,
        Integer cantidad
) {}
