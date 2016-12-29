package pl.eightbit.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import pl.eightbit.models.Receipt;

@Component
public interface ReceiptRepository extends PagingAndSortingRepository<Receipt, Long> {
}
