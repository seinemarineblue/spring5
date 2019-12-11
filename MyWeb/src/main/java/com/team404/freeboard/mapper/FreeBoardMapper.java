package com.team404.freeboard.mapper;

import java.util.ArrayList;

import com.team404.command.FreeBoardVO;
import com.team404.util.Criteria;

public interface FreeBoardMapper {

	public void regist(FreeBoardVO vo); //등록
	//public ArrayList<FreeBoardVO> getList(); //일반
	//기본페이징
	//public ArrayList<FreeBoardVO> getList(Criteria cri); //기본페이징
	//public int getTotal(); //전체 게시글 수
	//검색페이징
	public ArrayList<FreeBoardVO> getList(Criteria cri); //기본페이징
	public int getTotal(Criteria cri); //전체 게시글 수
	
	public FreeBoardVO content(int bno); //상세보기
	public boolean update(FreeBoardVO vo);//업뎃
	public boolean delete(int bno);//삭제
	
	
}
