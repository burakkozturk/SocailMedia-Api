package platformApp.demo.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import platformApp.demo.entites.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Getter
@Setter
public class JwtUserDetails implements UserDetails {

    private Long id;
    private String userName;
    private String password;
    private Collection<? extends GrantedAuthority> autherities;

    public JwtUserDetails(Long id, String userName, String password, Collection<? extends GrantedAuthority> autherities) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.autherities = autherities;
    }

    public static JwtUserDetails create(User user) {
        List<GrantedAuthority> authoritiesList = new ArrayList<>();
        authoritiesList.add(new SimpleGrantedAuthority("user"));
        return new JwtUserDetails(user.getId(), user.getUserName(), user.getPassword(),authoritiesList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
        return true ;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
