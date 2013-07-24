package com.fly1tkg.volley.request.test;

import java.lang.reflect.Method;

import junit.framework.TestCase;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.fly1tkg.volley.request.JsonModelRequest;
import com.fly1tkg.volley.request.test.model.Sample;
import com.fly1tkg.volley.request.test.model.SampleGen;

public class JsonModelRequestTest extends TestCase {
    private JsonModelRequest<Sample, SampleGen> mJsonModelRequest;
    private Class<JsonModelRequest<Sample, SampleGen>> mClass;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mJsonModelRequest = new JsonModelRequest<Sample, SampleGen>(null, null, null);
        mClass = (Class<JsonModelRequest<Sample, SampleGen>>) mJsonModelRequest.getClass();
    }

    public void testParseNetworkResponse() throws Exception {
        Method method = mClass.getDeclaredMethod("parseNetworkResponse", NetworkResponse.class);
        method.setAccessible(true);
        
        NetworkResponse response = new NetworkResponse("{\"id\":1,\"name\":\"hoge\"}".getBytes());
        Response<Sample> sampleResponse = (Response<Sample>) method.invoke(mJsonModelRequest, response);
        Sample sample = sampleResponse.result;
        assertEquals(sample.getId(), 1);
        assertEquals(sample.getName(), "hoge");
    }
}
