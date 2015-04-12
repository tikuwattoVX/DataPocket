package com.example.datapocket.item;

public class GenreDataItem {
	
	String title;
	String msg;
	String author;
	  
	public GenreDataItem(String title, String msg, String author){
	  this.title = title;
	  this.msg = msg;
	  this.author = author;
	}
	  
	public String getTitle(){
	  return title;
	}
	  
	public String getISBN(){
	  return msg;
	}
	  
	public String getAuthor(){
	  return author;
	}
	  
	public String toString(){
	  return title + ":" + msg + ":" + author;
	}
}



