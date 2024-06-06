package com.app.service;

import com.app.model.Employee;
import com.app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private EmployeeRepository empRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee authenticatedEmployee = empRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid Email !!!"));
        System.out.println(authenticatedEmployee.getEmail());
        return new CustomUserDetails(authenticatedEmployee);
    }
}
