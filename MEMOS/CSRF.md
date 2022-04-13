

**Cross-Site-Request-Forgery**

The action of forging a copy or imitation of a docs, signature.

<br>

**Internet Banking Simulation**

1. Perpetrator forges a request for a fund transfer to a website

2. Perpetrator `embeds` the request into a `link` and sends it to visitors may be logged into the site.

3. A visitor clicks the link, sending the request(Perpetrator's) to the website

4. Website validates request and transfers funds from the visitor's account to the perpetrator.


<br>

**CSRF in Spring Security**

Basically, Spring Security defends csrf by using csrf-token in form Authentication.

When authenticated user request something(POST,PUT,DELETE),

request must includes csrf-token and server validate it should be equal that server has.

(Request Header has X-XSRF-TOKEN)


> turn off

```java

http.csrf().disable()

```

<br>

**When to use CSRF Protection?**

when you use CSRF protection?

Recommendation is use for protection for any request that could be processed by `browser` by normal users.

In non-browser clients, can disable CSRF protection.




