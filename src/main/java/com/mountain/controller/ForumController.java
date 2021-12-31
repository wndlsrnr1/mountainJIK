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
//post 글 하나를 의미.

@Controller
public class ForumController {
    @Autowired
    MountainService mountainService;

    @GetMapping("forum") // 자유게시판 리스트 페이지로 이동
    public String freeBorad(Model model, Criteria cri) {

        // 자유게시판 페이징

        int total = mountainService.getTotal();

        PageMakerDto pageMake = new PageMakerDto(cri, total);

        model.addAttribute("List", mountainService.forumInfoPaging(cri));

        model.addAttribute("pageMaker", pageMake);

        return "forum/forum_list";
    }

    @GetMapping("forum/post/write") // 자유게시판 글 작성 페이지로 이동
    public String forum_write(HttpSession session, Model model, Criteria cri) {
        //로그인 하지 않았을 때.
        if(session.getAttribute("userId") == null) {

            String result = "로그인 해주세요.";

            model.addAttribute("List", mountainService.forumInfoPaging(cri));

            int total = mountainService.getTotal();

            PageMakerDto pageMake = new PageMakerDto(cri, total);

            model.addAttribute("result", result);
            model.addAttribute("pageMaker", pageMake);

            return "forward:";

        }else {

            return "forum/forum_write";

        }

    }

    @GetMapping("forum/post") // 자유게시판 글로 이동
    public String forum_select(Long forumId, Model model) {

        ForumUser forumOne = mountainService.forumSelectById(forumId);
        List<ReplyForumUser> list = mountainService.forumReplyList(forumId);

        model.addAttribute("forum", forumOne);
        model.addAttribute("replyList" ,list);

        return "forum/forum_post";
    }

    @PostMapping("forum/post/write") // 자유게시판 글 작성
    public String forum_write_post(ForumDto forumDto, Model model, Image image, HttpSession session, Criteria cri) {

        // 이미지
        MultipartFile img01 = image.getImage();

        if(!img01.isEmpty()) {
            String filePath ="C:\\Users\\tj\\dk\\Team\\Team_Controller\\src\\main\\webapp\\resources\\forum_img";
            String fileName = img01.getOriginalFilename();

            try {
                img01.transferTo(new File(filePath, fileName));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 자유게시판 페이징

        int total = mountainService.getTotal();

        PageMakerDto pageMake = new PageMakerDto(cri, total);

        model.addAttribute("pageMaker", pageMake);

        // 자유게시판 정보 담아와 DB에 삽입

        Long userId = (Long) session.getAttribute("userId");

        String result = mountainService.forumInsert(forumDto, userId);

        model.addAttribute("result", result);
        model.addAttribute("List", mountainService.forumInfoPaging(cri));

        return "forum/forum_list";

    }

    @GetMapping("forum/post/modify") // 자유게시판 글 수정 페이지로 이동
    public String forum_modify(@Param("forumId") Long forumId, @Param("uId") Long uId, Model model, HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        ForumUser forumOne = mountainService.forumSelectById(forumId);

        model.addAttribute("forum", forumOne);

        String result = "본인만 수정할 수 있습니다.";

        if(uId != userId) {

            model.addAttribute("result", result);

            forumOne = mountainService.forumSelectById(forumId);
            List<ReplyForumUser> list = mountainService.forumReplyList(forumId);

            model.addAttribute("forum", forumOne);
            model.addAttribute("replyList" ,list);

            return "forum/forum_post";

        }else {

            return "forum/forum_modify";

        }

    }

    @PostMapping("forum/post/modify") // 자유게시판 글 수정
    public String forum_modifyResult(ForumDto forumDto, Model model, Image image, HttpSession session, Criteria cri) {

        // 이미지
        MultipartFile img01 = image.getImage();

        if(!img01.isEmpty()) {
            String filePath ="C:\\Users\\tj\\dk\\Team\\Team_Controller\\src\\main\\webapp\\resources\\forum_img";
            String fileName = img01.getOriginalFilename();

            try {
                img01.transferTo(new File(filePath, fileName));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 자유게시판 페이징

        int total = mountainService.getTotal();

        PageMakerDto pageMake = new PageMakerDto(cri, total);

        model.addAttribute("pageMaker", pageMake);

        // 자유게시판 정보 담아와 DB 수정

        Long userId = (Long) session.getAttribute("userId");

        forumDto.setUserId(userId);

        String result = mountainService.forumUpdate(forumDto);

        model.addAttribute("result", result);
        model.addAttribute("List", mountainService.forumInfoPaging(cri));

        return "forum/forum_list";

    }

    @GetMapping("forum/post/delete") // 자유게시판 글 삭제
    public String forum_delete(Long forumId, HttpSession session, Model model, Criteria cri) {

        // 자유게시판 페이징

        int total = mountainService.getTotal();

        PageMakerDto pageMake = new PageMakerDto(cri, total);

        model.addAttribute("pageMaker", pageMake);

        Long userId = (Long)session.getAttribute("userId");

        // 글 삭제

        if(userId != null) {

            String result = mountainService.forumDelete(forumId, userId);

            model.addAttribute("result", result);
            model.addAttribute("List", mountainService.forumInfoPaging(cri));

            return "forum/forum_list";

        } else {

            String result = "본인만 삭제할 수 있습니다.";

            model.addAttribute("result", result);
            model.addAttribute("List", mountainService.forumInfoPaging(cri));

            return "forum/forum_list";
        }
    }

    @GetMapping("forum/post/search") // 자유게시판 검색
    public String forum_search(Model model, Criteria cri, @Param("select") String select, @Param("search") String search) {
        // 자유게시판 페이징
        int total = mountainService.getTotal();

        PageMakerDto pageMake = new PageMakerDto(cri, total);

        model.addAttribute("List", mountainService.forumInfoPagingForSearch(select, search, cri));

        model.addAttribute("pageMaker", pageMake);

        return "forum/forum_list";

    }

    @GetMapping("forum/reply") // 자유게시판 댓글 작성
    public String forum_reply_insert(ReplyForumDto replyForumDto, Long forumId, Model model, HttpSession session) {

        mountainService.forumReplyInsert(replyForumDto, session);

        ForumUser forumOne = mountainService.forumSelectById(forumId);

        List<ReplyForumUser> list = mountainService.forumReplyList(forumId);

        model.addAttribute("forum", forumOne);
        model.addAttribute("replyList" ,list);

        return "forum/forum_list";
    }

    @GetMapping("form_reply_value") // 자유게시판 댓글 하나 가져오기 -> 수정하기 위함
    public String forum_reply_one(HttpSession session, Long id, Long forumId, Model model) {

        Long userId = (Long) session.getAttribute("userId");

        ReplyForumDto reply = mountainService.forumReplyByUserId(userId, id, forumId);

        String result = "댓글을 수정할 수 없습니다.";

        if(reply == null) {

            model.addAttribute("result", result);
            model.addAttribute("reply", reply);

            ForumUser forumOne = mountainService.forumSelectById(forumId);
            List<ReplyForumUser> list = mountainService.forumReplyList(forumId);

            model.addAttribute("forum", forumOne);
            model.addAttribute("replyList" ,list);

            return "forum/forum_post";

        } else {

            model.addAttribute("reply", reply);

            return "forum/forum_post_modify";

        }

    }

    @GetMapping("forum/reply/modify") // 자유게시판 댓글 수정
    public String forum_reply_modify(ReplyForumDto replyForumDto, Model model) {

        mountainService.forum_reply_update(replyForumDto);

        ForumUser forumOne = mountainService.forumSelectById(replyForumDto.getForumId());
        List<ReplyForumUser> list = mountainService.forumReplyList(replyForumDto.getForumId());

        model.addAttribute("forum", forumOne);
        model.addAttribute("replyList" ,list);

        return "forum/forum_post";
    }

    @GetMapping("forum/reply/delete") // 자유게시판 댓글 하나 삭제하기
    public String forum_reply_delete_one(HttpSession session, Model model, Long id, Long forumId) {

        Long userId = (Long) session.getAttribute("userId");

        String result = mountainService.forum_reply_delete_one(forumId, userId, id);

        if(result.equals("댓글을 삭제할 수 없습니다.")) {

            model.addAttribute("result", result);

            ForumUser forumOne = mountainService.forumSelectById(forumId);
            List<ReplyForumUser> list = mountainService.forumReplyList(forumId);

            model.addAttribute("forum", forumOne);
            model.addAttribute("replyList" ,list);

            return "forum/forum_post";

        }else {

            ForumUser forumOne = mountainService.forumSelectById(forumId);
            List<ReplyForumUser> list = mountainService.forumReplyList(forumId);

            model.addAttribute("forum", forumOne);
            model.addAttribute("replyList" ,list);

            return "forum/forum_post";

        }
    }

}
