package com.example.narasimman.readincomingmessages;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by Narasimman on 24-12-2017.
 */

public class myReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle b=intent.getExtras();
        if(intent.getAction().equalsIgnoreCase("com.example.Broadcast")){
            Toast.makeText(context,b.getString("msg"),Toast.LENGTH_SHORT).show();
        }

        if(intent.getAction().equalsIgnoreCase("android.provider.Telephony.SMS_RECEIVED")){

            if(b!=null){
                final Object[] pdusObj= (Object[]) b.get("pdus");
                SmsMessage[] message=new SmsMessage[pdusObj.length];
                for(int i=0;i<message.length;i++){
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                        String format=b.getString("format");
                        message[i]=SmsMessage.createFromPdu((byte[]) pdusObj[i],format);
                    }
                    else {
                        message[i]=SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    }
                    String senderNum=message[i].getOriginatingAddress();
                    String msg=message[i].getMessageBody();
                    Toast.makeText(context,senderNum+" "+msg,Toast.LENGTH_SHORT).show();
                }
            }

        }


    }
}
