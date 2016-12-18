package pl.eightbit.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import pl.eightbit.models.TaxType;

@Component
public interface TaxTypeRepository extends PagingAndSortingRepository<TaxType, Long> {


}
