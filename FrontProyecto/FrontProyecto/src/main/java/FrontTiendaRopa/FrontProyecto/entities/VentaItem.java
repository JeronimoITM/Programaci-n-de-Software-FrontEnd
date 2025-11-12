package FrontTiendaRopa.FrontProyecto.entities;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentaItem {

    private Integer id;
    private Venta venta;
    private Producto producto;
    private Integer cantidad;
    private Double precioUnit;
    private Double subtotal;
}
