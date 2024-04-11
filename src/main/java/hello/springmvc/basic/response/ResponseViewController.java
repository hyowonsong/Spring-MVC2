package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 뷰 템플릿을 호출하는 컨트롤러
@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello").addObject("data","hello!");

        return mav;
    }

    // String 을 반환하는 경우 - View or Http 메시지
    //@ResponseBody 가 있으면 뷰 리졸버 실행X , HTTP 메시지 바디에 직접 response/hello 라는 문자 입력

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data","hello!!");
        return "response/hello";
    }

    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data","hello!");
    }
}
