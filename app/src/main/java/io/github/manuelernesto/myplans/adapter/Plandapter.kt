package io.github.manuelernesto.myplans.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.manuelernesto.myplans.R
import io.github.manuelernesto.myplans.model.Plan

class Plandapter(private val context: Context, private val plans: List<Plan>) :
    RecyclerView.Adapter<Plandapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(
            R.layout.item,
            parent, false
        )
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return plans.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.title.text = plans[position].title
        holder.description.text = plans[position].description
        holder.date.text = plans[position].date
    }


    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.title_plan_tv)
        val description: TextView = itemView.findViewById(R.id.desc_plan_tv)
        val date: TextView = itemView.findViewById(R.id.date_plan_tv)

    }
}