import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
/**
 * 可以获取到自己的“总金额”/“历史累计收益”/“2013-12-07”收益等信息
 * @author FX8120
 *
 */
public class Test3 {
	public static void main(String[] args) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://financeprod.alipay.com/fund/asset.htm");
		CloseableHttpResponse httpResponse = null;
		//通过携带cookie信息，可以获取到baidu的一些个人信息
		httpGet.setHeader("Cookie", "JSESSIONID=2lNPYQPh358K$gf$aB3Z1o$AnlLwhBauth; ali_apache_id=10.228.236.10.1385821064476.6; unicard1.vm=\"K1iSL1gn5tN9WoztekQvpw==\"; cna=buEgCzY3KD0CAXJUedQO7ks9; mobileSendTime=-1; credibleMobileSendTime=-1; ctuMobileSendTime=-1; riskMobileBankSendTime=-1; riskMobileAccoutSendTime=-1; riskMobileCreditSendTime=-1; riskCredibleMobileSendTime=-1; overLimit=false; overLimitObj=; iw.userid=\"K1iSL1gn5tN9WoztekQvpw==\"; iw.nick=pQcKybZcdYsirrn5; iw.partner=\"Lfo3AA==\"; alipay=K1iSL1gn5tN9WoztekQvp51CcEoyzdp6P2405aY01Ez7atO07eSUtIby; ALIPAYJSESSIONID=2lNPYQPh358K$gf$aB3Z1o$AnlLwhBauth; ctoken=$iACsfvyb0Tn8wBnj7tyu2oLA$3nNG; LoginForm=trust_login_taobao; CLUB_ALIPAY_COM=2088102857615052; ali_apache_tracktmp=\"uid=2088102857615052\"; session.cookieNameId=ALIPAYJSESSIONID; ali_apache_sid=10.228.226.10.1386480774974.3|1386482649; JSESSIONID=0A5167B78916085C42169C3672D20F84; spanner=MTAuMjI4LjEzOS4yNDI6ODA=");
		httpGet.setHeader("Referer", "https://my.alipay.com/portal/home.htm");
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
