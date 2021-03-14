package com.DkGroup.jobFinder.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public  class Mapper {
	
	public Mapper() {
		
	}
	
	public static List<KeyValuePair> mapObjToMap(List<Object[]> list){

		List <KeyValuePair> map=new ArrayList<KeyValuePair>();
	    for (Object[] result : list) {
	        map.add(new KeyValuePair((BigInteger) result[0], result[1].toString()));
	    }
		return map;
	}
}
