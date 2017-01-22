package pl.eightbit.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.eightbit.dto.TokenDTO;
import pl.eightbit.services.TokenService;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class TokenRestController {

    private static final int TOKEN_AMOUNT = 10;

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/generate-tokens", method = RequestMethod.GET)
    public ResponseEntity<List<TokenDTO>> generateTokents() {
        return ResponseEntity.ok(tokenService.generateTokens(TOKEN_AMOUNT));
    }


}
