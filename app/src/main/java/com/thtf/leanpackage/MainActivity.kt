package com.thtf.leanpackage

import android.app.Activity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TextView

/**
 * Skeleton of an Android Things activity.
 *
 *
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 *
 *
 * <pre>`PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
`</pre> *
 *
 *
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see [https://github.com/androidthings/contrib-drivers.readme](https://github.com/androidthings/contrib-drivers.readme)
 */
class MainActivity : Activity(), View.OnClickListener {

    override fun onClick(v: View?) {
        if (v!!.id == start_record_btn.id) {
            rdSound(tcmFileDir)
        } else if (v!!.id == stop_record_btn.id) {
            rdStop()

        }
    }

    private lateinit var stop_record_btn: Button

    private lateinit var start_record_btn: Button

    private lateinit var read_record_txt: TextView
    private lateinit var tcmFileDir: String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tcmFileDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/temp.pcm";
        read_record_txt = findViewById<View>(R.id.read_record) as TextView
        start_record_btn = findViewById<View>(R.id.start_record) as Button
        stop_record_btn = findViewById<View>(R.id.stop_record) as Button
//        tv.text = stringFromJNI()
        start_record_btn.setOnClickListener(this)
        stop_record_btn.setOnClickListener(this)
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

    external fun rdSound(path: String)

    external fun rdStop()

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
