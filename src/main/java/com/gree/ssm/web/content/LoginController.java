package com.gree.ssm.web.content;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ad")
public class LoginController {


    /**
     * 广告初始页面
     */
    @RequestMapping("/adlist")
    public String init(){
        System.out.println("tsteslkj");

        return "system/ad";

    }




}
