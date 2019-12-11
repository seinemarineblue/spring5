package com.team404.freereply.service;

import java.util.ArrayList;

import com.team404.command.ReplyVO;
import com.team404.util.Criteria;
import com.team404.util.ReplyPageVO;

public interface FreeReplyService {
	
	public int regist(ReplyVO vo); //댓글등록
	
	//public ArrayList<ReplyVO> getList(int bno); //목록처리
	public ReplyPageVO getList(Criteria cri, int bno);//더보기 목록처리
	
	public int pwCheck(ReplyVO vo);//비밀번호확인
	
	public int delete(ReplyVO vo);//삭제처리
	
	public int update(ReplyVO vo);//수정처리
	
}
