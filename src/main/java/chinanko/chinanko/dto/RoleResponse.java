package chinanko.chinanko.dto;

import java.util.List;

import chinanko.chinanko.model.User;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoleResponse {
    private Integer idRol;
    private String nameRol;
}
