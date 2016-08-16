/*
package se.grouprich.presencedetection.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import se.grouprich.presencedetection.R;
import se.grouprich.presencedetection.model.User;
import se.grouprich.presencedetection.retrofit.StringConverterFactory;
import se.grouprich.presencedetection.service.AppService;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://smartsensor.io/CBtest/";
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String USER_ID = "se.grouprich.presencedetection.USER_ID";

    //public static final String BEACON_PREFERENCES_KEY = "se.grouprich.presencedetection.BEACON_PREFERENCES_KEY";
    //public static final String APP_IS_ACTIVATED_KEY = "se.grouprich.presencedetection.APP_IS_ACTIVATED_KEY";
    //public static final String PUBLIC_KEY_AS_STRING_KEY = "se.grouprich.presencedetection.PUBLIC_KEY_AS_STRING_KEY";

    private String publicKeyAsString;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button scanToActivateButton = (Button) findViewById(R.id.button_scan_to_activate);
        final Button scanActiveBeaconButton = (Button) findViewById(R.id.button_scan_active_beacon);

        final SharedPreferences preferences = getSharedPreferences(USER_ID, MODE_PRIVATE);

        if (!preferences.getBoolean(USER_ID, false)) {

            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(new StringConverterFactory())
                    .build();

            final AppService appService = retrofit.create(AppService.class);
            final Call<User> result = appService.register_user(new User());

            result.enqueue(new Callback<String>() {

                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.d(TAG, "***************** " + response.body());

                    publicKeyAsString = response.body().replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");

                    preferences.edit().putString(PUBLIC_KEY_AS_STRING_KEY, publicKeyAsString).apply();
                    final Intent authIntent = new Intent(context, AuthorizationActivity.class);
                    startActivity(authIntent);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                    Log.d(TAG, "Could not fetch publicKey: " + t.getMessage());
                }
            });

        } else {

            if (scanToActivateButton != null) {

                scanToActivateButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        final Intent scanIntent = new Intent(context, ScanActivity.class);
                        startActivity(scanIntent);
                    }
                });
            }

            if (scanActiveBeaconButton != null) {

                scanActiveBeaconButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        final Intent rangingIntent = new Intent(context, RangingActivity.class);
                        startActivity(rangingIntent);
                    }
                });
            }
        }
    }
}
*/
