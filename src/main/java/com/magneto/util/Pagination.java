package com.magneto.util;

public class Pagination {
	int pageNumber = 1;
	int pageSize = 10;
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize<0)
			throw new IllegalArgumentException("IllegalArgumentException if the argument is negative") ;
		
			this.pageSize = pageSize;
		
	}
	
}
