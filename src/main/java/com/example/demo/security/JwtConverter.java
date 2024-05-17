package com.example.demo.security;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import com.example.demo.exception.BusinessException;

public class JwtConverter  implements Converter<Jwt, AbstractAuthenticationToken> {
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) throws BusinessException{

        Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
        if(null == realmAccess) throw new BusinessException("Token inválido");
        Collection<String> roles = realmAccess.get("roles");
        List<SimpleGrantedAuthority> grants = roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+ role)).toList();


        return new JwtAuthenticationToken(jwt, grants);
    }

}