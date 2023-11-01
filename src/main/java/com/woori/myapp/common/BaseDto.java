package com.woori.myapp.common;

public class BaseDto {
	protected int pg;  //페이지번호
	protected int rnum; //seq-게시물식별값, 정리된번호
	protected String searchKey=""; //검색에 사용
	protected String searchText="";
	
	public int getPg() {
		return pg;
	}
	public void setPg(int pg) {
		this.pg = pg;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
	
}
