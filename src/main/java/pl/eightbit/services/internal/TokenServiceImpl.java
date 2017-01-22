package pl.eightbit.services.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pl.eightbit.dao.TokenRepository;
import pl.eightbit.dto.TokenDTO;
import pl.eightbit.models.Member;
import pl.eightbit.models.Token;
import pl.eightbit.services.AccountService;
import pl.eightbit.services.TokenService;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final AccountService accountService;


    @Autowired
    public TokenServiceImpl(final TokenRepository tokenRepository, final AccountService accountService) {
        this.tokenRepository = tokenRepository;
        this.accountService = accountService;
    }

    @Override
    public List<TokenDTO> generateTokens(final int tokenAmount) {

        final SecureRandom random = new SecureRandom();

        final List<TokenDTO> tokens = generateSecureTokens(tokenAmount, random);
        saveTokens(tokens);
        return tokens;
    }

    @Async
    private void saveTokens(final List<TokenDTO> tokenDTOS) {

        final Member member = accountService.getLoggedMember()
                .orElseThrow(() -> new SecurityException("User is not logged."));

        tokenDTOS.stream()
                .map(TokenDTO::getToken)
                .map(token -> Token.builder()
                        .member(member)
                        .token(token)
                        .build())
                .forEach(token -> tokenRepository.save(token));
    }

    private List<TokenDTO> generateSecureTokens(final int tokenAmount, final SecureRandom random) {
        return IntStream.range(0, tokenAmount)
                .mapToObj(value -> new BigInteger(130, random).toString(32))
                .map(TokenDTO::new)
                .collect(Collectors.toList());
    }
}
