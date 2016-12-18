package pl.eightbit.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import pl.eightbit.models.CurrencyType;

@Component
public interface CurrencyTypeRepository extends PagingAndSortingRepository<CurrencyType, Long> {


}
