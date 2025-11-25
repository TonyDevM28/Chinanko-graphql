package chinanko.chinanko.repository;

import java.util.List;
import java.util.Optional; // ¡Importante!

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import chinanko.chinanko.model.User;

// 1. Cambia el 'Integer' por 'Long', ya que 'SERIAL' en Postgres es un BIGINT
public interface UserRepository extends JpaRepository<User, Integer> {

    // 2. ESTE ES EL MÉTODO QUE SECURITY DEBE USAR.
    // No necesita @Query. Spring lo crea solo.
    // Buscará en la tabla 'users' (de la entidad User) por la columna 'email'.
    Optional<User> getUserByEmail(String email);

    // 3. Tu otro método, ahora corregido para apuntar a 'users' y 'name_user'
    @Query(value = "SELECT * FROM users WHERE LOWER(name_user) = LOWER(:nameUser);", nativeQuery = true)
    List<User> getUserByName(@Param("nameUser") String nameUser);
    
    // 4. Borramos el método 'getUserByEmail', ya que 'findByEmail' lo reemplaza.
}