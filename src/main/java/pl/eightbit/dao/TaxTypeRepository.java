package pl.eightbit.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import pl.eightbit.models.TaxTypes;

@Component
public interface TaxTypeRepository extends PagingAndSortingRepository<TaxTypes, Long> {


}
