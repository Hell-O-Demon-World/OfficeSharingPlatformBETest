package com.golfzonaca.officesharingplatform.config.auth.provider;

import com.golfzonaca.officesharingplatform.config.auth.PrincipalDetailsService;
import com.golfzonaca.officesharingplatform.config.auth.token.IdPwAuthenticationToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class IdPwAuthenticationProvider implements AuthenticationProvider {
    private final PrincipalDetailsService principalDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final GrantedAuthoritiesMapper grantedAuthoritiesMapper = new SimpleAuthorityMapper();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userId = String.valueOf(authentication.getPrincipal());
        UserDetails userDetails = principalDetailsService.loadUserByUsername(userId);

        if (!this.passwordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
            return null;
        }

        IdPwAuthenticationToken certifiedToken = new IdPwAuthenticationToken(userDetails.getUsername(),
                userDetails.getPassword(), grantedAuthoritiesMapper.mapAuthorities(userDetails.getAuthorities()));

        certifiedToken.setDetails(authentication.getDetails());

        return certifiedToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (IdPwAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
