package chinanko.chinanko.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventResponse {

    @JsonProperty("id_event")
    private Integer idEvent;

    @JsonProperty("name_event")
    private String nameEvent;

    @JsonProperty("description")
    private String description;

    @JsonFormat(pattern = "HH:mm:ss")
    @JsonProperty("time_begin")
    private LocalDate timeBegin;

    @JsonFormat(pattern = "HH:mm:ss")
    @JsonProperty("time_end")
    private LocalDate timeEnd;

    @JsonProperty("price")
    private Float price;

    // --- CAMBIO: Devolvemos IDs, no Nombres ---
    @JsonProperty("town_id")
    private Integer townId;

    @JsonProperty("type_event_id")
    private Integer typeOfEventId;

    @JsonProperty("state_event_id")
    private Integer stateOfEventId;

    @JsonProperty("addresses")
    private List<AddressEventResponse> addresses;
}