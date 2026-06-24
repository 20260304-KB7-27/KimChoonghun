package org.scoula.weather.dto;
import lombok.Data;

@Data
public class Main{
	private Object temp;
	private Object tempMin;
	private Integer grndLevel;
	private Integer humidity;
	private Integer pressure;
	private Integer seaLevel;
	private Object feelsLike;
	private Object tempMax;
}
