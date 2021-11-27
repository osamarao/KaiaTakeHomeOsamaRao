package com.example.kaiacasestudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kaiacasestudy.usecase.MainActivityUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint(AppCompatActivity::class)
class MainActivity : Hilt_MainActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    @Inject
    lateinit var mainActivityUseCase: MainActivityUseCase


    @OptIn(InternalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}