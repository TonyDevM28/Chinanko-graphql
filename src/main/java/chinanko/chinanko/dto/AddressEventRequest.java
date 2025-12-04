package chinanko.chinanko.dto;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressEventRequest {

    @NotBlank(message = "Street is required")
    @Size(max = 150)
    @JsonProperty("street")
    private String street;

    @NotBlank(message = "Exterior number is required")
    @Size(max = 20)
    @JsonProperty("exterior_number")
    private String exteriorNumber;

    @Size(max = 20)
    @JsonProperty("interior_number")
    private String interiorNumber;

    @NotBlank(message = "Neighborhood is required")
    @Size(max = 100)
    @JsonProperty("neighborhood")
    private String neighborhood;

    @NotBlank(message = "Postal code is required")
    @Size(max = 10)
    @JsonProperty("postal_code")
    private String postalCode;

    @NotNull
    @DecimalMin("-90.0") @DecimalMax("90.0")
    @Digits(integer = 3, fraction = 8)
    @JsonProperty("latitude")
    private BigDecimal latitude;

    @NotNull
    @DecimalMin("-180.0") @DecimalMax("180.0")
    @Digits(integer = 3, fraction = 8)
    @JsonProperty("longitude")
    private BigDecimal longitude;

    // CORRECCIÓN: Quitamos @NotNull. 
    // Al crear un evento nuevo con sus direcciones, este campo vendrá nulo desde el Input.
    @JsonProperty("event_id")
    private Integer eventId;
}