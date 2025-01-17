package _4.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("pointDTO")
public class PointDTO {
	String bookNum;
	Integer usedPoint;
	String memNum;
}
