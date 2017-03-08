package com.example.hyejin.kb.dto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hyejin.kb.R;
import com.example.hyejin.kb.app.App;
import com.example.hyejin.kb.error.ApiError;
import com.example.hyejin.kb.error.ErrorUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Deprecated
public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Sign.CreateRequest createRequest = new Sign.CreateRequest("nickname", "uuid", "token");
                App.getInstance().getHttpService().signIn(createRequest).enqueue(new Callback<Sign.User>() {
                    @Override
                    public void onResponse(Call<Sign.User> call, Response<Sign.User> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                            Sign.User body = response.body();
                            textView.setText(body.toString());

                        } else {
                            ApiError error = ErrorUtils.parseError(response);
                            Log.e("error", error.getStatusCode() + error.getMessage());

                        }


                    }

                    @Override
                    public void onFailure(Call<Sign.User> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
