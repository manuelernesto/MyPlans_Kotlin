package io.github.manuelernesto.myplans.controll

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import io.github.manuelernesto.myplans.R
import io.github.manuelernesto.myplans.model.Plan
import kotlinx.android.synthetic.main.activity_new.*

class NewActivity : AppCompatActivity() {
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        db = FirebaseFirestore.getInstance()

        btnNew.setOnClickListener { newPlan() }
        btnCancel.setOnClickListener { finish() }

    }

    private fun newPlan() {

        progress_new.visibility = ProgressBar.VISIBLE
        if (!validate()) {

            val mPlanTable: CollectionReference = db.collection("Plan")

            val plan = Plan(
                null,
                add_plan_title.text.toString(),
                add_plan_desc.text.toString(),
                add_plan_time.text.toString()
            )
            mPlanTable.add(plan).addOnSuccessListener {
                "Plan Succesull Added.".toast(this@NewActivity)
                clearField()
                progress_new.visibility = ProgressBar.INVISIBLE
                startActivity(Intent(this, MainActivity::class.java))
            }.addOnFailureListener { exception ->
                "Error: ${exception.message}".toast(this@NewActivity)
            }
        } else {
            progress_new.visibility = ProgressBar.INVISIBLE
            Toast.makeText(this, "Please, fill all fields!", Toast.LENGTH_LONG).show()
        }

    }

    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
        return Toast.makeText(context, this.toString(), duration).apply { show() }
    }

    private fun clearField() {
        add_plan_title.text.clear()
        add_plan_desc.text.clear()
        add_plan_time.text.clear()
    }

    private fun validate(): Boolean {

        if (add_plan_title.text.isBlank() || add_plan_title.text.isEmpty()) {
            add_plan_title.requestFocus()
            return true
        }

        if (add_plan_desc.text.isBlank() || add_plan_desc.text.isEmpty()) {
            add_plan_desc.requestFocus()
            return true
        }

        if (add_plan_time.text.isBlank() || add_plan_time.text.isEmpty()) {
            add_plan_time.requestFocus()
            return true
        }

        return false
    }
}
