package com.whf.messagerelayer.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.whf.messagerelayer.bean.SmsModel;
import com.whf.messagerelayer.confing.Constant;
import com.whf.messagerelayer.utils.EmailRelayerManager;
import com.whf.messagerelayer.utils.NativeDataManager;

import java.io.Serializable;

public class SmsService extends IntentService {

    private NativeDataManager mNativeDataManager;

    public SmsService() {
        super("SmsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mNativeDataManager = new NativeDataManager(this);
        SmsModel model = (SmsModel) intent.getSerializableExtra(Constant.EXTRA_SMS_MODEL);

        if (mNativeDataManager.getEmailRelay()) {
            EmailRelayerManager.relayEmail(mNativeDataManager, model);
        }
    }
}
