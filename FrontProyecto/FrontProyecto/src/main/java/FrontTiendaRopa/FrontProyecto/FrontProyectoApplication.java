package FrontTiendaRopa.FrontProyecto;

import FrontTiendaRopa.FrontProyecto.window.ProductoWindow;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FrontProyectoApplication implements CommandLineRunner {

    private final ApplicationContext context;

    public FrontProyectoApplication(ApplicationContext context) {
        this.context = context;
    }

    public static void main(String[] args) {
        SpringApplication.run(FrontProyectoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        ProductoWindow ventana = context.getBean(ProductoWindow.class);
        ventana.initializeUI(); // método público
    }
}
