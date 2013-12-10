import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;


public class Utils {

	public static String getString(InputStream is) throws Exception {
		if(is == null) return null;
		byte[] b = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while((is.read(b)) > -1) {
			baos.write(b);
		}
		byte[] by = baos.toByteArray();
		baos.close();
		return new String(by);
	}
	

	public static String getContentCharset(HttpEntity entity) {
		if(entity == null) {
			throw new IllegalArgumentException("http entity is null");
		}
		String charset = null;
		if(entity.getContentType() != null) {
			HeaderElement[] valuesElements = entity.getContentType().getElements();
			if(valuesElements != null && valuesElements.length > 0) {
				NameValuePair param = valuesElements[0].getParameterByName("charset");
				if(param != null) {
					charset = param.getValue();
				}
			}
		}
		if(charset == null) {
			charset = "utf-8";
		}
		return charset;
	}	
}
