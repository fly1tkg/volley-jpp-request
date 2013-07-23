package com.fly1tkg.volley_jpp_request;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fly1tkg.volley.request.JsonModelRequest;
import com.fly1tkg.volley_jpp_request.model.Sample;
import com.fly1tkg.volley_jpp_request.model.SampleGen;

public class MainActivity extends Activity {
    private static final String JSON_URL = "https://raw.github.com/fly1tkg/volley-jpp-request/master/sample.json";
    private RequestQueue mQueueRequest;
    private TextView mJsonTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJsonTextView = (TextView) findViewById(R.id.json);

        mQueueRequest = Volley.newRequestQueue(this);
        mQueueRequest.add(new JsonModelRequest<Sample, SampleGen>(JSON_URL, new Listener<Sample>() {
            @Override
            public void onResponse(Sample sample) {
                mJsonTextView.setText(sample.toString());
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }));
    }
}
