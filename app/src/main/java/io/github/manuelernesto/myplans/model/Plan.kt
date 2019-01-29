package io.github.manuelernesto.myplans.model

import com.google.firebase.firestore.Exclude
import java.io.Serializable

data class Plan(
    @Exclude var id: String? = null,
    var title: String = "",
    var description: String = "",
    var date: String = ""
) : Serializable