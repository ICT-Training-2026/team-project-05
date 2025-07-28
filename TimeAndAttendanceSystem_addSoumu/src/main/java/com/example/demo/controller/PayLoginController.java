package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.form.AtLoginForm;
import com.example.demo.service.AtLoginService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("empNum")
public class PayLoginController {
	
	private final AtLoginService atLoginService;
	
	@ModelAttribute("empNum")
	public String empNum(){
		return "";
	}

    @PostMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("AtLoginForm", new AtLoginForm());
        return "at_login";
    }

    @PostMapping("/at_login")
    public String login(
        @ModelAttribute("AtLoginForm") AtLoginForm form,
        BindingResult bindingResult,
        Model model
    ) {
        
        boolean result = atLoginService.findByEmpNumAndPass(
            form.getEmp_num(), form.getEmp_pass());
        if (result) {
        	model.addAttribute("empNum", form.getEmp_num());
            return "redirect:/at_show_search";
        }
        
        model.addAttribute("error", "認証に失敗しました");
        return "at_login";
        
    }
}

