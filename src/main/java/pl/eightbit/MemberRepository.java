package pl.eightbit;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;


@Component
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {

    Member findByUsername(String username);
}
