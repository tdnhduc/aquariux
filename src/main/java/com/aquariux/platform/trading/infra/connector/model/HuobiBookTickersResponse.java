package com.aquariux.platform.trading.infra.connector.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@Getter
@NoArgsConstructor
@ToString
public class HuobiBookTickersResponse {
    @JsonProperty("data")
    private List<HuobiBookTicker> data;
}
