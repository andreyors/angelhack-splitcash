package com.angelhack.hackalone.splitcash;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class RequestTask extends AsyncTask<String, String, String> {

    public interface AsyncResponse {
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;

    public RequestTask(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }


    @Override
    protected String doInBackground(String... uri) {
        String responseString = null;
        try {
            URL url = new URL(uri[0]);
            Log.d("URL", url.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String line;
                StringBuilder responseOutput = new StringBuilder();

                while((line = br.readLine()) != null ) {
                    responseOutput.append(line);
                }
                br.close();

                responseString = responseOutput.toString();
            } finally {
                conn.disconnect();
            }
        } catch (IOException e) {
        }

        return responseString;
    }

}
