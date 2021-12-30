package com.mountain.controller;

import com.mountain.entity.*;
import com.mountain.service.MountainService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class QnaController {
    @Autowired
    MountainService mountainService;

    @GetMapping("qna") // qna게시판 리스트 페이지로 이동
    public String qnaBoard(Model model , Criteria cri) {


        int total = mountainService.getQnaTotal();

        PageMakerDto qnaPageMake = new PageMakerDto(cri, total);

        model.addAttribute("pageMaker", qnaPageMake);
        model.addAttribute("List", mountainService.qnaInfoPaging(cri));

        return "qna/qna_list";
    }

    @GetMapping("qna_write") // qna게시판 글 작성 페이지로 이동
    public String qnaBoard_write() {
        return "mountain/qnaWrite";
    }

    @PostMapping("qna_write") // qna 글 작성
    public String qnaBoard_write_post(QnaDto qnaDto, Model model, Image image, HttpSession session, Criteria cri) {

        // 이미지
        MultipartFile img01 = image.getImage();

        if(!img01.isEmpty()) {
            String filePath ="C:\\Users\\tj\\dk\\Team\\Team_Controller\\src\\main\\webapp\\resources\\qna_img";
            String fileName = img01.getOriginalFilename();

            try {
                img01.transferTo(new File(filePath, fileName));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 문의게시판 페이징


        int total = mountainService.getQnaTotal();

        PageMakerDto qnaPageMake = new PageMakerDto(cri, total);

        model.addAttribute("pageMaker", qnaPageMake);

        // 문의게시판 정보 담아와 DB에 삽입

        Long userId = (Long) session.getAttribute("userId");

        String result = mountainService.qnaInsert(qnaDto, userId);

        model.addAttribute("result", result);
        model.addAttribute("List", mountainService.qnaInfoPaging(cri));


        return "mountain/qnaList";
    }

    @GetMapping("qna_Modify") // qna게시판 글 수정 페이지로 이동
    public String qnaBoard_modify(@Param("qnaId") Long qnaId, @Param("uId") Long uId, Model model, HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        String result = "본인만 수정할 수 있습니다.";

        QnaUser qnaUser = mountainService.qnaSelectById(qnaId);

        if(userId != uId) {

            model.addAttribute("result", result);

            qnaUser = mountainService.qnaSelectById(qnaId);
            List<ReplyQnaAdmin> list = mountainService.qnaReplyList(qnaId);

            model.addAttribute("qna", qnaUser);
            model.addAttribute("replyList", list);

            return "mountain/qnaPost";

        }else {

            model.addAttribute("qna", qnaUser);
            return "mountain/qnaModify";

        }

    }

    @PostMapping("qna_modify") // qna게시판 수정
    public String qnaBoard_modify_result(QnaDto qnaDto, Model model, Image image, HttpSession session, Criteria cri) {

        // 이미지
        MultipartFile img01 = image.getImage();

        if(!img01.isEmpty()) {
            String filePath ="C:\\Users\\tj\\dk\\Team\\Team_Controller\\src\\main\\webapp\\resources\\qna_img";
            String fileName = img01.getOriginalFilename();

            try {
                img01.transferTo(new File(filePath, fileName));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 문의게시판 페이징

        Long userId = (Long) session.getAttribute("userId");

        qnaDto.setUserId(userId);

        int total = mountainService.getQnaTotal();

        PageMakerDto qnaPageMake = new PageMakerDto(cri, total);

        model.addAttribute("pageMaker", qnaPageMake);

        // 자유게시판 정보 담아와 DB 수정

        String result = mountainService.qnaUpdate(qnaDto);

        model.addAttribute("result", result);
        model.addAttribute("List", mountainService.qnaInfoPaging(cri));

        return "mountain/qnaList";
    }

    @GetMapping("qna_delete") // qna 글 삭제
    public String qna_delete(Long qnaId, HttpSession session, Model model, Criteria cri) {

        // qna 페이징

        int total = mountainService.getQnaTotal();

        PageMakerDto pageMake = new PageMakerDto(cri, total);

        model.addAttribute("pageMaker", pageMake);


        // 글 삭제

        Long userId = (Long) session.getAttribute("userId");

        if(userId != null) {

            String result = mountainService.qnaDelete(qnaId, userId);

            model.addAttribute("result", result);
            model.addAttribute("List", mountainService.qnaInfoPaging(cri));

            return "mountain/qnaList";

        } else {

            String result = "본인만 삭제할 수 있습니다.";

            model.addAttribute("result", result);
            model.addAttribute("List", mountainService.qnaInfoPaging(cri));


            return "mountain/qnaList";
        }
    }

    @GetMapping("qna_search") // qna게시판 검색
    public String qna_search(Model model, Criteria cri, @Param("select") String select, @Param("search") String search) {

        // qna게시판 페이징

        int total = mountainService.getQnaTotal();

        PageMakerDto pageMake = new PageMakerDto(cri, total);

        model.addAttribute("List", mountainService.qnaInfoPagingForSearch(select, search, cri));

        model.addAttribute("pageMaker", pageMake);

        return "mountain/qnaList";

    }

    @GetMapping("qna_reply") // qna 댓글 작성
    public String qna_reply_insert(ReplyQnaAdmin replyQnaAdmin, Long qnaId, Model model) {

        mountainService.qnaReplyInsert(replyQnaAdmin);

        QnaUser qnaOne = mountainService.qnaSelectById(qnaId);

        List<ReplyQnaAdmin> list = mountainService.qnaReplyList(qnaId);

        model.addAttribute("replyList", list);
        model.addAttribute("qna", qnaOne);

        return "mountain/qnaPost";

    }


    @GetMapping("qna_select") // qna 글로 이동
    public String qna_select(Long qnaId, Model model) {

        QnaUser qnaUser = mountainService.qnaSelectById(qnaId);
        List<ReplyQnaAdmin> list = mountainService.qnaReplyList(qnaId);

        model.addAttribute("qna", qnaUser);
        model.addAttribute("replyList", list);

        return "mountain/qnaPost";
    }

}
