package _4.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("courseDetailDTO")
public class CourseDetailDTO {
	String courseNum;
	Integer courseOrder;
	String storeNum;
	String memberNum;
	String storeName;
}
