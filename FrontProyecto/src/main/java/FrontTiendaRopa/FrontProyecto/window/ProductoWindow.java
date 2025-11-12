package FrontTiendaRopa.FrontProyecto.window;

import FrontTiendaRopa.FrontProyecto.DTOs.ProductoDTO;
import FrontTiendaRopa.FrontProyecto.webservicesclient.ProductoApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductoWindow extends JFrame {

    private final ProductoApiClient productoApiClient;

    // Tabla y modelo
    private JTable tableProductos;
    private ProductoTableModel tableModel;

    // Campos de formulario
    private final JTextField txtId = new JTextField(6);
    private final JTextField txtCodigoBarra = new JTextField(12);
    private final JTextField txtNombre = new JTextField(16);
    private final JTextField txtCategoriaId = new JTextField(6);
    private final JTextField txtTalla = new JTextField(8);
    private final JTextField txtColor = new JTextField(10);
    private final JTextField txtProveedorId = new JTextField(6);
    private final JTextField txtPrecioVenta = new JTextField(10);

    // Botones
    private final JButton btnCrear = new JButton("Crear");
    private final JButton btnActualizar = new JButton("Actualizar");
    private final JButton btnEliminar = new JButton("Eliminar");
    private final JButton btnLimpiar = new JButton("Limpiar");

    @Autowired
    public ProductoWindow(ProductoApiClient productoApiClient) {
        this.productoApiClient = productoApiClient;
        setTitle("Gestión de Productos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 520);
        setLocationRelativeTo(null);
    }

    /** Llamar esto desde tu clase Application (CommandLineRunner) */
    public void initializeUI() {
        // Modelo/tabla
        tableModel = new ProductoTableModel();
        tableProductos = new JTable(tableModel);
        tableProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableProductos.getSelectionModel().addListSelectionListener(new RowSelectionListener());

        JScrollPane spTabla = new JScrollPane(tableProductos);
        spTabla.setBorder(BorderFactory.createTitledBorder("Productos"));

        // Panel de formulario (arriba)
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Formulario"));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 6, 4, 6);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;

        int col = 0;
        addField(form, c, col++, "ID:", txtId);
        addField(form, c, col++, "Código:", txtCodigoBarra);
        addField(form, c, col++, "Nombre:", txtNombre);
        c.gridy++;
        col = 0;
        addField(form, c, col++, "CategoríaId:", txtCategoriaId);
        addField(form, c, col++, "Talla:", txtTalla);
        addField(form, c, col++, "Color:", txtColor);
        c.gridy++;
        col = 0;
        addField(form, c, col++, "ProveedorId:", txtProveedorId);
        addField(form, c, col++, "Precio Venta:", txtPrecioVenta);

        txtId.setEditable(false); // ID lo maneja la BD

        // Panel de botones (abajo)
        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        acciones.add(btnCrear);
        acciones.add(btnActualizar);
        acciones.add(btnEliminar);
        acciones.add(btnLimpiar);

        // Layout principal
        setLayout(new BorderLayout(10, 10));
        add(form, BorderLayout.NORTH);
        add(spTabla, BorderLayout.CENTER);
        add(acciones, BorderLayout.SOUTH);

        // Listeners
        btnCrear.addActionListener(e -> crearProducto());
        btnActualizar.addActionListener(e -> actualizarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        // Cargar datos
        cargarProductos();

        setVisible(true);
    }

    private void addField(JPanel panel, GridBagConstraints c, int gridx, String label, JComponent field) {
        c.gridx = gridx * 2;
        panel.add(new JLabel(label), c);
        c.gridx = gridx * 2 + 1;
        panel.add(field, c);
    }

    /* ===================== LÓGICA ===================== */

    private void cargarProductos() {
        try {
            List<ProductoDTO> productos = productoApiClient.getProductos();
            tableModel.setProductos(productos);
        } catch (Exception ex) {
            showError("Error al cargar productos: " + ex.getMessage());
        }
    }

    private void crearProducto() {
        try {
            ProductoDTO dto = construirDesdeFormulario(false);
            ProductoDTO creado = productoApiClient.crearProducto(dto);
            cargarProductos();
            limpiarCampos();
            showInfo("Producto creado. ID: " + (creado != null ? creado.getProductoId() : "(ver tabla)"));
        } catch (Exception ex) {
            showError("Error al crear: " + ex.getMessage());
        }
    }

    private void actualizarProducto() {
        int fila = tableProductos.getSelectedRow();
        if (fila == -1) {
            showWarn("Seleccione un producto en la tabla.");
            return;
        }
        ProductoDTO seleccionado = tableModel.getProductoAt(fila);
        if (seleccionado.getProductoId() == null) {
            showWarn("El producto seleccionado no tiene ID.");
            return;
        }
        try {
            ProductoDTO dto = construirDesdeFormulario(true);
            productoApiClient.actualizarProducto(seleccionado.getProductoId(), dto);
            cargarProductos();
            showInfo("Producto actualizado.");
        } catch (Exception ex) {
            showError("Error al actualizar: " + ex.getMessage());
        }
    }

    private void eliminarProducto() {
        int fila = tableProductos.getSelectedRow();
        if (fila == -1) {
            showWarn("Seleccione un producto en la tabla.");
            return;
        }
        ProductoDTO seleccionado = tableModel.getProductoAt(fila);
        if (seleccionado.getProductoId() == null) {
            showWarn("El producto seleccionado no tiene ID.");
            return;
        }
        int opt = JOptionPane.showConfirmDialog(this,
                "¿Eliminar el producto ID " + seleccionado.getProductoId() + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (opt != JOptionPane.YES_OPTION) return;

        try {
            productoApiClient.eliminarProducto(seleccionado.getProductoId());
            cargarProductos();
            limpiarCampos();
            showInfo("Producto eliminado.");
        } catch (Exception ex) {
            showError("Error al eliminar: " + ex.getMessage());
        }
    }

    private ProductoDTO construirDesdeFormulario(boolean paraActualizar) {
        // Validaciones básicas
        String nombre = txtNombre.getText().trim();
        String precioTxt = txtPrecioVenta.getText().trim();
        if (nombre.isEmpty()) throw new IllegalArgumentException("Nombre es obligatorio.");
        if (precioTxt.isEmpty()) throw new IllegalArgumentException("Precio Venta es obligatorio.");

        Double precioVenta;
        try { precioVenta = Double.parseDouble(precioTxt); }
        catch (NumberFormatException e) { throw new IllegalArgumentException("Precio Venta inválido."); }

        Integer id = parseIntNullable(txtId.getText().trim());
        Integer categoriaId = parseIntNullable(txtCategoriaId.getText().trim());
        Integer proveedorId = parseIntNullable(txtProveedorId.getText().trim());

        ProductoDTO dto = new ProductoDTO();
        if (paraActualizar) dto.setProductoId(id);
        dto.setCodigoBarra(emptyToNull(txtCodigoBarra.getText()));
        dto.setNombre(nombre);
        dto.setCategoriaId(categoriaId);
        dto.setTalla(emptyToNull(txtTalla.getText()));
        dto.setColor(emptyToNull(txtColor.getText()));
        dto.setProveedorId(proveedorId);
        dto.setPrecioVenta(precioVenta);
        return dto;
    }

    private Integer parseIntNullable(String s) {
        if (s == null || s.isEmpty()) return null;
        try { return Integer.parseInt(s); }
        catch (NumberFormatException e) { throw new IllegalArgumentException("Valor numérico inválido: " + s); }
    }

    private String emptyToNull(String s) {
        if (s == null) return null;
        String t = s.trim();
        return t.isEmpty() ? null : t;
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtCodigoBarra.setText("");
        txtNombre.setText("");
        txtCategoriaId.setText("");
        txtTalla.setText("");
        txtColor.setText("");
        txtProveedorId.setText("");
        txtPrecioVenta.setText("");
        tableProductos.clearSelection();
    }

    private void showInfo(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showWarn(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /** Listener que rellena el formulario con la fila seleccionada */
    private class RowSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            int fila = tableProductos.getSelectedRow();
            if (fila < 0) return;
            ProductoDTO p = tableModel.getProductoAt(fila);
            if (p == null) return;

            txtId.setText(p.getProductoId() == null ? "" : String.valueOf(p.getProductoId()));
            txtCodigoBarra.setText(p.getCodigoBarra() == null ? "" : p.getCodigoBarra());
            txtNombre.setText(p.getNombre() == null ? "" : p.getNombre());
            txtCategoriaId.setText(p.getCategoriaId() == null ? "" : String.valueOf(p.getCategoriaId()));
            txtTalla.setText(p.getTalla() == null ? "" : p.getTalla());
            txtColor.setText(p.getColor() == null ? "" : p.getColor());
            txtProveedorId.setText(p.getProveedorId() == null ? "" : String.valueOf(p.getProveedorId()));
            txtPrecioVenta.setText(p.getPrecioVenta() == null ? "" : String.valueOf(p.getPrecioVenta()));
        }
    }

    /* ===================== TABLE MODEL ===================== */

    public static class ProductoTableModel extends AbstractTableModel {
        private final String[] cols = {
                "ID", "Código", "Nombre", "CategoríaId", "Talla", "Color", "ProveedorId", "PrecioVenta"
        };
        private List<ProductoDTO> data = new ArrayList<>();

        public void setProductos(List<ProductoDTO> productos) {
            this.data = (productos == null) ? new ArrayList<>() : new ArrayList<>(productos);
            fireTableDataChanged();
        }

        public ProductoDTO getProductoAt(int row) {
            if (row < 0 || row >= data.size()) return null;
            return data.get(row);
        }

        @Override public int getRowCount() { return data.size(); }
        @Override public int getColumnCount() { return cols.length; }
        @Override public String getColumnName(int column) { return cols[column]; }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            ProductoDTO p = data.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> p.getProductoId();
                case 1 -> p.getCodigoBarra();
                case 2 -> p.getNombre();
                case 3 -> p.getCategoriaId();
                case 4 -> p.getTalla();
                case 5 -> p.getColor();
                case 6 -> p.getProveedorId();
                case 7 -> p.getPrecioVenta();
                default -> null;
            };
        }
    }
}
