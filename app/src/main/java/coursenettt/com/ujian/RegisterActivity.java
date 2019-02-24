package coursenettt.com.ujian;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


    public void signn (View view){

        final EditText et1 = (EditText) findViewById(R.id.et1);
        final EditText et2 = (EditText) findViewById(R.id.et2);
        final EditText et3 = (EditText) findViewById(R.id.et3);
        final EditText et4 = (EditText) findViewById(R.id.et4);
        final EditText et5 = (EditText) findViewById(R.id.et5);

        final String username = et1.getText().toString();
        final String password = et2.getText().toString();
        final String email = et3.getText().toString();
        final String usia = et4.getText().toString();
        final String phone = et5.getText().toString();


        if (username.length()==0){
            et1.setError("Nama Pengguna");
            return;
        }
        else if (password.length()==0){
            et2.setError("Masukan Password");
            return;
        }
        else if (email.length()==0){
            et3.setError("Masukan Email Anda");
            return;
        }
        else if (usia.length()==0){
            et4.setError("Masukan Alamat Anda");
            return;
        }
        else if (phone.length()==0){
            et5.setError("Masukan No Telpon Anda");
            return;
        }

        OkHttpClient postman = new OkHttpClient();

        RequestBody body = new MultipartBody.Builder().
                setType(MultipartBody.FORM)
                .addFormDataPart("a", username)
                .addFormDataPart("b", password)
                .addFormDataPart("c", email)
                .addFormDataPart("d", usia)
                .addFormDataPart("m", phone)
                .build();

        Request request = new Request.Builder()
                .post(body)
                .url(Setting.IP_SERVER + "proses_register.php")
                .build();

        final ProgressDialog pd = new ProgressDialog(RegisterActivity.this);
        pd.setTitle("Processing..");
        pd.setMessage("Wait..");
        pd.setCancelable(false);
        pd.show();

        postman.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "please check your connection " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String hasil = response.body().string();
                try {
                    JSONObject j = new JSONObject(hasil);
                    boolean r = j.getBoolean("result");

                    if (r==true){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pd.dismiss();

                                Intent ygj = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(ygj);
                            }
                        });
                    }
                    else {
                        String m = j.getString("message");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pd.dismiss();

                            }
                        });
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
