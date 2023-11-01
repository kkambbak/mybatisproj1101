package com.woori.myapp.repository;

import com.woori.myapp.entity.MemberDto;

public interface MemberDao {
	boolean idCheck(MemberDto dto);
	void insert(MemberDto dto);
	MemberDto logon_proc(MemberDto dto);
}
