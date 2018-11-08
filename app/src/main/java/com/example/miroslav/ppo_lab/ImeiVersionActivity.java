package com.example.miroslav.ppo_lab;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

//Визуальный интерфейс
public class ImeiVersionActivity extends AppCompatActivity {

    static final int REQUEST_READ_PHONE_STATE_PERMISSION = 345;
    TextView imeiView;
    TextView versionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imei_version);         //чтобы установить ресурс разметки дизайна, вызывется метод setContentView (ссылка на файл)
                                                                //Класс R содержит идентификаторы для всех ресурсов, расположенных в каталоге res
        imeiView = findViewById(R.id.imei_version__imeiTextView);
        versionView = findViewById(R.id.imei_version__versionTextView);

        versionView.setText(BuildConfig.VERSION_NAME);
        getPhoneImei();
    }


    private void getPhoneImei()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)    //имеет ли приложение разрешение на получение информации о телефоне  пользователя
                != PackageManager.PERMISSION_GRANTED) {

            // Разрешение не предоставляется
            // Должны ли мы показать объяснение?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,      //shouldShowRequestPermissionRationale(), который возвращается, true если пользователь ранее отказал в запросе, и возвращается, false если пользователь отказал в разрешении и выбрал параметр « Не спрашивать снова»
                    Manifest.permission.READ_PHONE_STATE)) {

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                dialogBuilder.setMessage("The app needs a permission to access your phone functions," +
                        "because the app has to show you your device IMEI");
                dialogBuilder.setTitle("Hey!");
                dialogBuilder.setCancelable(false);
                final Activity thisActivity = this;
                dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ActivityCompat.requestPermissions(thisActivity,                     //для запроса соответствующих разрешений
                                new String[]{Manifest.permission.READ_PHONE_STATE},
                                REQUEST_READ_PHONE_STATE_PERMISSION);
                    }
                });

                dialogBuilder.show();
            } else {
                // Никаких объяснений не требуется; запросить разрешение
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        REQUEST_READ_PHONE_STATE_PERMISSION);
            }

        } else {
            // Разрешение уже предоставлено
            showImei();
        }

    }

    private void showImei()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED)
            return;
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();
        imeiView.setText(imei);
    }

    public void onRequestPermissionsResult(int requestCode,         //Когда пользователь отвечает на запрос разрешения вашего приложения, система вызывает onRequestPermissionsResult()метод вашего приложения , передавая ему ответ пользователя
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_PHONE_STATE_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showImei();
                    // разрешение было предоставлено, yay! Сделайте
                    // Задача, связанная с контактами, которую вы должны выполнить.
                } else {
                    imeiView.setText("NO PERMISSION -> NO IMEI");
                }
                return;
            }
        }
    }
}
