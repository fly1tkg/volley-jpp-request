/*
 *  Copyright 2013 fly1tkg
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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
