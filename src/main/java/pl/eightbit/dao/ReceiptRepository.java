package pl.eightbit.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import pl.eightbit.models.Member;
import pl.eightbit.models.Receipt;

@Component
public interface ReceiptRepository extends PagingAndSortingRepository<Receipt, Long> {

    Page<Receipt> findByMember(final Member member, final Pageable pageable);
}
