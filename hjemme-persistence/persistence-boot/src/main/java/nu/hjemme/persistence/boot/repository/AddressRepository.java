package nu.hjemme.persistence.boot.repository;

import nu.hjemme.persistence.boot.entity.address.AddressEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

}
