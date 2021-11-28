package com.example.kaiacasestudy

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.kaiacasestudy.data.NetworkResult
import com.example.kaiacasestudy.fragment.ExerciseDetailFragment
import com.example.kaiacasestudy.fragment.ExerciseListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint(AppCompatActivity::class)
class ExerciseDetailActivity : Hilt_ExerciseDetailActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ExerciseDetailActivity::class.java)
        }

        private const val TAG: String = "ExerciseDetailActivity"
    }

    private val vm: ExerciseListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE


        vm.exerciseList.onEach { exerciseList ->
            when (exerciseList) {
                is NetworkResult.Error -> {
                    Log.e(TAG, "Failed to load postMetadata", exerciseList.error)
                }
                NetworkResult.Loading -> {
                    Log.i(TAG, "postMetadata loading")
                }
                is NetworkResult.Success -> {
                    for (exercise in exerciseList.data) {
                        supportFragmentManager.beginTransaction()
                            .replace(android.R.id.content, ExerciseDetailFragment.newInstance(exercise.id))
                            .commit();
                        delay(5_000)
                    }
                    finish()
                }
            }
        }.launchIn(lifecycleScope)
    }
}