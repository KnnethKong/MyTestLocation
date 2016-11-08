package gjcm.kxf.mytestlocation;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by kxf on 2016/11/8.
 */
public class TestOne extends AppCompatActivity {
    PermissionTools mPermissionHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPermissionHelper = new PermissionTools(this);
        mPermissionHelper.checkPermisson(new OnPermissionListener() {
            @Override
            public void onAgreePermission() {
                showToast("同意权限了");
                // do something
            }

            @Override
            public void onDeniedPermission() {
                showToast("拒绝权限了");
                finish(); // 当界面一定通过权限才能继续，就要加上这行
            }
        }, Manifest.permission.SEND_SMS);
    }

    private void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPermissionHelper.onResume(); // 当界面一定通过权限才能继续，就要加上这行
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
