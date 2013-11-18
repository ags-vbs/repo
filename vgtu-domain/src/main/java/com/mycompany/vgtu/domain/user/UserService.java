
package com.mycompany.vgtu.domain.user;

public interface UserService {

    UserJpa loadById(long id);

    UserJpa saveNewUser(UserJpa user);
    
    UserJpa loadByUsername(String username);
    
    UserJpa loadCurrentUser();
    
}
