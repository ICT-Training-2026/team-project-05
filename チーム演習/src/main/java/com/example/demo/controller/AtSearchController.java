package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.EmpAtData;
import com.example.demo.form.AtSearchForm;
import com.example.demo.service.AtSearchService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AtSearchController {

    private final AtSearchService atSearchService;
    
    @PostMapping("/at_show_search")
    public String showRtList(@ModelAttribute AtSearchForm form, Model model) {
        model.addAttribute("AtSearchForm", form); // 明示的に渡す
        return "at_search";
    }

    @PostMapping("/at_search")
    public String searchRt(@ModelAttribute AtSearchForm form, Model model) {
    	int empNum = form.getEmp_num(); // 社員番号（整数）を取得
    	
    	List<EmpAtData> list = atSearchService.findByNumber(empNum); 
        
    	if(list.size()>0) {
        	model.addAttribute("EmpAtDataList", list);
        }
    	
    	model.addAttribute("AtSearchForm", form);
        
        return "at_search";
    }
    
//    @PostMapping("/at_search")
//    public String showRtList(@ModelAttribute AtSearchForm form) {
//        return "at_search";
//    }
//    @PostMapping("/at_search")
//	private String atSearch(
//			@ModelAttribute AtSearchForm form,
//			Model model) {
//		//--テストデータ--
//		List<EmpAtData> list = new ArrayList<EmpAtData>();
//		list.add(new EmpAtData(123456,"2025/7/22", 34,"8:45", "17:45","8:00","1:00","0:00"));
//		list.add(new EmpAtData(123457,"2025/7/23", 34,"8:45", "17:45","8:00","1:00","0:00"));
//		list.add(new EmpAtData(123458,"2025/7/24", 34,"8:45", "17:45","8:00","1:00","0:00"));
//		//--テストデータ--
//		
//		model.addAttribute("EmpAtDataList", list);
//		
//		return "at_search";
//	}
}
