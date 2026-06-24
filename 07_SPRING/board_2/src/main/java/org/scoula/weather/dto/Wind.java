package org.scoula.weather.dto;
import lombok.Data;

@Data
public class Wind{
	private Integer deg;
	private Object speed;
	private Object gust;
}
