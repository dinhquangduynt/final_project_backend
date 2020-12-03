package com.dinhquangduy.electronic.config;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dinhquangduy.electronic.bean.entity.RoleEntity;
import com.dinhquangduy.electronic.bean.entity.UserEntity;
import com.dinhquangduy.electronic.dao.UserDao;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserDao accountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userOpt = accountRepo.findByUserName(username);
//              .orElseThrow(() -> new UsernameNotFoundException("Not found account with username: " + username));
        if(userOpt.isPresent()) {
            UserEntity accountEntity = userOpt.get();

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

            Set<RoleEntity> roles = accountEntity.getRoles();

            for (RoleEntity role : roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
            }

            return new org.springframework.security.core.userdetails.User(
                    accountEntity.getUserName(), accountEntity.getPassword(), grantedAuthorities);
        }
      throw new UsernameNotFoundException("Not found account with username: " + username);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity account = accountRepo.findByUserName(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Not found account with username: " + username));
//        return UserPrincipal.create(account);
//    }

}