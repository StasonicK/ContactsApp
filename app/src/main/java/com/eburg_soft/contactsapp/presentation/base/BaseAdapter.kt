package com.eburg_soft.contactsapp.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.eburg_soft.contactsapp.base.BaseAdapter.BaseViewHolder

abstract class BaseAdapter<V : BaseViewHolder> : RecyclerView.Adapter<V>() {

    var items: ArrayList<Any> = ArrayList()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: V, position: Int) {
        holder.bind(getItem(position))
    }

    fun getItem(position: Int): Any {
        return items[position]
    }

    fun add(newItem: Any) {
        items.add(newItem)
    }

    fun add(newItems: List<Any>) {
        items.add(newItems)
    }

    abstract class BaseViewHolder(protected val view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(item: Any)
    }
}