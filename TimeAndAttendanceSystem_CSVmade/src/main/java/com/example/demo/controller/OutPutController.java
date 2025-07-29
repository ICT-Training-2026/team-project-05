package com.example.demo.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.EmpAtDataSoumu;
import com.example.demo.form.OutPutForm;
import com.example.demo.service.OutPutService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OutPutController {

	private final OutPutService service;
	
	/*--- 編集画面表示リクエスト ---*/
	@PostMapping("/output_conf")
	public String showOutPut(@ModelAttribute OutPutForm form, Model model) { // Modelを引数に追加
		// @ModelAttributeはフォームのバインディングを行うが、
		// そのオブジェクトが必ずしもModelに自動で追加されるとは限らない（特にthymeleafのth:objectの場合）。
		// そのため、明示的にModelに追加する。
		model.addAttribute("OutPutForm", form); // OutPutFormオブジェクトをModelに追加
		return "output_conf";
	}
//	@PostMapping("/delete")
//	public String mtRemove(
//			@Validated @ModelAttribute AtDeleteForm form,
//			BindingResult result) {
//
//		if (result.hasErrors()) {
//			return "delete"; // 入力がエラーの場合
//		}
//
//		return "delete_conf"; // 入力が正常の場合
//	}

	/*--- 更新リクエスト（編集画面より） ---*/
	@PostMapping("/output_csv")
	public String outputCsv(
		@ModelAttribute OutPutForm form, HttpServletResponse response) throws IOException {
		response.setContentType("text/csv"); // コンテンツタイプをCSVに設定
		response.setHeader("Content-Disposition", "attachment; filename=\"employee_attendance.csv\""); // ダウンロードファイル名を指定
		response.setCharacterEncoding(StandardCharsets.UTF_8.name()); // 文字エンコーディングをUTF-8に設定

		try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8))) {
			// CSVヘッダー
			writer.println("社員番号,社員名,超過時間,合計時間"); // ヘッダー行を出力

			// OutPutFormからデータを取得し、EmpAtDataSoumuオブジェクトを作成
			EmpAtDataSoumu data = new EmpAtDataSoumu();
			data.setEmp_num(form.getEmp_num());
			data.setEmp_name(form.getEmp_name());
			data.setOver_time(form.getOver_time());
			data.setTotal_time(form.getTotal_time());
			
			// サービスを呼び出してCSVデータを書き込む
			service.outputCsv(List.of(data), writer);

		}
		return "redirect:/complete";
	}

}
