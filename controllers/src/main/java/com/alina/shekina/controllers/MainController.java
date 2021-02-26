package com.alina.shekina.controllers;

import com.alina.shekina.userDetails.UserDetailsImpl;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;


@Controller
@Setter(onMethod_ = @Autowired)
public class MainController {


    @GetMapping(value = {"/hotel","/"})
    public String hello(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model){
        if (userDetails != null){
            model.addAttribute("user",userDetails.getUser());
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            boolean admin = authorities.contains(new SimpleGrantedAuthority("ADMIN"));
            model.addAttribute("userAuthorities", admin);
        } else {
            model.addAttribute("user",null);

        }
        return "index";
    }

    @GetMapping(value = "/rooms")
    public String getRooms(){
        return "hotelRoomsInfo";
    }

    @GetMapping(value = "/leisure")
    public String getLeisure(){return "leisureInfo";}

    @GetMapping(value = "/facilities")
    public String getFacilities(){return "facilitiesInfo";}

}
