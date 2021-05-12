/*package ru.lii.vmfm.db.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id = UUID.randomUUID();

    @Column(name = "username")
    private String username = "";

    @Column(name = "email")
    private String email = "";

    @Column(name = "password")
    private String password = "";

    @JoinTable(name = "user_role", joinColumns = JoinColumn(name = "user_id",
            referencedColumnName = "id"), inverseJoinColumns = JoinColumn(name = "role_id",
                    referencedColumnName = "id"))
    private Collection<Role> roles = new ArrayList<Role>();

    public User() {
    }

    public User(String uname, String email, String passwd) {
        this.username = uname;
        this.email = email;
        this.password = passwd;
    }

    public User(String uname, String email, String passwd, Collection<Role> roles) {
        this.username = uname;
        this.email = email;
        this.password = passwd;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public UUID getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final User other = (User) obj;
        if (!Objects.equals(this.Id, other.Id)) {
            return false;
        }

        if (!Objects.equals(this.username, other.username)) {
            return false;
        }

        if (!Objects.equals(this.password, other.password)) {
            return false;
        }

        if (!Objects.equals(this.name, other.name)) {
            return false;
        }

        return true;
    }
}
*/