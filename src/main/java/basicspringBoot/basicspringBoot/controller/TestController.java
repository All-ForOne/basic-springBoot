package basicspringBoot.basicspringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    /*
        url 호출시
        spring은 dispatcherServlet을 통해 해당 이름을 가진 controller를 요청(HandlerAdapter)합니다.
        controller에서는 비즈니스 로직을 처리하고, view에 전달할 객체를 Model에 담아 view name을 반환합니다.
        DispatcherServlet은 viewResolver를 통해 해당 view name을 가진 template(html 페이지)을 얻게 됩니다.

        혹 controller에 @ResponserBody가 존재하는 경우에는 viewResolver 대신
        HttpMessageConverter를 통해 http의 body에 내용을 직접 반환하게 됩니다.
        이 때 반환할 내용이 문자열인 경우 응답 데이터에 문자열 데이터를 그대로 반환하고,
        객체인 경우 응답 데이터에 객체 데이터를 JSON으로 변환하여 반환합니다.
    */

    @GetMapping("hello")
    /*
        호출 url : http://localhost:8080/hello
        url 호출시 hello.html가 있는지 검사하여 존재한다면 ${data}에 test라는 문자열을 대입합니다.
     */
    public String hello(Model model){
        model.addAttribute("data", "test");
        return "hello";
    }

    @GetMapping("hello-mvc")
    /*
        호출 url : http://localhost:8080/hello-mvc?name=Alice
        url 호출시 hello-template.html가 있는지 검사하여 존재한다면 ${name}에 Alice라는 문자열을 대입합니다.
     */
    public String hello(@RequestParam(value = "name", required = false) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    /*
        호출 url : http://localhost:8080/hello-string?name=Alice
        url 호출시 "hello Alice"를 출력합니다.
        @ResponseBody을 사용했으므로 html을 찾지 않습니다.
        반환할 내용이 문자열이므로 그대로 반환합니다.
     */
    public String hello(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    /*
        호출 url : http://localhost:8080/hello-api?name=Alice
        url 호출시 "{"name":"Alice"}"를 출력합니다.
        반환할 내용이 객체이므로 객체 -> JSON 과정을 거쳐 JSON 내용이 출력됩니다.
     */
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
