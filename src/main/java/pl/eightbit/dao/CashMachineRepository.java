package pl.eightbit.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.eightbit.models.CashMachine;

import java.util.Optional;

@Component
public interface CashMachineRepository extends CrudRepository<CashMachine, Long> {

    Optional<CashMachine> findByCashMachineNumber(final Long cashMachineNumber);

}
