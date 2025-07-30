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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.entity.EmpAtData;
import com.example.demo.form.AtSearchForm;
import com.example.demo.service.AtSearchService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("empNum")
public class AtSearchController {

    private final AtSearchService atSearchService;
    
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

    
    @ModelAttribute("empNum")
    public String empNum() {
    	return "";
    }
    
    @GetMapping("/at_show_search")
    public String showRtList(@ModelAttribute("empNum") String empNum, Model model) {
        AtSearchForm form = new AtSearchForm();
        form.setEmp_num(empNum);
    	model.addAttribute("AtSearchForm", form); // 明示的に渡す
        return "at_search";
    }

    @PostMapping("/at_search")
    public String searchRt(@ModelAttribute("AtSearchForm") AtSearchForm form, @ModelAttribute("empNum") String empNum, Date date, String at_type_code , Model model) {
    	
    	List<EmpAtData> list = atSearchService.findByData(empNum,date,at_type_code); 
        
    	if(list.size()>0) {
        	model.addAttribute("EmpAtDataList", list);
        }
    	
    	model.addAttribute("AtSearchForm", form);
        
        return "at_search";
    }

}
