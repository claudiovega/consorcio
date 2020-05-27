package cl.consorcio.dao;


import cl.consorcio.vo.Comuna;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service("farmaciasDAORest")
public class FarmaciasDAORest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FarmaciasDAORest.class);

    @Value("${url.minsal.comunas}")
    private String urlComunasMinsal;

    @Value("${url.minsal.locales}")
    private String urlMinsalLocales;

    public List<Comuna> callFarmaciasMapMinsal(String idRegion) throws Exception {

        LOGGER.info("INIT callFarmaciasMapMinsal, idRegion: ["+idRegion+"]");
        List<Comuna> lista = new ArrayList<Comuna>();

        try {

            String result = "";
            HttpPost post = new HttpPost(this.urlComunasMinsal);
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

                    String[] arrComuna = arr[i].split(">");
                    comuna.setId(arrComuna[0]);
                    comuna.setNombre(arrComuna[1]);
                    lista.add(comuna);

                }

            }

        } catch (Exception e) {
            LOGGER.error("ERROR: callFarmaciasMapMinsal "+e);
            throw e;
        }
        LOGGER.info("END callFarmaciasMapMinsal");
        return lista;
    }
    public String callFarmaciasByRegion(String idRegion) throws Exception {

        LOGGER.info("INIT callFarmaciasByRegion, idRegion: ["+idRegion+"]");
        String result = "";
        HttpGet request = new HttpGet(urlMinsalLocales+idRegion);
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(request);

            System.out.println(response.getProtocolVersion());              // HTTP/1.1
            System.out.println(response.getStatusLine().getStatusCode());   // 200
            System.out.println(response.getStatusLine().getReasonPhrase()); // OK
            System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                result = EntityUtils.toString(entity);
            }

        }catch(Exception e){
            LOGGER.error("ERROR: callFarmaciasByRegion "+e.getMessage());
            throw e;
        }
        LOGGER.info("END callFarmaciasByRegion");
        return result;
    }

}
