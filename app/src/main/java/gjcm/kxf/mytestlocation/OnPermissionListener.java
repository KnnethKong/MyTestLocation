package gjcm.kxf.mytestlocation;

/**
 * Created by kxf on 2016/11/8.
 */
public interface OnPermissionListener {
    /** sdk<23或>=23时所需权限已经获取到了权限执行api
    */
    void onAgreePermission();

    /**
     * sdk>=23时所需权限未获取到了权限执行api
     */
    void onDeniedPermission();
}
