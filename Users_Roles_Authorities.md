

## 어떤 유저 정보가 필요할까?..?

**Username(or id)** 
	- 유니크해야한다.

**Password** 
	- 반드시 `Encoded` 되어야 한다.

**Roles** : 역할
	- 시스템의 사용자 계층화 
	- Role has permissions

**Permissions** 
	- 할 수 있는 행위들


## UserDetails

- 사용자의 정보를 담는 인터페이스

- 해당 인터페이스를 구현하면 Spring Security 가 그 클래스를 사용자 정보 객체로 인식한다.

- 기존에 시스템에 있던 사용자 엔티티로 이걸 implements 하면 된다.



## UserDetailService

- DB에서 유저 정보를 `username` 을 이용해 가져오는 역할을 하는 인터페이스이다.
- Session 에 저장될 사용자 정보인 `UserDetails`. 를 반환한다.

- `loadUserByUsername()`
	- `username` 으로 사용자 정보 조회 한다.
	- 권한 조회해 SimpleGrantedAuthority 목록을 세팅
	- Authentication -> principal 객체에 UserDetails 저장
	- Authentication -> authorities 객체에 권한 저장

- 구현체
	- InMemoryUserDetailsManager
		- 메모리 상에서 인증 정보를 관리한다.
	- JdbcUserDetailsManager
		- DB 에서 인증 정보를 관리한다.



## PasswordEncorder

- SpringSecurity 에서 비밀번호의 암호화는 필수다.

```java
    java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
```

- 다음과 같은 인터페이스다

```java

public interface PasswordEncoder {
    String encode(CharSequence rawPassword);

    boolean matches(CharSequence rawPassword, String encodedPassword);

    default boolean upgradeEncoding(String encodedPassword) {
        return false;
    }
}

```


## Set A Role

- 사용자는 Role (들)을 가진다.
- Role 은 그 자체로도 권한일 수 있다.
- Role 은 Permission 들을 가지는 컨테이너가 될 수 있다.

**Role 적용해서 자원 접근 제한해보기 without Permission**

```java

public enum UserRole {
	STUDENT, ADMIN
}

```

테스트를 위해 InMemory 유저에 권한을 넣어 만들자

```java
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails kim = User.builder()
                .username("킴갑환")
                .password(passwordEncoder.encode("1234"))
                .roles(STUDENT.name())
                .build();

        UserDetails choi = User.builder()
                .username("최번개")
                .password(passwordEncoder.encode("1234"))
                .roles(ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(kim, choi);
    }

```


configure 메소드에서 특정 자원에 대한 접근에 대해 특정한 권한을 요구할 수 있다,

```java

.antMatchers("/api/**").hasRole(STUDENT.name())

```

- STUDENT Role 을 지닌 사용자만 `/api/` 에 대한 자원에 접근할 수 있다.

- ADMIN Role 을 지닌 최번개가 접근 시도 시 `403 Access Denied` 



## Permission

- low-level 의 권한을 말함
- Role 이 사용할 모든 권한이 있어야 함, Role 보다 세밀하고 작은 단위 (N : N)

