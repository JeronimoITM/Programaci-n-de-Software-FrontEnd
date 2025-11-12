package FrontTiendaRopa.FrontProyecto.entities;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Venta {
    private Integer id;
    private Cliente cliente;
    private LocalDateTime fechaVenta;
    private Double total;
    private List<VentaItem> items = new ArrayList<>();


}
