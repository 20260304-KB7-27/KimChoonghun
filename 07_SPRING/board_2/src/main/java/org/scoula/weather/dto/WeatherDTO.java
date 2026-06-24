package org.scoula.weather.dto;

import java.util.List;
import lombok.Data;

@Data
public class WeatherDTO{
	private Integer visibility;
	private Integer timezone;
	private Main main;
	private Clouds clouds;
	private Sys sys;
	private Integer dt;
	private Coord coord;
	private List<WeatherItem> weather;
	private String name;
	private Integer cod;
	private Integer id;
	private String base;
	private Wind wind;
}