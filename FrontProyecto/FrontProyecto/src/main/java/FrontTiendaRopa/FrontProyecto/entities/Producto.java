package FrontTiendaRopa.FrontProyecto.entities;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto {
    private Integer productoId;
    private String codigoBarra;
    private String nombre;
    private Integer categoriaId;
    private String talla;
    private String color;
    private Integer proveedorId;
    private Double precioVenta;
}
