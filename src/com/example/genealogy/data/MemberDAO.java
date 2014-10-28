package com.example.genealogy.data;

public interface MemberDAO {

	public int add(Member m);
	public Member[] getAll();
	public Member[] search(String keyword);
	public void removeAll();
	public void delete(int ID);
	public void edit(Member m);
	public Member getMember(int ID);
	public Member[] searchCall(String keyword);
	
	
	
}
