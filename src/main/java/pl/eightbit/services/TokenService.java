package pl.eightbit.services;

import pl.eightbit.dto.TokenDTO;

import java.util.List;

public interface TokenService {
    List<TokenDTO> generateTokens(int tokenAmount);
}
