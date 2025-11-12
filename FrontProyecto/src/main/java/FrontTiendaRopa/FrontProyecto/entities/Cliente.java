package FrontTiendaRopa.FrontProyecto.entities;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Cliente implements Comparable<Cliente>{
    private Integer idCliente;
    private String nombre;
    private String telefono;
    private String email;
    @Override
    public int compareTo(Cliente o) {
        return this.nombre.compareTo(o.nombre);
    }
}
