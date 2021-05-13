package com.hhgs.plantshows;



import com.hhgs.plantshows.mapper.avatar.BcsMapper;
import com.hhgs.plantshows.mapper.hh.NetAndPlanPowerMapper;
import com.hhgs.plantshows.mapper.mysqlAvatar4.AvatarBcsMapper;
import com.hhgs.plantshows.model.DO.Bcs;
import com.hhgs.plantshows.service.SaveDataFromHbaseToCSV;
import com.hhgs.plantshows.task.MonthNetPowerTask;
import com.hhgs.plantshows.util.HbaseUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PlantShowsApplicationTests {

	@Autowired
	private NetAndPlanPowerMapper netAndPlanPowerMapper;

	@Autowired
	private MonthNetPowerTask task;

	//@Autowired
	//private HbaseUtil util;

	@Autowired
	private BcsMapper bcsMapper;

	@Autowired
	private AvatarBcsMapper avatarBcsMapper;

	@Autowired
	private SaveDataFromHbaseToCSV saveDataFromHbaseToCSV;

	@Test
	public void data(){
		task.updateMonthNetPower();
	}

	@Test
	public void contextLoads() throws Exception {

		/*CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		String metaObjectName = "光电场.黄河特许共和光伏电站";

		HttpPost httpPost = new HttpPost("http://172.28.8.100/avatar/data/queryMetaObjData?metaObjectName="+metaObjectName);

		QueryObjectData queryObjectData = new QueryObjectData();

		queryObjectData.setForceFetch(0);
		queryObjectData.setOrFilter(1);
		queryObjectData.setQueryExpression("id='313633542324'");
		String jsonString = JSON.toJSONString(queryObjectData);
		StringEntity entity = new StringEntity(jsonString, "utf-8");

		httpPost.setEntity(entity);
		httpPost.setHeader("Content-Type", "application/json;charset=utf8");
		httpPost.setHeader("authorization","4A86F0071EDEA4CB30D178050FC109A653001E8880206366D4B868EAED84D9D9");
		//响应模型
		CloseableHttpResponse response = null;

		try {
			response = httpClient.execute(httpPost);
			HttpEntity responseEntity = response.getEntity();
			System.out.println("响应状态为:" + response.getStatusLine());
			if (responseEntity != null) {
				System.out.println("响应长度为:" + responseEntity.getContentLength());
				System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException  e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if(httpClient != null) {
                    httpClient.close();
                }
				if(response != null) {
                    response.close();
                }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
*/
	}

	@Test
	public void testHbaseQuery() {

		List<String> ids = new ArrayList<>();
		ids.add("318199566347");
		saveDataFromHbaseToCSV.saveDataToCSV("茶卡哇玉风电场",ids,"2020-11-12 00:00:00","2020-11-12 23:59:59");
	}

	@Test
	public void testMysqlAvatar4() {
		List<String> ids = new ArrayList<>();
		ids.add("189080801313");
		ids.add("455504564374");
		ids.add("249217036353");

		List<Bcs> bcsList = avatarBcsMapper.queryBcsByOrgId(ids);
		System.out.println(bcsList);
	}
}
