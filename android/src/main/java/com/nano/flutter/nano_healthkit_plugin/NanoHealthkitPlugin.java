package com.nano.flutter.nano_healthkit_plugin;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** NanoHealthkitPlugin */
public class NanoHealthkitPlugin implements MethodCallHandler {
    private Context context;
    private MethodChannel methodChannel;
  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
//     final MethodChannel channel = new MethodChannel(registrar.messenger(), "nano_healthkit_plugin");
//     channel.setMethodCallHandler(new NanoHealthkitPlugin());
    new NanoHealthkitPlugin().onAttachedToEngine(registrar.context(), registrar.messenger());
  }
  
  @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
        onAttachedToEngine(binding.getApplicationContext(), binding.getBinaryMessenger());
    }

    private void onAttachedToEngine(Context applicationContext, BinaryMessenger messenger) {
        this.context = applicationContext;
        methodChannel = new MethodChannel(messenger, "nano_healthkit_plugin");
        methodChannel.setMethodCallHandler(this);
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        context = null;
        methodChannel.setMethodCallHandler(null);
        methodChannel = null;
    }

  @Override
  public void onMethodCall(@NonNull MethodCall call,@NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else {
      result.notImplemented();
    }
  }
}
