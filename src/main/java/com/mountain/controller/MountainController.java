package com.mountain.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mountain.entity.Criteria;
import com.mountain.entity.DifficultyDto;
import com.mountain.entity.EntranceDto;
import com.mountain.entity.ForumDto;
import com.mountain.entity.ForumUser;
import com.mountain.entity.Image;
import com.mountain.entity.PageMakerDto;
import com.mountain.entity.QnaDto;
import com.mountain.entity.QnaUser;
import com.mountain.entity.ReplyForumDto;
import com.mountain.entity.ReplyForumUser;
import com.mountain.entity.ReplyQnaAdmin;
import com.mountain.entity.SanDto;
import com.mountain.entity.User;
import com.mountain.service.MountainService;

@Controller
public class MountainController {

	@Autowired
	MountainService mountainService;

	@GetMapping("mainlist") // ajax 테스트
	@ResponseBody
	public List<ForumUser> mainPost() {

		List<ForumUser> list =  mountainService.forumList();

		return list;
	}

	//주인국 추가내용
	@GetMapping("san/getSrc")
	public @ResponseBody String giveSrc(@RequestParam String id, Model model) {
		return mountainService.getImageSource(id);
	}

	//입구 정보 받기.
	@PostMapping("san/entrance/list")
	public @ResponseBody List<EntranceDto> getEntranceList(@RequestParam String sanId, Model model){
		Long sanIdLong = Long.parseLong(sanId);
		return mountainService.getEntranceList(sanIdLong);
	}

	//주인국: 산 리스트 ajax로 보내주기.
	@PostMapping("san/list")
	public @ResponseBody
	List<SanDto> mountain_mainReturnSanData(){
		return mountainService.getSanList();
	}

	@GetMapping("san/mountain") // 산 개별 페이지으로 이동
	public String mountain_page(@RequestParam String id, Model model) { // 산 ID를 파라미터로 받아 페이지 이동
		if(id == null){
			id = "1";
		}else {
			Long idLong = Long.parseLong(id);
			SanDto san = mountainService.selectById(idLong);
			model.addAttribute("san", san);

			List<DifficultyDto> difficultyList = mountainService.levelList(idLong);

			model.addAttribute("difficultyList", difficultyList);
		}
		return "san/each_mountain";
	}

	@GetMapping("main") // 메인 페이지로 이동
	public String main() {
		return "mountain/main";
	}


	// 산

	@GetMapping("san")
	public String mountain_main(Model model) {
		String soeulBoundJson = mountainService.getSoeulBoundJson();
		model.addAttribute("soeulBoundJson", soeulBoundJson);
		return "san/mountains";

	}


	//	@GetMapping("mountain_page") // 산 개별 페이지으로 이동
	//	public String mountain_page(Long id, Model model) { // 산 ID를 파라미터로 받아 페이지 이동
	//
	//		SanDto san = mountainService.selectById(id);
	//
	//		model.addAttribute("san", san);
	//
	//		return "mountain/mountain_page";
	//	}

	// 게시판

	// qna게시판


	// 자유게시판


	@PostMapping("difficulty/write") // 난이도 작성
	public String levelInsert(DifficultyDto difficultyDto, Model model, Long sanId, Long userId, HttpSession session) {

		String result = mountainService.levelInsert(difficultyDto, sanId, session);

		SanDto san = mountainService.selectById(sanId);

		model.addAttribute("san", san);

		List<DifficultyDto> difficultyList = mountainService.levelList(sanId);

		model.addAttribute("difficultyList", difficultyList);

		model.addAttribute("result", result);

		return "san/each_mountain";

	}

}
