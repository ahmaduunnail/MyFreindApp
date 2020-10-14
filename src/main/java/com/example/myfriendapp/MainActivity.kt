package com.example.myfriendapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showMyFreinds_fragments()
    }

    private fun changeFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment, frameId: Int
    ) {
        val transcaction = fragmentManager.beginTransaction()
        transcaction.replace(frameId, fragment)

        transcaction.commit()
    }

    fun showMyFreinds_fragments() {
        changeFragment(supportFragmentManager, MyFreinds_fragments.newInstance(), R.id.content)
    }
    fun showMyFreindsAdd_fragments() {
        changeFragment(supportFragmentManager, MyFreindsAdd_fragments.newInstance(), R.id.content)
    }
}