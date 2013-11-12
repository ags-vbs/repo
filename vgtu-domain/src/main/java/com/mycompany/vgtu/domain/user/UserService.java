
package com.mycompany.vgtu.domain.user;

public interface UserService {

    UserJpa loadById(long id);

    UserJpa save(UserJpa user);
    
    UserJpa loadByUsername(String username);
    
    UserJpa loadCurrentUser();
    
}
