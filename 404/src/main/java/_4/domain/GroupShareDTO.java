package _4.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("groupShareDTO")
public class GroupShareDTO {
	String groupNum;
	String memNum;
	String memNickname;
	String memName;
	
	String message;
	

	    // 기본 생성자
	    public GroupShareDTO() {}

	    // String 값을 받는 생성자 추가
	    public GroupShareDTO(String message) {
	        this.message = message;
	    }

	    // Getter와 Setter
	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }
}
