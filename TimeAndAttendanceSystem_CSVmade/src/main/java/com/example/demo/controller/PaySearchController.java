package com.example.demo.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.EmpAtDataSoumu;
import com.example.demo.form.PaySearchForm;
import com.example.demo.service.PaySearchService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PaySearchController {

    private final PaySearchService paySearchService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        fmt.setLenient(false);
        binder.registerCustomEditor(java.sql.Date.class,
            new CustomDateEditor(fmt, true) {
                @Override
                public void setAsText(String text) {
                    if (text == null || text.trim().isEmpty()) {
                        setValue(null);
                    } else {
                        try {
                            java.util.Date parsed = fmt.parse(text);
                            setValue(new java.sql.Date(parsed.getTime()));
                        } catch (ParseException e) {
                            setValue(null);
                        }
                    }
                }
            }
        );
    }

    
    @GetMapping("/pay_show_search")
    public String showRtList(@ModelAttribute PaySearchForm form, Model model) {
    	model.addAttribute("PaySearchForm", form); // 明示的に渡す
        return "pay_search";
    }

    @PostMapping("/pay_search")
    public String searchRt(@ModelAttribute PaySearchForm form, Model model) {
    	
    	String team_code = form.getTeam_code();
    	String empNum = form.getEmp_num();
    	Date date = form.getDate();
    	String atTypeCode = form.getAt_type_code();
    	
    	List<EmpAtDataSoumu> list = paySearchService.findByData(team_code,empNum,date,atTypeCode); 
        
    	if(list.size()>0) {
        	model.addAttribute("EmpAtDataSoumuList", list);
        }
    	
    	model.addAttribute("PaySearchForm", form);
        
        return "pay_search";
    }

}
