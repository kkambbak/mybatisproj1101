package com.woori.myapp.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.woori.myapp.entity.BoardDto;

//이객체를 만들어서 저장시에 스프링프레임워크한테 boardDao로 저장해주시고
//이거 달라고 할때 주세요  @Resource(name="boardDao")
@Repository("boardDao")
public class BoardDaoImpl implements BoardDao{

	//mybatis에 제공하는 SessionTemplate : 디비에 데이터 읽고 쓰기 담당
	@Autowired 
	SqlSessionTemplate sm; //DataConfig 파일에서 만든 객체를 전달한다. 
	
	@Override
	public List<BoardDto> getList(BoardDto dto) {
		
		// Board.xml 위 쿼리 아이디 "Board_getList" 
		//프로젝트를 통털어 하나만 있어야 한다 
		return sm.selectList("Board_getList", dto);
	}

	@Override
	public BoardDto getView(BoardDto dto) {
		
		return sm.selectOne("Board_getView", dto);
	}

	@Override
	public void insert(BoardDto dto) {
		sm.insert("Board_insert", dto);
	}
		
}
