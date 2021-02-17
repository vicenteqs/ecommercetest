package es.vicenteqs.ecommercetest.util;

import org.springframework.web.util.UriComponents;

public class ApiUtils {

	private ApiUtils() {

	}

	public static String getBaseUrl(UriComponents uri) {
		String result = "";
		String scheme = uri.getScheme();
		String host = uri.getHost();
		int port = uri.getPort();

		if (scheme != null) {
			result = scheme + "://";
		}

		if (host != null) {
			result += host;
		}

		if (port != -1) {
			result += ":" + port;
		}

		result += "/";
		return result;
	}

}
