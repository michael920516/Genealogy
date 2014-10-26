package com.example.genealogy.data;

public class Member {

	public int Serial;
	public String Name;
	public String Called;
	public String Birthday;
	public String Addr;
	public String Tel;
 	public int Life;

	public Member (int seri, String n,String c,String b,String t,String a,int l) {
		this.Serial = seri;
		this.Name = n;
		this.Called = c;		
		this.Birthday = b;
		this.Tel = t;
		this.Addr=a;
		this.Life = l;		
	}
}
