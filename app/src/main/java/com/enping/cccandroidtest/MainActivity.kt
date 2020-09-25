package com.enping.cccandroidtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber
import timber.log.Timber.DebugTree


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(DebugTree())
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}
