import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Test1 {
	public static void main(String[] args) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://www.baidu.com/p/wenwujuncheng?from=super");
		CloseableHttpResponse httpResponse = null;
		//通过携带cookie信息，可以获取到baidu的一些个人信息
		httpGet.setHeader("Cookie", "	BAIDUID=14642727EC4D66B13419DD08992FC3F0:FG=1; bdshare_firstime=1385861612940; NBID=86414088DAE913DBB828C8D0E563093D:FG=1; BDUSS=npEcGpyaVYwaEVaYUpCSGpvVThCU2htUVp3MHRLflcxRncxdkMzNXljd3p0TVpTQVFBQUFBJCQAAAAAAAAAAAEAAACBc5YOd2Vud3VqdW5jaGVuZwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADMnn1IzJ59Sb; cflag=65535%3A1; BD_TMP_CK=true");
		
		try {
			httpResponse = httpClient.execute(httpGet);
			System.out.println(httpResponse.getStatusLine());

			HttpEntity entity1 = httpResponse.getEntity();
			String charset = Utils.getContentCharset(entity1);
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
