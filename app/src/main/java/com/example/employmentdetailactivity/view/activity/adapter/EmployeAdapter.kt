package com.example.employmentdetailactivity.view.activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.employmentdetailactivity.databinding.EmployeLayoutBinding
import com.example.employmentdetailactivity.model.EmployeDetailItem

class EmployeAdapter : RecyclerView.Adapter<EmployeAdapter.ViewHolderItem>() {

    private var itemCallBackListener: ((EmployeDetailItem) -> Unit)? = null

    fun itemViewClickListener(listener: (EmployeDetailItem) -> Unit) {
        itemCallBackListener = listener
    }

    inner class ViewHolderItem(private val binding: EmployeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(items: EmployeDetailItem) {

            binding.apply {
                name.text = items.name
                emailText.text = items.email

            }
            itemView.setOnClickListener {
                itemCallBackListener?.apply {
                    invoke(items)
                }
            }
        }
    }

    private var differCallBack = object : DiffUtil.ItemCallback<EmployeDetailItem>() {
        override fun areItemsTheSame(
            oldItem: EmployeDetailItem, newItem: EmployeDetailItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: EmployeDetailItem, newItem: EmployeDetailItem
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItem {
        return ViewHolderItem(
            binding = EmployeLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {
        holder.bind(differ.currentList[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}