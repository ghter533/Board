package bitek.board.test;

import java.sql.Date;

public class boardVO {
	private String id;
	private String title;
	private String contents;
	private int boardNum;
	private int page;
	private Date boarddate;
	private int hit;
	private boolean deleteOk;
	private boolean updateOk;
	private int fileNum;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public Date getBoarddate() {
		return boarddate;
	}
	public void setBoarddate(Date boarddate) {
		this.boarddate = boarddate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public boolean isDeleteOk() {
		return deleteOk;
	}
	public void setDeleteOk(boolean deleteOk) {
		this.deleteOk = deleteOk;
	}
	public boolean isUpdateOk() {
		return updateOk;
	}
	public void setUpdateOk(boolean updateOk) {
		this.updateOk = updateOk;
	}
	public int getFileNum() {
		return fileNum;
	}
	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}
	
	
	
}
