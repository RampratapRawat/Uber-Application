package com.example.Service;

import org.locationtech.jts.geom.Point;

public interface DistanceService {
           
	  double calculate(Point source,Point destination);
}
