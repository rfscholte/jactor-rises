package com.gitlab.jactor.rises.client.facade;

import com.gitlab.jactor.rises.client.datatype.Username;
import com.gitlab.jactor.rises.client.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserFacade {

    Optional<User> fetch(Long id);

    Optional<User> find(Username username);

    List<String> findAllUsernames();
}
