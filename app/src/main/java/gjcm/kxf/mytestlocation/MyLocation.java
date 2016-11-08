package gjcm.kxf.mytestlocation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

/**
 * Created by Administrator on 2016/11/7.
 */
public class MyLocation extends Activity implements View.OnClickListener {
    private Button btnLocation;
    private LocationService locationService;
    private TextView LocationResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        btnLocation = (Button) findViewById(R.id.addfence);
        LocationResult = (TextView) findViewById(R.id.textView1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        locationService = ((LocationApplication) getApplication()).locationService;
        locationService.registerListener(mListener);
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        btnLocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addfence:
                if (btnLocation.getText().toString().equals("start")) {
                    locationService.start();// 定位SDK
                    btnLocation.setText("stop");
                } else {
                    locationService.stop();
                    btnLocation.setText("start");
                }
                break;

        }

    }

    private BDLocationListener mListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            Log.i("kxflog", "---------->" + location.getAddrStr());
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuffer sb = new StringBuffer(256);
                sb.append(location.getAddrStr());
                LocationResult.setText(sb);
                locationService.stop();
            }
        }
    };
}
