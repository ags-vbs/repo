
package com.mycompany.vgtu.domain.user;

import com.google.common.base.Optional;

public interface UserService {

    UserJpa loadById(long id);

    UserJpa saveNewUser(UserJpa user);
    
    Optional<UserJpa> loadByUsername(String username);
    
    Optional<UserJpa> loadCurrentUser();
    
    boolean isUsernameUnique(String username);
    
}
