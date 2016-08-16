package se.grouprich.presencedetection.retrofit;

import retrofit2.Retrofit;
import se.grouprich.presencedetection.service.AppService;

public final class RetrofitManager {

    public static final String REGISTERUSER_URL = "http://beacons.zenzor.io/sys/api/";

    private Retrofit retrofit;
    private AppService appService;

    public RetrofitManager() {

        retrofit = new Retrofit.Builder()
                .baseUrl(REGISTERUSER_URL)
                .addConverterFactory(new StringConverterFactory())
                .build();

        appService = retrofit.create(AppService.class);
    }

    public AppService getAppService() {

        return appService;
    }
}
