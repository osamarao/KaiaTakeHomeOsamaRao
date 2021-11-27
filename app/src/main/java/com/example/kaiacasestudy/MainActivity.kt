package com.example.kaiacasestudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kaiacasestudy.fragment.ExerciseListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint(AppCompatActivity::class)
class MainActivity : Hilt_MainActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(android.R.id.content, ExerciseListFragment.newInstance())
            .commit();
    }
}