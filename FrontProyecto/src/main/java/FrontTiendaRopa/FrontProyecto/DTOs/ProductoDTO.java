package FrontTiendaRopa.FrontProyecto.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductoDTO {

    private Integer productoId;
    private String codigoBarra;
    private String nombre;
    private Integer categoriaId;
    private String talla;
    private String color;
    private Integer proveedorId;
    private Double precioVenta;

    public ProductoDTO() {}

    public ProductoDTO(Integer productoId, String codigoBarra, String nombre,
                       Integer categoriaId, String talla, String color,
                       Integer proveedorId, Double precioVenta) {
        this.productoId = productoId;
        this.codigoBarra = codigoBarra;
        this.nombre = nombre;
        this.categoriaId = categoriaId;
        this.talla = talla;
        this.color = color;
        this.proveedorId = proveedorId;
        this.precioVenta = precioVenta;
    }

    public Integer getProductoId() { return productoId; }
    public void setProductoId(Integer productoId) { this.productoId = productoId; }

    public String getCodigoBarra() { return codigoBarra; }
    public void setCodigoBarra(String codigoBarra) { this.codigoBarra = codigoBarra; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }

    public String getTalla() { return talla; }
    public void setTalla(String talla) { this.talla = talla; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public Integer getProveedorId() { return proveedorId; }
    public void setProveedorId(Integer proveedorId) { this.proveedorId = proveedorId; }

    public Double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(Double precioVenta) { this.precioVenta = precioVenta; }
}
