# A Spring Security 6.0 Guide For Spring Boot 3

## Background
...

## Spring Frameworks
- Spring Web
- Spring Security
- Spring Data MongoDB
- Lombok

## To Get Started
clone this git repository into your local folder
```
git clone git@github.com:omgshalihin/spring-security-starter.git
```
go into the folder and open with your favorite IDE (intelliJ)
```
cd <folder> && idea pom.xml
```
inside main/resources, create a file called env.properties and then update the MongoDB Atlas connection string
```
DB_USER=<mongoDB_user>
DB_PWD=<mongoDB_password>
DB_ENDPOINT=<mongoDB_endpoint>
DB_NAME=<mongoDB_name>
```

## [Authentication](https://docs.spring.io/spring-security/reference/servlet/authentication/index.html)

Reading Username/Password: [Form Login](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/form.html)
- config/SecurityConfig.java
```
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf().disable()
        .authorizeHttpRequests()
        .requestMatchers("/users/new", "/products/welcome").permitAll()
        .and()
        .authorizeHttpRequests().requestMatchers("/products/**")
        .authenticated().and().formLogin().and().build();
}
```

Password Storage: Custom data stores with [UserDetailsService](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/user-details-service.html)
- config/SecurityConfig.java
```
@Bean
public UserDetailsService userDetailsService() {
    return new DatabaseUserDetailsService();
}
```
- config/DatabaseUserDetailsService.java
```
@Component
public class DatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> userModel = userRepository.findUserModelByUsername(username);
        return userModel.map(UserModelUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User [%s] not found", username)));
    }
}
```
- config/UserModelUserDetails.java
```
public class UserModelUserDetails implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserModelUserDetails(UserModel userModel) {
        username = userModel.getUsername();
        password = userModel.getPassword();
        authorities = Arrays.stream(userModel.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
}
```

Password Storage: [Password Encoder](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/password-encoder.html)
- config/SecurityConfig.java
```
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

Password Storage: [DaoAuthenticationProvider](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/dao-authentication-provider.html)
- config/SecurityConfig.java
```
@Bean
public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
}
```


## [Authorization](https://docs.spring.io/spring-security/reference/servlet/authorization/index.html)
@EnableMethodSecurity
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@PreAuthorize("hasAuthority('ROLE_USER')")
