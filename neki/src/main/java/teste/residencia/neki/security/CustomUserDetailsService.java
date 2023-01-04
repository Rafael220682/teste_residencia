package teste.residencia.neki.security;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import teste.residencia.neki.model.User;
import teste.residencia.neki.service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login){
        User user = getUser(() -> userService.obterPorLogin(login));
        return user;

        //return userService.obterPorLogin(login).get();
    }

    public UserDetails obterUserPorId(Long id){
        return userService.obterPorId(id).get();

    }


    private User getUser(Supplier<Optional<User>> supplier){
        return supplier.get().orElseThrow(() -> new UsernameNotFoundException("Login não encontrado!"));
    }

      
}
