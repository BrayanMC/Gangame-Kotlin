package bmc.com.gangamecommons

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

class DataBindingViewHolder<MODEL>(val itemVariableId: Int, val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(item: MODEL) {
        binding.setVariable(itemVariableId, item)
        binding.executePendingBindings()
    }
}