package com.example.fragmentonbackpressedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for(i in 1..4){
            supportFragmentManager.beginTransaction().add(R.id.fragment_container_view, BlankFragment.newInstance("Page${i}")).commit()
        }
    }
}
