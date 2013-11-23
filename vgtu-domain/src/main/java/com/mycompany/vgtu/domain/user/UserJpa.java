package com.mycompany.vgtu.domain.user;

import com.mycompany.vgtu.domain.BasicEntity;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserJpa extends BasicEntity {

    private static final long serialVersionUID = 1L;
    @Column(unique = true, nullable = false)
    private String username;    
    private String name;
    private String surname;
    @Column(nullable = false)
    private String salt;
    @Column(nullable = false)
    private String password;
    @ElementCollection
    @CollectionTable(
            name = "permissions",
            joinColumns =
            @JoinColumn(name = "user_id"))
    private Set<String> permissions;

    public UserJpa() {
        super();
    }

    public UserJpa(String username,
            String name,
            String surname,
            String salt) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.salt = salt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}
