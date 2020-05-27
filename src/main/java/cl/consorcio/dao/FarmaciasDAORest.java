package cl.consorcio.dao;


import cl.consorcio.vo.Comuna;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.FormBodyPartBuilder;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service("farmaciasDAORest")
public class FarmaciasDAORest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FarmaciasDAORest.class);


    public List<Comuna> callFarmaciasMapMinsal(String idRegion) throws Exception {
        List<Comuna> lista = new ArrayList<Comuna>();
        try {
            String result = "";
            HttpPost post = new HttpPost("https://midastest.minsal.cl/farmacias/maps/index.php/utilidades/maps_obtener_comunas_por_regiones");
            List<NameValuePair> urlParameters = new ArrayList<>();
            urlParameters.add(new BasicNameValuePair("reg_id", idRegion));


            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(post);

            result = EntityUtils.toString(response.getEntity());
            result = result.replace("<option value='0' selected>Elija Comuna</option>", "");
            result = result.replace("<option ", "");
            result = result.replace("'", "");
            result = result.replace("value=", "");
            if (!StringUtils.isEmpty(result)){
                String[] arr = result.split("</option>");

                for (int i = 0; i < arr.length; i++) {
                    Comuna comuna = new Comuna();
                    LOGGER.info(arr[i]);
                    String[] arrComuna = arr[i].split(">");
                    comuna.setId(arrComuna[0]);
                    comuna.setNombre(arrComuna[1]);
                    lista.add(comuna);

                }
                LOGGER.info("####################################");
            }



        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    public String callFarmaciasByRegion(String idRegion) throws Exception {
       String result = "";
        HttpGet request = new HttpGet("https://farmanet.minsal.cl/maps/index.php/ws/getLocalesRegion?id_region="+idRegion);

        // add request headers
//        request.addHeader("custom-key", "mkyong");
//        request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getProtocolVersion());              // HTTP/1.1
            System.out.println(response.getStatusLine().getStatusCode());   // 200
            System.out.println(response.getStatusLine().getReasonPhrase()); // OK
            System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // return it as a String
                result = EntityUtils.toString(entity);
                System.out.println(result);
            }

        }
        return result;
    }

}
