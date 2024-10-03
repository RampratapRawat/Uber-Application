package com.example;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import com.example.Dto.PointDto;

public class GeometryUtil {
      
	public static Point createPoint(PointDto pointDto) {
		GeometryFactory geometryFactory=new GeometryFactory(new PrecisionModel(),4326);//SRID(simple resource ID :4326)
		Coordinate coordinate=new Coordinate(pointDto.getCoordinates()[0],
				pointDto.getCoordinates()[1]);
		return geometryFactory.createPoint(coordinate);
	}
}
