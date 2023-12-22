package com.ecommerce.genAI.security;

import com.ecommerce.genAI.constants.ErrorMessage;
import com.ecommerce.genAI.repository.UserRepository;
import com.ecommerce.genAI.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (user.getActivationCode() != null) {
            throw new LockedException("Email not activated");
        }
        return UserPrincipal.create(user);
    }
}
