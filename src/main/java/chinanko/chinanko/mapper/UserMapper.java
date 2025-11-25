package chinanko.chinanko.mapper;

import chinanko.chinanko.dto.RoleResponse;
import chinanko.chinanko.dto.UserRequest;
import chinanko.chinanko.dto.UserResponse;
import chinanko.chinanko.model.Role;
import chinanko.chinanko.model.User;
import chinanko.chinanko.repository.RoleRepository;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public class UserMapper {
    private final RoleRepository roleRepository;
    public static User toEntity(UserRequest dto) {
        if (dto == null)
            return null;
        return User.builder()
                .nameUser(dto.getNameUser())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

    public static UserResponse toResponse(User user) {
        if (user == null)
            return null;
        return UserResponse.builder()
                .idUser(user.getIdUser())
                .nameUser(user.getNameUser())
                .email(user.getEmail())
                .build();
    }

    public static void copyToEntity(UserRequest dto, User entity) {
        if (dto == null || entity == null)
            return;
        entity.setNameUser(dto.getNameUser());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
    }
}
