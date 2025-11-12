package FrontTiendaRopa.FrontProyecto.DTOs;
import lombok.*;
import FrontTiendaRopa.FrontProyecto.entities.VentaItem;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VentaItemDTO {
    private Integer id;
    private Integer productoId;
    private String  productoNombre;
    private Integer cantidad;
    private Double  precioUnit;
    private Double  subtotal;   // en tu BD es columna calculada

    /** Mapper opcional desde la entidad (útil si conviertes en el front) */
    public static VentaItemDTO fromEntity(VentaItem it) {
        return VentaItemDTO.builder()
                .id(it.getId())
                .productoId(it.getProducto().getProductoId())
                .productoNombre(it.getProducto().getNombre())
                .cantidad(it.getCantidad())
                .precioUnit(it.getPrecioUnit())
                .subtotal(it.getSubtotal())
                .build();
    }
}