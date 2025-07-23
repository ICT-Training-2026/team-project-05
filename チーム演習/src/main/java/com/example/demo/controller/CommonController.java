package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

	/*--- メニュー画面表示 ---*/
	@GetMapping("/home")
	private String ShowHome() {
		return "home";
	}

	/*--- 完了後のリダイレクト先 ---*/
	@GetMapping("/complete")
	private String Complete() {
		return "complete";
	}

}
