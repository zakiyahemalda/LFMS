package model;

public class Shelves {
	
	private String shelvesNo;
	private int totalSize;
	private int totalAvailableSize;
	private String location;
	
	public String getShelvesNo() {
		return shelvesNo;
	}
	
	public void setShelvesNo(String shelvesNo) {
		this.shelvesNo = shelvesNo;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getTotalAvailableSize() {
		return totalAvailableSize;
	}

	public void setTotalAvailableSize(int totalAvailableSize) {
		this.totalAvailableSize = totalAvailableSize;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
