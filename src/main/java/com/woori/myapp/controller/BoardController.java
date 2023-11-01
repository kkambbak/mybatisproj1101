package com.woori.myapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.woori.myapp.common.Pager;
import com.woori.myapp.entity.BoardDto;
import com.woori.myapp.repository.BoardDao;

import jakarta.annotation.Resource;

@Controller
public class BoardController {

	@Resource(name="boardDao")
	BoardDao dao;
	
	@GetMapping("/board/list/{pg}")
	public String board_list(Model model, BoardDto dto, 
			@PathVariable("pg")int pg)
	{
		String page = Pager.makePage(10, 100, pg);//한페이지당표출될데이터개수, 전체개수, 현재페이지
		dto.setPg(pg);
		
		List<BoardDto> list = dao.getList(dto);
//		for(int i=0; i<list.size(); i++)
//		{
//			System.out.println(list.get(i).getTitle()); 
//		}
		  
		//model - request 객체를 스프링에서 좀더 업그레이드  
		model.addAttribute("boardList", list);
		model.addAttribute("page", page);////////// html로 정보를 보내자 
		return "/board/board_list";
	}
	
	@GetMapping("/board/view/{id}")
	public String board_view(Model model, @PathVariable("id")int id)
	{
		BoardDto dto = new BoardDto();
		dto.setSeq(id );
		BoardDto resultDto = dao.getView(dto);
		
		//model - request 객체를 스프링에서 좀더 업그레이드  
		model.addAttribute("board", resultDto);
		
		return "/board/board_view";
	}
	
}



