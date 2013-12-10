import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
/**
 * µÇÂ¼Ö§¸¶±¦
 * @author FX8120
 *
 */
public class Test4 {
	public static void main(String[] args) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://auth.alipay.com/login/index.htm");
		CloseableHttpResponse httpResponse = null;
		HttpContext localContext = new BasicHttpContext();
		CookieStore cookies = new BasicCookieStore();
		localContext.setAttribute(ClientContext.COOKIE_STORE,cookies);
		try {
			httpResponse = httpClient.execute(httpGet, localContext);
			System.out.println(httpResponse.getStatusLine());

			HttpEntity entity1 = httpResponse.getEntity();
			String charset = Utils.getContentCharset(entity1);
			
			List<Cookie> cookieList = cookies.getCookies();
			for(int i=0; i<cookieList.size(); i++) {
				System.out.println(cookieList.get(i).toString()); 
			}
//			EntityUtils.consume(entity1);
			String respContent = EntityUtils.toString(entity1, charset);
			//InputStream inputStream = entity1.getContent();
			System.out.println(respContent);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpResponse != null) {
				try {
					httpResponse.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
