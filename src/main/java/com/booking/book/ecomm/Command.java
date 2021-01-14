package com.booking.book.ecomm;

import com.booking.book.entity.Order;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

@Service
public class Command {

    private final ConnectIT connector;

    HttpPost merchantHandler = new HttpPost("https://ecommerce.ufc.ge:18443/ecomm2/MerchantHandler");
    HttpPost clientHandler = new HttpPost("https://ecommerce.ufc.ge:18443/ecomm2/MerchantHandler");

    public Command(ConnectIT connector) {
        this.connector = connector;
    }

    public void chargeMoney(Order order) throws IOException, UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        //getting connection with certificate
        CloseableHttpClient httpclient = connector.sslConnect();

        //adding parameters to the url...
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("command", "v"));
        params.add(new BasicNameValuePair("amount", String.valueOf(order.getAmount())));
        params.add(new BasicNameValuePair("currency", "981"));
        params.add(new BasicNameValuePair("client_ip_addr", "10.12.12.2"));
        params.add(new BasicNameValuePair("language", "EN"));
        params.add(new BasicNameValuePair("description", "Order : " + order.getId()));
        params.add(new BasicNameValuePair("msg_type", "SMS"));

        //encoding parameters
        UrlEncodedFormEntity entity1 = new UrlEncodedFormEntity(params, "UTF-8");

        //setting encoded parameters to the url...
        merchantHandler.setEntity(entity1);

        //Execute and get the response.
        CloseableHttpResponse response = httpclient.execute(merchantHandler);


        HttpEntity entity = response.getEntity();

        if (entity != null) {

            //response body in string format
            String merchantHandlerResponse = EntityUtils.toString(entity);

            //get tran id from response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(merchantHandlerResponse);
            String transaction_id = jsonNode.get("TRANSACTION_ID").asText();

            //set tran id to this order...
            order.setTransactionId(transaction_id);

            List<NameValuePair> tranId = new ArrayList<>();
            tranId.add(new BasicNameValuePair("trans_id", order.getTransactionId()));
            UrlEncodedFormEntity entity2 = new UrlEncodedFormEntity(tranId, "UTF-8");
            clientHandler.setEntity(entity2);

            //Post to client handler
            CloseableHttpResponse clientHandlerResponse = HttpClients.createDefault().execute(clientHandler);


        }
    }


    public void checkTranStatus(String tranId) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        CloseableHttpClient httpclient = connector.sslConnect();
        String encodedTranId = URLEncoder.encode(tranId, StandardCharsets.UTF_8);

        HttpPost merchantHandler = new HttpPost("https://ecommerce.ufc.ge:18443/ecomm2/MerchantHandler");

        List<NameValuePair> check = new ArrayList<>();
        check.add(new BasicNameValuePair("command", "c"));
        check.add(new BasicNameValuePair("trans_id", encodedTranId));
        check.add(new BasicNameValuePair("client_ip_addr", "10.12.12.2"));
        UrlEncodedFormEntity entity1 = new UrlEncodedFormEntity(check, "UTF-8");
        merchantHandler.setEntity(entity1);

        CloseableHttpResponse response = httpclient.execute(merchantHandler);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            //response body in string format
            String merchantHandlerResponse = EntityUtils.toString(entity);

            //get tran id from response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(merchantHandlerResponse);
            String transaction_id = jsonNode.get("TRANSACTION_ID").asText();
        }
    }

}
