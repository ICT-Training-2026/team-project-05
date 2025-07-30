package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.EmpAtData;
import com.example.demo.form.AtEditForm;
import com.example.demo.service.AtEditService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AtEditController {

	private final AtEditService service;
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
     // 時刻 (java.sql.Time) 用の変換
        SimpleDateFormat timeFmt = new SimpleDateFormat("HH:mm:ss");
        timeFmt.setLenient(false);
        binder.registerCustomEditor(java.sql.Time.class,
            new CustomDateEditor(timeFmt, true) {
                @Override
                public void setAsText(String text) {
                    if (text == null || text.trim().isEmpty()) {
                        setValue(null);
                    } else {
                        try {
                            java.util.Date parsed = timeFmt.parse(text);
                            setValue(new java.sql.Time(parsed.getTime()));
                        } catch (ParseException e) {
                            setValue(null);
                        }
                    }
                }
            });
    }
	
	/*--- 編集画面表示リクエスト ---*/
	@PostMapping("/at_show_edit")
	public String atShowEdit(@ModelAttribute AtEditForm form) {
		return "edit";
	}

	/*--- 更新リクエスト（編集画面より） ---*/
	@PostMapping("/edit")
	public String mtEdit(
		@Validated @ModelAttribute AtEditForm form,
		BindingResult result ) {

		if (result.hasErrors()) {
			return "edit";			// 入力がエラーの場合
		}

		return "edit_conf";		// 入力が正常の場合
	}

	/*--- 更新リクエスト（編集確認画面より） ---*/
	@PostMapping("/edit_conf")
	public String atConfirmEdit(
			@Validated @ModelAttribute AtEditForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes ) {

		if (result.hasErrors()) {
			return "edit";			// 入力がエラーの場合
		}

		EmpAtData ead = new EmpAtData();
		ead.setEmp_num(form.getEmp_num());
		ead.setDate(form.getDate());
		ead.setAt_type_code(form.getAt_type_code());
		ead.setSt_time(form.getSt_time());
		ead.setFn_time(form.getFn_time());
		//ead.setAwh_time(form.getAwh_time());
		ead.setRest_time(form.getRest_time());
		//ead.setOver_time(form.getOver_time());

		service.edit(ead);
			
		redirectAttributes.addFlashAttribute("msg", "更新");
			
		return "redirect:/complete";
	}

}
