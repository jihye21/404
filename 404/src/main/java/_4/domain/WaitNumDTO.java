package _4.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("waitNumDTO")
public class WaitNumDTO {
	String storeNum;
	String bookNum;
	Integer waitNum;
}
