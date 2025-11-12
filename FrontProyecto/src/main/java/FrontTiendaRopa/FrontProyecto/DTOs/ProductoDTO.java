package FrontTiendaRopa.FrontProyecto.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    private Integer productoId;
    private String codigoBarra;
    private String nombre;
    private Integer categoriaId;
    private String talla;
    private String color;
    private Integer proveedorId;
    private Double precioVenta;
}
