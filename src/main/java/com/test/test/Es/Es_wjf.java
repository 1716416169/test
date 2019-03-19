package com.test.test.Es;

import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Component
public class Es_wjf {
    TransportClient client;
    public TransportClient getClient(){
        try {
            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }
    public Map<String, Object> add_es(Object object, String db, String type) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        IndexRequestBuilder prepareIndex = getClient()
                .prepareIndex(db, type, String.valueOf(object.getClass()
                        .getMethod("getId",null)
                        .invoke(object)
                    )
                );
        Map<String,Object> map=new HashMap<String, Object>();
        for (Field objects:object.getClass().getDeclaredFields()) {
            System.out.println("test:"+objects.getName());
            String name = objects.getName();
            char[] chars = name.toCharArray();
            chars[0]-=32;
            String s = String.valueOf(chars);
            System.out.println(s);
            map.put(objects.getName(),object.getClass()
                    .getMethod("get"+s)
                    .invoke(object)
            );
        }
        IndexResponse indexResponse = prepareIndex.setSource(map).get();
        System.out.println(indexResponse);
        getClient().close();
        return map;
    }
    public GetResponse get_es(Object object, String db, String type) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        GetRequestBuilder prepareGet = getClient().prepareGet(db, type, "12"
        );
        System.out.println(object.getClass()
                .getMethod("getId")
                .invoke(object));
        GetResponse documentFields = prepareGet.get();
        System.out.println(documentFields);
        return documentFields;
    }

}
