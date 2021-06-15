package com.whf.messagerelayer.receiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.whf.messagerelayer.bean.SmsModel;
import com.whf.messagerelayer.confing.Constant;
import com.whf.messagerelayer.service.SmsService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");

            for (SmsModel model : parse(pdus)) {
                startSmsService(context, model);
            }
        }
    }

    private Collection<SmsModel> parse(Object[] pdus) {
        Map<String, SmsModel> smsMap = new HashMap<>(2);
        for (Object o : pdus) {
            SmsMessage message = SmsMessage.createFromPdu((byte[]) o);
            String address = message.getOriginatingAddress();
            if (smsMap.containsKey(address)) {
                SmsModel prev = smsMap.get(address);
                prev.addContent(message.getMessageBody());
            } else {
                smsMap.put(address, SmsModel.fromMessage(message));
            }
        }
        return smsMap.values();
    }

    private void startSmsService(Context context, SmsModel sms) {
        Intent serviceIntent = new Intent(context, SmsService.class);
        serviceIntent.putExtra(Constant.EXTRA_SMS_MODEL, sms);

        context.startService(serviceIntent);
    }
}
