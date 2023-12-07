package com.wimp.customer.application;

import com.wimp.customer.dao.CustomerRepository;
import com.wimp.customer.model.CustomerEntity;
import com.wimp.customer.model.CustomerDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomerEntity customer = customerRepository.findByEmail(username) .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));

        if(customer != null) {
            return new CustomerDetails(customer);
        }

        return null;
    }
}
