package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.EmpAtData;
import com.example.demo.form.AtDeleteForm;
import com.example.demo.service.AtDeleteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AtDeleteController {

	private final AtDeleteService service;
	
	/*--- 削除リクエスト（一覧画面より） ---*/
	@PostMapping("/delete")
	public String mtRemove(
			@Validated @ModelAttribute AtDeleteForm form,
			BindingResult result) {

		if (result.hasErrors()) {
			return "delete"; // 入力がエラーの場合
		}

		return "delete_conf"; // 入力が正常の場合
	}

	/*--- 削除リクエスト（削除確認画面より） ---*/
	@PostMapping("/delete_conf")
	public String confirmRemoveRt(
			@Validated @ModelAttribute AtDeleteForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "delete"; // 入力がエラーの場合
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

		service.remove(ead);

		redirectAttributes.addFlashAttribute("msg", "削除");

		return "redirect:/complete";
	}
}
