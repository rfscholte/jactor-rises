package com.github.jactor.rises.client.facade;

import com.github.jactor.rises.client.datatype.Username;
import com.github.jactor.rises.client.domain.User;

import java.util.Optional;

public interface UserFacade {

    Optional<User> fetch(Long id);

    Optional<User> find(Username username);
}
