package bitek.board.file;
 
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;  
 
public class UploadedFileVO{  
   
 private MultipartFile file;  
 //�ѹ��� �ټ����� ������ ���� ���� List<MultipartFile> files �� ���� �����ϸ� �ȴ�
 private int fileNum;
 private String originfilename;
 private String changefilename;
 private Date uploaddate;

 
 public MultipartFile getFile() {  
  return file;  
 }  
   
 public void setFile(MultipartFile file) {  
  this.file = file;  
 }

public int getFileNum() {
	return fileNum;
}

public void setFileNum(int fileNum) {
	this.fileNum = fileNum;
}

public String getOriginfilename() {
	return originfilename;
}

public void setOriginfilename(String originfilename) {
	this.originfilename = originfilename;
}

public String getChangefilename() {
	return changefilename;
}

public void setChangefilename(String changefilename) {
	this.changefilename = changefilename;
}

public Date getUploaddate() {
	return uploaddate;
}

public void setUploaddate(Date uploaddate) {
	this.uploaddate = uploaddate;
}



}