package com.woori.myapp.service;

import com.woori.myapp.entity.MemberDto;

public interface MemberService {
	boolean idCheck(MemberDto dto);
	void insert(MemberDto dto);
	MemberDto logon_proc(MemberDto dto);
}
