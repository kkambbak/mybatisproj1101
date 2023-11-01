package com.woori.myapp.service;

import org.springframework.stereotype.Service;

import com.woori.myapp.entity.MemberDto;
import com.woori.myapp.repository.MemberDao;

import jakarta.annotation.Resource;

@Service("memberService")
public class MemberSeviceImpl implements MemberService {

	@Resource(name="memberDao")
	MemberDao dao;
	
	@Override
	public boolean idCheck(MemberDto dto) {
		return dao.idCheck(dto);
	}

	@Override
	public void insert(MemberDto dto) {
		dao.insert(dto);
	}
	
	@Override
	public MemberDto logon_proc(MemberDto dto) {
		MemberDto resultDto = dao.logon_proc(dto);
		
		return resultDto;
	}

}
