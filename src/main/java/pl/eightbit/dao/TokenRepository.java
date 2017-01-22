package pl.eightbit.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import pl.eightbit.models.Token;

import java.util.Optional;

@Component
public interface TokenRepository extends PagingAndSortingRepository<Token, Long> {

    Optional<Token> findByToken(final String token);

    void deleteByToken(final String token);

}
