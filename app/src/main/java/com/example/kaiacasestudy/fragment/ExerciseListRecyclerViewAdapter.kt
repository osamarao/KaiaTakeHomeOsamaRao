package com.example.kaiacasestudy.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kaiacasestudy.R
import com.example.kaiacasestudy.databinding.ListItemExerciseBinding

class ExerciseListRecyclerViewAdapter(
    private val exerciseList: List<ExerciseApplicationModel>,
    private val onCheckChanged: (Boolean, Int) -> Unit,
) :
    RecyclerView.Adapter<ExerciseListItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseListItemViewHolder {
        return ExerciseListItemViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_exercise, parent, false), onCheckChanged)
    }

    override fun onBindViewHolder(holder: ExerciseListItemViewHolder, position: Int) {
        holder.bind(exerciseList[position])
    }

    override fun getItemCount(): Int = exerciseList.size

}


class ExerciseListItemViewHolder(
    itemView: View,
    private val onCheckChanged: (Boolean, Int) -> Unit,
) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: ExerciseApplicationModel) {
        val binding = ListItemExerciseBinding.bind(itemView)
        binding.apply {
            name.text = item.name
            favorite.isChecked = item.favorite
            Glide.with(root.context).load(item.coverImageUrl).into(exerciseImage);
            favorite.setOnCheckedChangeListener(null)
            favorite.setOnCheckedChangeListener { _, isChecked ->
                onCheckChanged(isChecked, item.id)
            }
        }

    }
}