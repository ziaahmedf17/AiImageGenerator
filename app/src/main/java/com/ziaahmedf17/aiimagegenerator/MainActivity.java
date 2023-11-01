package com.ziaahmedf17.aiimagegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ziaahmedf17.aiimagegenerator.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    public static final MediaType JSON = MediaType.get("application/json");
    OkHttpClient client = new OkHttpClient();
    String imageUrl;
    String fileName = "ziaahmedf17";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = binding.inputText.getText().toString();
                callAPI(text);
            }
        });
    }

    private void callAPI(String text) {
        JSONObject object = new JSONObject();
        try {
            object.put("prompt", text);
            object.put("size","256x256");
        } catch (JSONException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        RequestBody requestBody = RequestBody.create(object.toString(), JSON);
        Request request = new Request.Builder().url("https://api.openai.com/v1/images/generations")
                .header("Authorization","Bearer "+getString(R.string.apikey))
                .post(requestBody);
    }
}