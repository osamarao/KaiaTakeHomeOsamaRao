package com.example.kaiacasestudy.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.kaiacasestudy.R
import com.example.kaiacasestudy.data.NetworkResult
import com.example.kaiacasestudy.databinding.ExerciseDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint(Fragment::class)
class ExerciseDetailFragment : Hilt_ExerciseDetailFragment(R.layout.exercise_detail_fragment) {
    companion object {
        private const val KEY_EXERCISE_ID = "EXERCISE_ID"
        private const val TAG = "ExerciseDetailFragment"
        fun newInstance(id: Int): ExerciseDetailFragment {
            return ExerciseDetailFragment().apply {
                arguments = bundleOf(KEY_EXERCISE_ID to id)
            }
        }
    }

    private val vm: ExerciseDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val exerciseId = requireArguments().getInt(KEY_EXERCISE_ID)

        val binding = ExerciseDetailFragmentBinding.bind(view)
        binding.apply {
            vm.exercise(exerciseId).onEach { exercise ->
                when (exercise) {
                    is NetworkResult.Error -> {
                        Log.e(TAG, "Failed to load postMetadata", exercise.error)
                    }
                    NetworkResult.Loading -> {
                        Log.i(TAG, "postMetadata loading")
                    }
                    is NetworkResult.Success -> {
                        actionOnExercise.text =
                            if (exercise.data.favorite) getString(R.string.unfavorite) else getString(R.string.favorite)

                        Glide.with(requireContext()).load(exercise.data.coverImageUrl).into(image)

                        actionOnExercise.setOnClickListener {
                            if (exercise.data.favorite) {
                                vm.removeFavorite(exerciseId)
                            } else {
                                vm.addFavorite(exerciseId)
                            }

                        }
                    }
                }
            }.launchIn(lifecycleScope)

            cancelTraining.setOnClickListener {
                requireActivity().finish()
            }
        }

    }


}