package nu.hjemme.business.domain.builder;

import java.util.Optional;

@FunctionalInterface
public interface RequiredField<T> {

    Optional<String> fetchMissingFieldMessage(T bean);
}
