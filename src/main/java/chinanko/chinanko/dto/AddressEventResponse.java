package chinanko.chinanko.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressEventResponse {

    @JsonProperty("id_address_event")
    private Integer idAddressEvent;

    @JsonProperty("street")
    private String street;

    @JsonProperty("exterior_number")
    private String exteriorNumber;

    @JsonProperty("interior_number")
    private String interiorNumber;

    @JsonProperty("neighborhood")
    private String neighborhood;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("latitude")
    private BigDecimal latitude;

    @JsonProperty("longitude")
    private BigDecimal longitude;

    @JsonProperty("event_name")
    private String eventName;
}
