package FrontTiendaRopa.FrontProyecto.window;

import FrontTiendaRopa.FrontProyecto.DTOs.ProductoDTO;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProductoTableModel extends AbstractTableModel {

    private List<ProductoDTO> productos = new ArrayList<>();
    private final String[] columnas = {"ID", "Código Barra", "Nombre", "Precio Venta"};

    @Override
    public int getRowCount() {
        return productos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProductoDTO p = productos.get(rowIndex);
        switch (columnIndex) {
            case 0: return p.getProductoId();
            case 1: return p.getCodigoBarra();
            case 2: return p.getNombre();
            case 3: return p.getPrecioVenta();
            default: return null;
        }
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
        fireTableDataChanged();
    }

    public ProductoDTO getProductoAt(int rowIndex) {
        return productos.get(rowIndex);
    }
}
