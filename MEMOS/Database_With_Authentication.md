


## Database Authentication


- 지금까지 기본적으로 인증 정보는 InMememory 에 저장되었었다.

- 앞서 폼 인증 실습에서까지 InMemoryUserDetailsManager 를 통해 인 메모리로 사용자를 생성했었다.

```java

// params: UserDetails
return new InMemoryUserDetailsManager(kim, zang, choi);

```



**데이터베이스에 인증정보 저장하기**


![secu1](https://user-images.githubusercontent.com/76927397/163817585-b39c798c-3f2d-401a-ab5d-f01d84294041.JPG)


1. 스프링 시큐리티가 클라이언트로부터 인증 요청을 받아 `DaoAuthenticationProvider` 에 인증 처리를 위임함

2. `UserDetailsService` 에게 사용자 정보를 가져오게 한다.

3. `UserDetailsService` 구현 클래스는 데이터 저장소에서 사용자 정보를 가져온다.

4. 가져온 사용자 정보를 이용해 `UserDetails` 를 만든다.

5. `DaoAuthenticationProvider` 는 UserDetails 와 클라이언트가 제공한 인증 정보를 대조해 인증을 처리한다.


```java
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder); // password encorder
        provider.setUserDetailsService(applicationUserService); // userDetailsService 구현체
        return provider;
    }
``` 

