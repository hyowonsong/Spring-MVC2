package hello.springmvc.basic;

//실제 개발을 하면 요청 파라미터를 받아서 필요한 객체를 만들고 그 객체에 값을 넣어주어야 한다. 보통
//다음과 같이 코드를 작성할 것이다.
//먼저 요청 파라미터를 바인딩 받을 객체 만들기

import lombok.Data;

@Data
public class HelloData {
    private String username;
    private int age;
}
