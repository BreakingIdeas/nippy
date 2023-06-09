# NippyPay SDK Integration Guide

## Introduction
The NippyPay SDK allows seamless integration of the NippyPayment Gateway into your Android application, enabling secure and convenient payment processing. This guide provides step-by-step instructions on how to integrate the NippyPay SDK into your Android project.

## Prerequisites
- Android Studio 3.0 or above
- Android API level 21 or higher

## Obtaining the NippyPay SDK
1. Download the NippyPay SDK from the following link: [aar/nippy-test.aar](https://github.com/BreakingIdeas/nippy/blob/f922956b982fb863a600871b20ebb99f947a8831/aar/nippy-test.aar).
2. Extract the downloaded package to obtain the SDK file `nippypay-sdk.aar`.

## Importing the NippyPay SDK
1. Open your Android project in Android Studio.
2. Copy the `nippy-test.aar` file to the `libs` directory in your project.
3. In your project's `build.gradle` file, add the following lines:
   groovy
   repositories {
       flatDir {
           dirs 'libs'
       }
   }
   
4. In your app module's `build.gradle` file, add the following dependency:
   groovy
   dependencies {
       implementation(name: 'nippypay-sdk', ext: 'aar')
   }
   

## Setting Up Permissions and Dependencies
1. Open your app's `AndroidManifest.xml` file.
2. Add the following permission inside the `<manifest>` tag:
   xml
   ```
   <uses-permission android:name="android.permission.INTERNET" />
   ```
   

## Initialization and Configuration
1. In your activity or application class, import the necessary classes:
   java
   ```
   import com.nippypay.sdk.NippyPay;
   import com.nippypay.sdk.PaymentParam;
   import org.json.JSONException;
   import org.json.JSONObject;
   ```
2. Initialize the NippyPay SDK with your credentials:
   java
   ```
   NippyPay nippyPay = new NippyPay();
   nippyPay.setNippyMID("your-merchant-id");
   nippyPay.setNippyAuth("your-auth-key");
   ```
   

## Payment Options
1. Create a JSONObject to store the payment options:
   java
  ```
    JSONObject options = new JSONObject();
   ```
   
2. Populate the options with the required parameters:
   java
   ```
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
   } catch (JSONException e) {
       throw new RuntimeException(e);
   }
   ```
   

## Callback Listeners
1. Implement the 'NippyPayResultListener' interface in your activity or fragment:
   java
   ```
   public class MainActivity extends AppCompatActivity implements NippyPayResultListener {
       // ...
   }
   ```
   
2. Override the necessary methods to handle payment responses:
   java
   ```
   @Override
   public void onPaymentSuccess(String transactionId) {
       // Handle successful payment
   }

   @Override
   public void onPaymentError(int errorCode, String errorMessage) {
       switch (errorCode) {
           case 1:
               // Payment cancelled by the user
               // Show appropriate message to the user
               Log.d("NippyPay", "Payment Cancelled");
               break;
           case 2:
               // Payment failed
               // Show appropriate message to the user
               Log.d("NippyPay", "Payment Failed");
               break;
           default:
               // Other error codes
               // Handle other error codes if needed
               break;
       }
   }
   ```
## Explanation of error codes:

- *Error Code 1: Payment Cancelled*
  - This error code is triggered when the user cancels the payment process. It indicates that the user intentionally aborted the payment.
  - In the code sample, the message "Payment Cancelled" is logged. You can replace the logging statement with your own implementation to display an appropriate message to the user.

- *Error Code 2: Payment Failed*
  - This error code is triggered when the payment processing encounters an error or fails for some reason.
  - In the code sample, the message "Payment Failed" is logged. You can replace the logging statement with your own implementation to display an appropriate message to the user.

You can customize the error handling logic in the 'onPaymentError' method based on your application's requirements. You may want to display a dialog box, redirect the user to a specific screen, or perform any other necessary actions to handle the different error scenarios.

Remember to refer to the documentation provided with the NippyPay SDK for any additional error codes or specific error handling instructions.

## Integration Steps
1. Call the 'open' method of the 'NippyPay' instance to open the payment gateway:
   java
   ```
   nippyPay.open(MainActivity.this, options, MainActivity.this);
   ```

## Testing and Error Handling
- Run your app and trigger the payment flow to test the integration with the NippyPayment Gateway.
- Ensure that you receive the payment response and handle it accordingly in your app.
- Handle any exceptions or errors that may occur during the integration process.

## Troubleshooting and FAQs
- *Q: Why am I getting an "Invalid merchant ID" error?*
  - Make sure you have provided the correct merchant ID obtained from the NippyPay team.
  - Verify that the merchant ID is correctly set using 'nippyPay.setNippyMID("your-merchant-id")'.

- *Q: Why am I receiving a "Payment failed" message without an error code?*
  - Check your internet connectivity to ensure the device has a stable network connection.
  - Verify that you have entered the correct payment details and parameters.
  - Contact the NippyPay support team for further assistance.

## References
- Official NippyPay SDK Documentation: [
https://nippy.co.in ]
---
