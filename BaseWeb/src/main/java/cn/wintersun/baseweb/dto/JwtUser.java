package cn.wintersun.baseweb.dto;

import cn.wintersun.baseweb.dao.Modules;
import cn.wintersun.baseweb.dao.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author WinterSun
 * @email wintersun2284@163.com
 * @className cn.wintersun.basebackend.dto.JwtUser
 * @create 2021-10-13-10:36
 * @Description TODO
 */
public class JwtUser implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private List<Modules> modules;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser() {
    }

    /**
     * 写一个能直接使用user创建jwtUser的构造器
     **/
    public JwtUser(User user) {
        id = user.getId();
        username = user.getUserAccount();
        password = user.getPassword();
        modules = user.getModules();
        authorities = Collections.singleton(new SimpleGrantedAuthority(user.getUserAccount()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.modules == null) {
            return null;
        }
        List<GrantedAuthority> list = new ArrayList<>();

        List<Modules> modules1 = getModules();
        for (Modules modules : modules1) {
            SimpleGrantedAuthority s = new SimpleGrantedAuthority(modules.getId().toString());
            list.add(s);
        }

        return list;
    }

    public List<Modules> getModules() {
        return modules;
    }

    public void setModules(List<Modules> modules) {
        this.modules = modules;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
    public String toString() {
        return "JwtUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
