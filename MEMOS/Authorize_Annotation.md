


Spring Security 에서 권한별 접근은 재정의한 `configure` 에 다음과 같은 형태로 설정될 것이다.

```java
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .antMatchers(HttpMethod.DELETE, "/management/api/**").hasRole(COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/management/api/**").hasRole(COURSE_WRITE.getPermission())
                .antMatchers("/management/api/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
```

이 방법의 대안으로 `Controller` 에 어노테이션을 추가하는 방법이 있다.

위에서 권한을 필요로하는 `antMatchers` 들은 어노테이션으로 쉽게 대체될 수 있다.

우선 다음과 같은 어노테이션을 `Configure` 클래스에 추가해줘야 합니다.


```java

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)

```

- securedEnabled : @Secure 
- prePostEnabled: @PreAuthorize, @PostAuthorize


**@Secured**

- 사용자 권한 정보에 따라 메소드 접근 제한 가능
- 표현식을 사용할 수 없다.


**@PreAuthorize**

- 동작 수행전에 권한 or 역할을 검사
- SpringEL 의 expression 도 사용 가능하며, Custom Expression(메소드) 도 호출할 수 있다.

```java

@GetMapping
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
public List<Student> getAllStudents() {
	return STUDENTS;
}

@PostMapping
@PreAuthorized("hasAuthority('student:write')")
public void register(@RequestBody Student student) {
	System.out.println(student);
}

```

```java

// Component

@Component("loginService")
public class LoginService {
	public boolean pageReadRoleCheck(String id) {
		return true;
	}
}

// Controller

@GetMapping("/api/v1/projects")
@PreAuthorize("@loginService.pageReadRoleCheck(#pj.id)")
public List<ProjectResponseDto> getProjectList(Project pj) {
	return projectService.findAll();
}

```


**@PostAuthorize**

- 동작 수행 후 권한 or 역할을 검사한다.

- `returnObject` 예약어를 통해 해당되는 메소드의 수행 결과 값도 전달할 수 있다.


```java

@PostAuthorize("@loginService.pageReadRoleCheck(returnObject.name)")
@RequestMapping( value = "/{id}", method = RequestMethod.GET )
public Project getProject( @PathVariable("id") long id ){
    return service.findOne(id);
}

```


**표현식 종류**

**hasRole([role])**

- 현재 사용자의 권한이 파라미터의 권한과 동일한 경우 `true`

**hasAnyRole([role1,role2])**

- 현재 사용자의 권한이 파라미터들 중 포함되어 있다면 `true`

**principal**

- 사용자 증명 주요 객체에 직접 접근

**authentication**

- `SecurityContext` 에 있는 `authentication` 객체에 접근

**denyAll**

- 모든 접근 금지

**permitAll**

- 모든 접근 허용

**isAnonymous()**

- 익명사용자인 경우 `true`

**isRememberMe()**

- 현재 사용자가 rememberMe 면 `true`

**isAuthenticated()**

- 현재 사용자가 로그인 상태면 `true`

