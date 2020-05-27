package cl.consorcio;

import cl.consorcio.controller.FarmaciasController;
import cl.consorcio.dao.FarmaciasDAORest;
import cl.consorcio.helper.FarmaciasHelper;
import cl.consorcio.vo.Comuna;
import cl.consorcio.vo.ResponseVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ConsorcioApplicationTests {

	@Autowired
	FarmaciasController farmaciasController;

	@Autowired
	FarmaciasDAORest farmaciasDAORest;

	@Autowired
	FarmaciasHelper farmaciasHelper;
	private static Logger logger = LogManager.getLogger(ConsorcioApplicationTests.class);
	@Test
	void contextLoads() {
		try {
			farmaciasHelper.getFarmaciasByIdComuna("7","127","san nicolas");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
