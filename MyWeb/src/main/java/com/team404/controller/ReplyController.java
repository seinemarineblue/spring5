package com.team404.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team404.command.ReplyVO;
import com.team404.freereply.service.FreeReplyService;
import com.team404.util.Criteria;
import com.team404.util.ReplyPageVO;

@RestController
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	@Qualifier("freeReply")
	private FreeReplyService freeReplyService;

	//jackson-databind라이브러리 추가
	//댓글 등록
	@RequestMapping("/new")
	public int regist(@RequestBody ReplyVO vo) {
		
		int result = freeReplyService.regist(vo);
				
		return result;
	}
	
	//댓글 목록(url의 형태로 받기위해서 PathVariable)
	@RequestMapping("/getReply/{bno}/{pageNum}")
	public ReplyPageVO getReply(@PathVariable("bno") int bno,
									   @PathVariable("pageNum") int pageNum) {
		//ArrayList<ReplyVO> list = freeReplyService.getList(bno);
		//System.out.println(list.toString());
		
		//더보기(처리)
		//1. Criteria클래스에 pageNum와, 들고올 게시글 수 20개를 셋팅
		//2. getList메소드는 (cri, 게시글번호) 매개변수로 받는다.
		//3. 댓글쿼리를 변경(@Param 사용)
		//4. getTotal메소드로 게시글에 따른 댓글 수를 구해옴
		//5. ReplyPageVO에 list와 total을 담아서 반환
		//6. 화면에서는 더보기 조건처리
		Criteria cri = new Criteria(pageNum, 20);
		ReplyPageVO list = freeReplyService.getList(cri, bno);
		
		System.out.println(list.toString());
		return list;
	}
	
	//댓글 삭제
	@RequestMapping("/delete")
	public int delete(@RequestBody ReplyVO vo) {
		//비밀번호가 맞다면 1을 반환, 같지 않다면 0을 반환
		int result = freeReplyService.pwCheck(vo);
		if(result == 1) {
			return freeReplyService.delete(vo);
		} else {
			return 0;			
		}
	}
	
	//수정 처리
	@RequestMapping("/update")
	public int update(@RequestBody ReplyVO vo) {
		System.out.println(vo.toString());
		int result = freeReplyService.pwCheck(vo);
		if(result == 1) {
			return freeReplyService.update(vo);//성공하면1, 실패하면0 반환
		} else {
			return 0;			
		}
	}
	
	
	
}