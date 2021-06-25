package com.fizhu.androidplayground

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fizhu.androidplayground.databinding.ItemMainBinding

/**
 * Created by fizhu on 25 June 2021
 * https://github.com/Fizhu
 */
class MainMenuAdapter(private var onClick: (pos: Int) -> Unit) :
    RecyclerView.Adapter<MainMenuAdapter.ViewHolder>() {

    private val list: MutableList<Int> = mutableListOf()

    fun setData(list: List<Int>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemMainBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        )

    override fun getItemCount(): Int = list.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemMainBinding.bind(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        with(holder.binding) {
            btn.text = root.context.getString(data)
            btn.setOnClickListener { onClick.invoke(position) }
        }
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position
}