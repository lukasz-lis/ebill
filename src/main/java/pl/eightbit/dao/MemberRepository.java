package pl.eightbit.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import pl.eightbit.models.Member;


@Component
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {

    Member findByUsername(String username);

    Member findByEmail(String email);

    Member findById(long id);
}
