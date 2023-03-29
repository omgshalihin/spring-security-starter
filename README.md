# A Spring Security 6.0 Guide For Spring Boot 3

## [Authentication](https://docs.spring.io/spring-security/reference/servlet/authentication/index.html)
[Password Encoder](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/password-encoder.html)
```
@Bean
public PasswordEncoder passwordEncoder() {

    return new BCryptPasswordEncoder();
}
```
[Form Login](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/form.html)
```
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    return http.csrf().disable()
        .authorizeHttpRequests()
        .requestMatchers("/products/welcome").permitAll()
        .and()
        .authorizeHttpRequests().requestMatchers("/products/**")
        .authenticated().and().formLogin().and().build();
}
```
[In-Memory Authentication](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/in-memory.html): Users & Passwords
```
@Bean
public UserDetailsService userDetailsService(PasswordEncoder encoder) {

    UserDetails admin = User.withUsername("admin")
        .password(encoder.encode("password"))
        .roles("ADMIN")
        .build();
        
    UserDetails userModel = User.withUsername("userModel")
        .password(encoder.encode("password"))
        .roles("USER")
        .build();
        
    return new InMemoryUserDetailsManager(admin, userModel);
}
```
OR
save users and password to Database
```
kk
```
## [Authorization](https://docs.spring.io/spring-security/reference/servlet/authorization/index.html)
@EnableMethodSecurity
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@PreAuthorize("hasAuthority('ROLE_USER')")
