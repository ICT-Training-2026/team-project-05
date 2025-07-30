package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.PayLoginForm;
import com.example.demo.service.PayLoginService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final PayLoginService payLoginService;
	
    @PostMapping("/login2")
    public String showLoginForm(Model model) {
        model.addAttribute("PayLoginForm", new PayLoginForm());
        return "pay_login";
    }

    @PostMapping("/pay_login")
    public String login(
        @ModelAttribute("PayLoginForm") PayLoginForm form,
        BindingResult bindingResult,
        Model model
    ) {
        
        boolean result = payLoginService.findByEmpNumAndPass(
            form.getEmp_num(), form.getEmp_pass());
        if (result) {
        	model.addAttribute("empNum", form.getEmp_num());
            return "redirect:/pay_show_search";
        }
        
        model.addAttribute("error", "認証に失敗しました");
        return "pay_login";
        
    }
}

