package pl.eightbit;

import org.springframework.data.repository.PagingAndSortingRepository;


public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {
}
