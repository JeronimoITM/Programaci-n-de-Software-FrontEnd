package FrontTiendaRopa.FrontProyecto.DTOs;
import lombok.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import FrontTiendaRopa.FrontProyecto.entities.Venta;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VentaDTO {
    private Integer id;
    private Integer clienteId;
    private String  clienteNombre;
    private LocalDateTime fechaVenta;
    private Double  total;

    @Builder.Default
    private List<VentaItemDTO> items = new ArrayList<>();

    /** Mapper opcional desde la entidad (si la construyes en el front) */
    public static VentaDTO fromEntity(Venta v) {
        VentaDTO dto = VentaDTO.builder()
                .id(v.getId())
                .clienteId(v.getCliente().getIdCliente())
                .clienteNombre(v.getCliente().getNombre())
                .fechaVenta(v.getFechaVenta())
                .total(v.getTotal())
                .build();

        if (v.getItems() != null) {
            dto.setItems(
                    v.getItems().stream()
                            .map(VentaItemDTO::fromEntity)
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }
}