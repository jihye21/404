package _4.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("courseDTO")
public class CourseDTO {
	String courseNum;
	String memNum;
}
