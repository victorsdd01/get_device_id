package com.example.get_device_info

import android.os.Build
import android.provider.Settings
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import java.util.*

class MainActivity: FlutterActivity() {

    private var channel = "sampleProject"
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger,channel).setMethodCallHandler { call, result ->
            if(call.method == "getImei"){
                 val androidId =  Settings.Secure.getString(this.contentResolver,Settings.Secure.ANDROID_ID)
                 val id        =  Build.ID
                 val serial    =  android.os.Build.SERIAL
                 //val manufacter = Build.getSerial()
                 val pseudoId   = UUID.randomUUID().toString()
                 val m_szDevIDShort = ("35" +
                          Build.BOARD.length % 10
                        + Build.BRAND.length % 10
                        + Build.CPU_ABI.length % 10
                        + Build.DEVICE.length % 10
                        + Build.MANUFACTURER.length % 10
                        + Build.MODEL.length % 10
                        + Build.PRODUCT.length % 10)

                result.success("AndroidId:$androidId \nid:$id\nserial:$serial\n pseudoId: $pseudoId\n m_szDevIDShort : $m_szDevIDShort")
            }else{
                result.notImplemented()
            }
        }
    }
}
