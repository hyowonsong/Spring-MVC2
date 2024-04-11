package hello.springmvc.basic.response;

// HTTP API 를 제공하는 경우에는 HTML 이 아니라 데이터를 전달해야 하므로,
// HTTP 메시지 바디에 JSON 같은 형식으로 데이터를 실어 보낸다.

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class ResponseBodyController {

    //서블릿을 직접 다룰 때 처럼
    //HttpServletResponse 객체를 통해서 HTTP 메시지 바디에 직접 ok 응답 메시지를 전달한다.

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException{
        response.getWriter().write("ok");
    }

    //ResponseEntity 엔티티는 HttpEntity 를 상속 받았음.
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    //@ResponseBody 를 사용하면 view 를 사용하지 않고, HTTP 메시지 컨버터를 통해서 메시지 입력
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3(){
        return "ok";
    }


    //ResponseEntity 를 반환한다. HTTP 메시지 컨버터를 통해서 JSON 형식으로 변환되어서 반환된다
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    //ResponseEntity 는 HTTP 응답 코드를 설정
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2(){
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return helloData;
    }

}
