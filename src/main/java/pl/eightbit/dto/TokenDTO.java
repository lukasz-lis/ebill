package pl.eightbit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class TokenDTO {

    @NotNull
    private String token;


}
