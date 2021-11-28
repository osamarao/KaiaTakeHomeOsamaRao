package com.example.kaiacasestudy.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.kaiacasestudy.ExerciseDetailActivity
import com.example.kaiacasestudy.R
import com.example.kaiacasestudy.data.NetworkResult
import com.example.kaiacasestudy.databinding.ExerciseListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint(Fragment::class)
class ExerciseListFragment : Hilt_ExerciseListFragment(R.layout.exercise_list_fragment) {

    companion object {
        fun newInstance() = ExerciseListFragment()
        private const val TAG: String = "ExerciseListFragment"
    }


    private val viewModel: ExerciseListViewModel by viewModels()
    lateinit var binding: ExerciseListFragmentBinding
    lateinit var adapter: ExerciseListRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ExerciseListFragmentBinding.bind(view)

        binding.apply {
            viewModel.exerciseList.onEach {
                when (it) {
                    is NetworkResult.Error -> {
                        Log.e(TAG, "Failed to load postMetadata", it.error)
                    }
                    NetworkResult.Loading -> {
                        Log.i(TAG, "postMetadata loading")
                    }
                    is NetworkResult.Success -> {
                        Log.d(TAG, it.data.toString())
                        adapter = ExerciseListRecyclerViewAdapter(
                            exerciseList = it.data
                        ) { isFavorited, id ->
                            if (isFavorited) {
                                viewModel.addFavorite(id)
                            } else {
                                viewModel.removeFavorite(id)
                            }
                        }
                        list.adapter = adapter
                    }
                }
            }.launchIn(lifecycleScope)

            startTraining.setOnClickListener {
                requireActivity().startActivity(ExerciseDetailActivity.newIntent(requireContext()))
            }

        }
    }
}