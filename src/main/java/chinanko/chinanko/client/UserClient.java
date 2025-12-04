package chinanko.chinanko.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import chinanko.chinanko.dto.UserDto;

// name: Nombre del servicio (útil si usas Eureka luego)
// url: La dirección directa del servicio Auth
@FeignClient(name = "auth-service", url = "http://localhost:8082") 
public interface UserClient {

    // Este método llamará a GET http://localhost:8080/api/v1/users/{id}
    @GetMapping("/api/v1/users/{id}")
    UserDto getUserById(@PathVariable("id") Integer id);
}