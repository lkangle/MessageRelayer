package com.whf.messagerelayer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.whf.messagerelayer.R;
import com.whf.messagerelayer.utils.NativeDataManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    NativeDataManager manager;
    TextView target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = new NativeDataManager(this);
        target = (TextView) findViewById(R.id.target_email);

        RelativeLayout mEmailLayout = (RelativeLayout) findViewById(R.id.email_relay_layout);
        mEmailLayout.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuItem menuItem = menu.add("关于");
        menuItem.setIcon(R.mipmap.ic_about)
                .setShowAsAction(2);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String toAccount = manager.getEmailToAccount();
        if (toAccount != null) {
            target.setText(String.format("目标邮箱: \n%s", toAccount));
        } else {
            target.setText("目标邮箱: 请设置！");
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.email_relay_layout) {
            startActivity(new Intent(this, EmailRelayerActivity.class));
        }
    }
}
