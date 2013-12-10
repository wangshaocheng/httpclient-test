import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Test2 {
	public static void main(String[] args) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://login.taobao.com/member/login.jhtml?tpl_redirect_url=https%3A%2F%2Fauth.alipay.com%2Flogin%2FtrustLoginResultDispatch.htm%3FredirectType%3D%26sign_from%3D3000%26goto%3Dhttps%253A%252F%252Flab.alipay.com%252Fuser%252Fi.htm%253Fsrc%253Dyy_content_jygl&from_alipay=1");
		CloseableHttpResponse httpResponse = null;
		//通过携带cookie信息，可以获取到baidu的一些个人信息
		httpGet.setHeader("Cookie", "_umdata=B7433E5E20FF75096F1BCECCD187D420C4F19B4007B5F8EC1B9DF45B48BCA229331FD78923482F042009E1A40AE44CC6CFC819EAD70D0F2F8E89EC67FEDA8369796E9ADBFD4C7772; miid=2999176088067958132; t=a08f0d362096468a987ab21cee7bbc22; mt=np=&ci=0_1&cyk=0_0; cna=buEgCzY3KD0CAXJUedQO7ks9; lid=%E5%AE%B6%E7%9A%84%E6%A2%A6%E6%83%B3%E6%97%B6%E5%88%86; lc=UIWq%2F4cI5yPgcpTJbfxKuhM%3D; tg=0; _cc_=VFC%2FuZ9ajQ%3D%3D; tracknick=%5Cu5BB6%5Cu7684%5Cu68A6%5Cu60F3%5Cu65F6%5Cu5206; uc3=nk2=30tDpyjykIOi3yyV&id2=UU2y8txFggE%2B&vt3=F8dHqKcTgw2k1S98%2BJ4%3D&lg2=W5iHLLyFOGW7aA%3D%3D; lgc=%5Cu5BB6%5Cu7684%5Cu68A6%5Cu60F3%5Cu65F6%5Cu5206; cookie2=343975860a67d4f2031974112dcd976d; uc1=cookie14=UoLU7Rifotk1IA%3D%3D; v=0");
		
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
