package pl.eightbit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


@Component
public interface MemberRepository extends CrudRepository<Member, Long> {
}
