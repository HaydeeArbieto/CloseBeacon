package se.grouprich.closebeacon.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.security.PublicKey;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import se.grouprich.closebeacon.service.BeaconService;
import se.grouprich.closebeacon.R;
import se.grouprich.closebeacon.requestresponsemanager.converter.KeyConverter;
import se.grouprich.closebeacon.retrofit.StringConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://smartsensor.io/CBtest/";
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String BEACON_PREFERENCES = "beaconPreferences";
    public static final String APP_IS_ACTIVATED_KEY = "appIsActivated";
//    public static final String PUBLIC_KEY_KEY = "publicKey";
    public static final String PUBLIC_KEY_AS_STRING_KEY = "publicKeyAsString";

    private String publicKeyAsString;
//    private PublicKey publicKey;
    private boolean appIsActivated;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences preferences = getSharedPreferences(BEACON_PREFERENCES, 0);

//        preferences.edit().putBoolean(APP_IS_ACTIVATED_KEY, false).apply(); // bara för test

        if (!preferences.getBoolean(APP_IS_ACTIVATED_KEY, false)) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(new StringConverterFactory())
                    .build();

            BeaconService beaconService = retrofit.create(BeaconService.class);
            Call<String> result = beaconService.getPublicKey();

            result.enqueue(new Callback<String>() {

                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.d(TAG, "***************** " + response.body());

                    publicKeyAsString = response.body().replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");

//                    try {
//
//                        publicKey = KeyConverter.stringToPublicKey(keyString);
//                        System.out.println(publicKey.toString());
//
//                    } catch (Exception e) {
//
//                        e.printStackTrace();
//                    }

                    preferences.edit().putString(PUBLIC_KEY_AS_STRING_KEY, publicKeyAsString).apply();
                    Intent intent = new Intent(context, AuthorizationActivity.class);
//                    intent.putExtra(PUBLIC_KEY_KEY, publicKey);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                    Log.d(TAG, "Could not fetch publicKey: " + t.getMessage());
                }
            });

        } else {

            Intent intent = new Intent(this, ScanActivity.class);
            startActivity(intent);
        }
    }
}
