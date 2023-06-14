package ru.abdullaeva.informatbackend.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUser implements UserDetails {

    private final Integer id;
    private final String login;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean blocked;

    public JwtUser(Integer id, String login, String password, Collection<? extends GrantedAuthority> authorities, boolean blocked)
    {
        this.id = id;
        this.login = login;
        this.password = password;
        this.authorities = authorities;
        this.blocked = blocked;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return "";
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !blocked;
    }

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    /**
     * Метод для получения логина пользователя Jwt
     * @return login пользователя JwtUser
     */
    public String getLogin() {
        return login;
    }

}
