package com.fly1tkg.volley.request;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

public class JsonModelRequest<T1, T2> extends Request<T1> {
    Class<T2> t2Class;
    Listener<T1> mListener;

    public JsonModelRequest(int method, String url, Listener<T1> listener, ErrorListener errorListener, T2... t2) {
        super(method, url, errorListener);
        mListener = listener;
        @SuppressWarnings("unchecked") Class<T2> t2Class = (Class<T2>) t2.getClass().getComponentType();
        this.t2Class = t2Class;
    }

    public JsonModelRequest(String url, Listener<T1> listener, ErrorListener errorListener, T2... t2) {
        super(Method.GET, url, errorListener);
        mListener = listener;
        @SuppressWarnings("unchecked") Class<T2> t2Class = (Class<T2>) t2.getClass().getComponentType();
        this.t2Class = t2Class;
    }

    @Override
    protected void deliverResponse(T1 response) {
        mListener.onResponse(response);
    }

    @Override
    protected Response<T1> parseNetworkResponse(NetworkResponse response) {
        String responseString;
        try {
            responseString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            responseString = new String(response.data);
        }

        try {
            java.lang.reflect.Method method = t2Class.getMethod("get", String.class);
            @SuppressWarnings("unchecked") T1 model = (T1) method.invoke(null, responseString);
            return Response.success(model, HttpHeaderParser.parseCacheHeaders(response));
        } catch (NoSuchMethodException e) {
            return Response.error(new ParseError(e));
        } catch (IllegalArgumentException e) {
            return Response.error(new ParseError(e));
        } catch (IllegalAccessException e) {
            return Response.error(new ParseError(e));
        } catch (InvocationTargetException e) {
            return Response.error(new ParseError(e));
        }
    }
}
