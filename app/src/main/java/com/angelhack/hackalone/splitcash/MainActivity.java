package com.angelhack.hackalone.splitcash;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements DeviceListFragment.OnFragmentInteractionListener  {
    private static final String TAG = "MainActivity";

    private DeviceListFragment mDeviceListFragment;
    private BluetoothAdapter BTAdapter;

    public static int DISCOVERABLE_DURATION = 300;
    public static int REQUEST_BLUETOOTH = 1;
    public static int DISCOVERABLE_BT_REQUEST_CODE = 2;

    BroadcastReceiver bluetoothState = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String stateExtra = BluetoothAdapter.EXTRA_STATE;
            int state = intent.getIntExtra(stateExtra, 0);

            String message;

            switch(state) {
                case (BluetoothAdapter.STATE_TURNING_ON) :
                    message = "Bluetooth turning on";
                    break;
                case (BluetoothAdapter.STATE_ON) :
                    message = "Bluetooth on";
                    break;
                case (BluetoothAdapter.STATE_TURNING_OFF) :
                    message = "Bluetooth turning off";
                    break;
                case (BluetoothAdapter.STATE_OFF) :
                    message = "Bluetooth off";
                    break;
                default:
                    message = "";
                    break;
            }

            Log.d(TAG, message);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BTAdapter = BluetoothAdapter.getDefaultAdapter();

        // Phone does not support Bluetooth so let the user know and exit.
        if (BTAdapter == null) {
            new AlertDialog.Builder(this)
                    .setTitle("Not compatible")
                    .setMessage("Your phone does not support Bluetooth")
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        if (!BTAdapter.isEnabled()) {
            Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBT, REQUEST_BLUETOOTH);
        }

//        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
//        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, DISCOVERABLE_DURATION);
//        startActivityForResult(discoverableIntent, DISCOVERABLE_BT_REQUEST_CODE);

        String actionState = BTAdapter.ACTION_STATE_CHANGED;
        registerReceiver(bluetoothState, new IntentFilter(actionState));

        String mydeviceaddress = BTAdapter.getAddress();
        String mydevicename = BTAdapter.getName();
        String status = mydevicename + " : " + mydeviceaddress;

        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();

        DeviceUuidFactory deviceUuidFactory = new DeviceUuidFactory(getApplicationContext());
        Toast.makeText(this, deviceUuidFactory.getDeviceUuid().toString(), Toast.LENGTH_SHORT).show();

        FragmentManager fragmentManager = getSupportFragmentManager();

        mDeviceListFragment = DeviceListFragment.newInstance(BTAdapter);
        fragmentManager.beginTransaction().replace(R.id.container, mDeviceListFragment).commit();

    }

    @Override
    public void onFragmentInteraction(String id) {
        Toast.makeText(getApplicationContext(), "HOOO" + id, Toast.LENGTH_SHORT);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_BLUETOOTH) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Ha! Bluetooth has been enabled.",
                        Toast.LENGTH_SHORT).show();
            } else { // RESULT_CANCELED as user refuse or failed
                Toast.makeText(getApplicationContext(), "Bluetooth is required.",
                        Toast.LENGTH_SHORT).show();

                finish();
            }
        } else if (requestCode == DISCOVERABLE_BT_REQUEST_CODE) {
            if (resultCode == DISCOVERABLE_DURATION){
                Toast.makeText(getApplicationContext(), "Your device is now discoverable by other devices for " +
                                DISCOVERABLE_DURATION + " seconds",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Fail to enable discoverability on your device.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
