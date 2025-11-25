package chinanko.chinanko.model;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "ROLES")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_rol")
    private Integer idRol;

    @Column(name = "name_rol", nullable = false, unique = true)
    private String nameRol; // ej: "ROLE_ADMIN", "ROLE_USER"

    // --- (El campo 'authority' se ha borrado) ---

    @Override
    public String getAuthority() {
        // --- APUNTA AL CAMPO 'nameRol' ---
        return this.nameRol;
    }
}