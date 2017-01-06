package pl.eightbit.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import pl.eightbit.models.TaxType;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public interface TaxTypeRepository extends PagingAndSortingRepository<TaxType, Long> {

    Optional<TaxType> findByTaxTypeAmount(final BigDecimal taxTypeAmount);

}
