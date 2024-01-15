package com.example.quicknote.presentation.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quicknote.databinding.NoteItemBinding
import com.example.quicknote.domain.model.Note

class NoteAdapter(
    private val onItemClicked: (Int) -> Unit
):ListAdapter<Note,NoteAdapter.ViewHolder>(DiffCallback) {
    inner class ViewHolder(
        private val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.apply {
                title.text = note.title
                subtitle.text = note.subTitle
                createDate.text = note.createdAt
                when(note.priority){
                    "low" -> priority.backgroundTintList= ColorStateList.valueOf(Color.parseColor("#11D99B"))
                    "medium" -> priority.backgroundTintList= ColorStateList.valueOf(Color.parseColor("#D9D92F"))
                    "high" -> priority.backgroundTintList= ColorStateList.valueOf(Color.parseColor("#FF5151"))
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(note.id)
        }
        holder.bind(note)

    }

    object DiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.title ==newItem.title
        }

    }
}