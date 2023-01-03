package teste.residencia.neki.model;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@SequenceGenerator(name = "generator_user", sequenceName = "user_seq", initialValue = 1, allocationSize = 1)
@Table(name = "user")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_user")
    @NotNull
    @Column(name="userId")
    private Long id;

    @Column(length = 12, unique = true)  
    @NotNull  
    private String login;

    @Column(length = 100)
    @NotNull
    private String password;
    
    private LocalDate lastLoginDate;

    public User() {
    }

    public User(Long id, String login, String password, LocalDate lastLoginDate) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.lastLoginDate = lastLoginDate;
    }

    public User(String login, String password, LocalDate lastLoginDate) {
        this.login = login;
        this.password = password;
        this.lastLoginDate = lastLoginDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDate lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public String getPassword1() {
       return password;
    }
  
    @Override
    public String getUsername() {
       return login;
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

    

    
    
    

}
