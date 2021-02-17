package es.vicenteqs.ecommercetest.util;

import java.util.Collection;

public class ListUtils {

	private ListUtils() {
		
	}
	
	public static boolean isEmpty(Collection<?> list) {
		boolean value = true;

		if (list != null && !list.isEmpty()) {
			value = false;
		}

		return value;
	}

}
