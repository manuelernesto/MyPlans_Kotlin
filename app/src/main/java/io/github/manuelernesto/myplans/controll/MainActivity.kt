package io.github.manuelernesto.myplans.controll

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import io.github.manuelernesto.myplans.R
import io.github.manuelernesto.myplans.adapter.Plandapter
import io.github.manuelernesto.myplans.model.Plan
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mAdapter: Plandapter
    lateinit var mplans: ArrayList<Plan>
    lateinit var mRecyclerView: RecyclerView
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView = findViewById(R.id.rv)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        mplans = ArrayList()
        mAdapter = Plandapter(this, mplans)

        mRecyclerView.adapter = mAdapter
        db = FirebaseFirestore.getInstance()

        loadPlans()
        btn_new.setOnClickListener { startActivity(Intent(this, NewActivity::class.java)) }
    }

    private fun loadPlans() {

        db.collection("Plan").get().addOnSuccessListener { query ->
            progress_list.visibility = ProgressBar.GONE
            if (!query.isEmpty) {
                val list = query.documents

                for (d in list) {
                    val plan: Plan? = d.toObject(Plan::class.java)
                    if (plan != null) {
                        plan.id = d.id
                        mplans.add(plan)
                    }
                }
                no_more_plan.visibility = TextView.VISIBLE
                mAdapter.notifyDataSetChanged()
            } else {
                rl_empty.visibility = TextView.VISIBLE
            }
        }
    }

}
