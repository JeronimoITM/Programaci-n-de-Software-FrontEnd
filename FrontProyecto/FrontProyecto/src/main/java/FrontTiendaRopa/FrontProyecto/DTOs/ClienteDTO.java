package FrontTiendaRopa.FrontProyecto.DTOs;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Integer idCliente;
    private String  nombre;
    private String  email;
    private String  telefono;
}
