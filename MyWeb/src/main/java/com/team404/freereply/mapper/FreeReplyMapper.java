package com.team404.freereply.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.team404.command.ReplyVO;
import com.team404.util.Criteria;

public interface FreeReplyMapper {

	public int regist(ReplyVO vo); //댓글등록
	
	//public ArrayList<ReplyVO> getList(int bno); //목록처리
	public ArrayList<ReplyVO> getList(@Param("cri") Criteria cri, 
									  @Param("bno") int bno);//더보기목록처리 쿼리
	//2개 한꺼번에 쿼리문에 못담지만 @Param 사용해서 담음
	
	public int getTotal(int bno); //게시글에 따른 댓글수
	
	public int pwCheck(ReplyVO vo);//비밀번호 확인
	
	public int delete(ReplyVO vo);//삭제처리
	
	public int update(ReplyVO vo);//수정처리
}
