package FrontTiendaRopa.FrontProyecto.window;

import FrontTiendaRopa.FrontProyecto.DTOs.ProductoDTO;
import FrontTiendaRopa.FrontProyecto.webservicesclient.ProductoApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Component
public class ProductoWindow extends JFrame {

    @Autowired
    private ProductoApiClient productoApiClient;

    private JTable tableProductos;
    private ProductoTableModel tableModel;

    private JTextField txtNombre = new JTextField(15);
    private JTextField txtCodigoBarra = new JTextField(15);
    private JTextField txtPrecio = new JTextField(10);

    private JButton btnCrear = new JButton("Crear");
    private JButton btnActualizar = new JButton("Actualizar");
    private JButton btnEliminar = new JButton("Eliminar");

    public ProductoWindow() {
        setTitle("Gestión de Productos");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void initializeUI() {
        tableModel = new ProductoTableModel();
        tableProductos = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(tableProductos);
        add(scrollPane, BorderLayout.CENTER);

        // Panel de Formulario
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new FlowLayout());
        panelForm.add(new JLabel("Código Barra:"));
        panelForm.add(txtCodigoBarra);
        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(txtNombre);
        panelForm.add(new JLabel("Precio Venta:"));
        panelForm.add(txtPrecio);
        panelForm.add(btnCrear);
        panelForm.add(btnActualizar);
        panelForm.add(btnEliminar);
        add(panelForm, BorderLayout.NORTH);

        // Botones
        btnCrear.addActionListener(e -> crearProducto());
        btnActualizar.addActionListener(e -> actualizarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());

        // Cargar datos
        cargarProductos();

        setVisible(true);
    }

    private void cargarProductos() {
        List<ProductoDTO> productos = productoApiClient.getProductos();
        tableModel.setProductos(productos);
    }

    private void crearProducto() {
        try {
            ProductoDTO p = new ProductoDTO();
            p.setCodigoBarra(txtCodigoBarra.getText());
            p.setNombre(txtNombre.getText());
            p.setPrecioVenta(Double.parseDouble(txtPrecio.getText()));

            productoApiClient.crearProducto(p);
            cargarProductos();
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Producto creado correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al crear producto: " + e.getMessage());
        }
    }

    private void actualizarProducto() {
        int fila = tableProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para actualizar.");
            return;
        }
        ProductoDTO p = tableModel.getProductoAt(fila);
        try {
            p.setCodigoBarra(txtCodigoBarra.getText());
            p.setNombre(txtNombre.getText());
            p.setPrecioVenta(Double.parseDouble(txtPrecio.getText()));

            productoApiClient.actualizarProducto(p);
            cargarProductos();
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar producto: " + e.getMessage());
        }
    }

    private void eliminarProducto() {
        int fila = tableProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar.");
            return;
        }
        ProductoDTO p = tableModel.getProductoAt(fila);
        try {
            productoApiClient.eliminarProducto(p.getProductoId());
            cargarProductos();
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Producto eliminado correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar producto: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        txtCodigoBarra.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }
}
