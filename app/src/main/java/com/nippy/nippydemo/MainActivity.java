package com.nippy.nippydemo;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sdk.nippy.AllUPIActivity;
import com.sdk.nippy.NippyPay;
import com.sdk.nippy.NippyPayResultListener;
import com.sdk.nippy.PaymentParam;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements NippyPayResultListener {
    TextView pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pay = findViewById(R.id.payment);
        pay.setOnClickListener(v -> {
            Log.e("Check_Moorthy","click");
            NippyPay nippyPay =new NippyPay();
            nippyPay.setNippyMID("4");
            nippyPay.setNippyAuth("asGjoiDFfajoisdf45324fwe1");
            JSONObject options = new JSONObject();
            try {
                options.put(PaymentParam.APPNAME, getString(R.string.app_name));
                options.put(PaymentParam.DESCRIPTION, "NippyPayment");
                options.put(PaymentParam.CURRENCY, "INR");
                options.put(PaymentParam.AMOUNT, 10.00);
                options.put(PaymentParam.UNAME, "Moorthy");
                options.put(PaymentParam.MOBILENUMBER, "9087654321");
                options.put(PaymentParam.UID, "57575757");
                options.put(PaymentParam.UD1, "test1");
                options.put(PaymentParam.UD2, "test2");
                nippyPay.open(MainActivity.this,options,MainActivity.this);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }



            /*Intent intent = new Intent(MainActivity.this, AllUPIActivity.class);
            intent.putExtra("authCode", "Authentication Code");
            intent.putExtra("txnId", "Unique Transaction id to validate txn");
            intent.putExtra("appName",getString(R.string.app_name));
            launch.launch(intent);*/
        });

    }
    ActivityResultLauncher<Intent> launch = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            assert (result.getData() != null);
            /*Response after transaction Status will be in boolean true/false
             * true: Success
             * false: Failure/Pending * */
            boolean txnStatus = result.getData().getBooleanExtra("status", false); }
    });

    @Override
    public void onPaymentSuccess(String var1) {
        Log.e("Check_Result","var1 "+var1);
    }

    @Override
    public void onPaymentError(int var1, String var2) {
        Log.e("Check_Result","E var1 "+var1);
        Log.e("Check_Result","E var1 "+var2);


    }
}