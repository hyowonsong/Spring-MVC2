package hello.springmvc.basic.request;

//이번에는 HTTP API에서 주로 사용하는 JSON 데이터 형식을 조회해보자

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//HttpServletRequest 를 사용해서 직접 HTTP 메시지 바디에서 데이터를 읽어와 문자로 변환

@Slf4j
@Controller
public class RequestBodyJsonController {
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException{

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        HelloData data = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", data.getUsername(), data.getAge());

        response.getWriter().write("ok");
    }

    //V2
    //@RequestBody 를 사용해서 HTTP 메시지에서 데이터를 꺼내고 messageBody 저장
    //문자로 된 JSON 데이터인 messageBody 를 objectMapper 를 통해서 자바 객체로 변환한다.
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        HelloData data = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", data.getUsername(), data.getAge());

        return "ok";
    }

    //v3
    //예외 처리도 안한다.
    //@RequestBody 객체 파라미터
    //@RequestBody HelloData data
    //@RequestBody 에 직접 만든 객체를 지정할 수 있다.
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData data){

        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return "ok";
    }

    //V4
    //requestBody 대신에 HttpEntity 를 사용해도 된다.
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity <HelloData> httpEntity){

        HelloData data = httpEntity.getBody();
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return "ok";
    }

    //V5
    // 응답의 경우에도 @ResponseBody 를 사용하면 해당 객체를 HTTP 메시지 바디에 직접 넣어줄 수 있다.
    // String 대신에 HelloData 를 넣어주고 data 를 출력해줌
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data){

        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return data;
    }
}
