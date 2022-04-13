


**Basic Authentication**

- BASE64 인코딩

- HTTPS 추천됨

- 빠르고 단순

- 로그아웃 불가



**Form Authentication**

- 유저ID(Name) & 비밀번호 사용

- 대다수 웹사이트에서의 표준임

- HTTPS 추천됨

- 로그아웃 가능



**Spring Security**

- 다음과 같은 설정으로 form authentication 활성화 가능

```java

http
	.formLogin();
```


**SESSSIONID**

- 기본으로는 In Memory Database 에 세션 정보 저장됨(애플리케이션 종료하면 초기화됨)
	- rdbms 또는 nosql db 에 보관 가능

- 스프링 시큐리티에서 폼 인증 시 자동으로 SESSIONID 정보가 브라우저의 쿠키에 저장됨



### Custom Login Page

- form 인증 방식을 실습하기 위해 뷰 템플릿 엔진이 필요하겠죠?
	- `thymeleaf` 의존성을 추가해준다


커스텀 페이지를 로그인 페이지로

```java
                .formLogin()
                .loginPage("/login").permitAll();
```


**인증 성공 후 리다이렉트하기**

- default page 설정 [지정 페이지, 항상 사용여부]

```java

.defaultSuccessUrl("/courses", true);

```


**Remember Me**

- 사용자 인증을 오래 기억하도록 하는 것(default 14 days)

- 기본적으로 설정 없이는 세션아이디는 30분 후 만료된다.

- remember-me 쿠키가 생성되서 저장된다.
	- username
	- expired date
	- md5 hash of the 2 values(key)


```java

.rememberMe().tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
.key("somethingverysecured");

```

- 세션의 유지시간을 remember-me 기능으로 연장시킬 수 있다.



**Logout**

- 로그아웃 하면 기본으로 사용자 브라우저의 인증 관련 세션을 모두 invalidate 시킨다.

- 물론 설정으로 커스텀 할 수 도 있다.


```java

                .logout()
                .logoutUrl("/logout") // 로그아웃 동작이 되는 url
                .clearAuthentication(true) // 인증정보를 모두 지움
                .invalidateHttpSession(true) // 세션을 invalidate 시킴
                .deleteCookies("JSESSIONID", "remember-me") // 해당 이름의 쿠키를 모두 지움
                .logoutSuccessUrl("/login"); // 로그아웃 성공 후 리다이렉트할 페이지

```


default logout method 는 `GET` 이다.

- 하지만 기본적으로 CSRF 등의 공격에 대한 방어를 위해 POST 사용을 권장한다.

> If CSRF protection is enabled, then logout request must also be a POST

> Best Practice to use an HTTP POST on any action that change state(i.e logout) to protect against CSRF 

> If you really want to use an HTTP GET, you can use it

```java

logoutRequestMatcher(new AntPathRequestMatcher(logoutUrl, "GET"));

```


**파라미터 바꾸기**

- 기본으로 `username`, `password`, `remember-me` 파라미터를 사용한다.

```java

                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/courses", true)
                .usernameParameter("yourparam")
                .passwordParameter("yourparam")
```
