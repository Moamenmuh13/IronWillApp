package com.example.ironwillapp.data

import com.example.ironwillapp.models.Ranks

class RanksData {
    fun ranksData(): MutableList<Ranks> {
        return mutableListOf(
            Ranks("Scout", "Reach 1 Day"),
            Ranks("Private", "Reach 2 Day"),
            Ranks("Corporal", "Reach 5 Day"),
            Ranks("Sergeant", "Reach 10 Day"),
            Ranks("Master Sergeant", "Reach 15 Day"),
            Ranks("Knight", "Reach 20 Day"),
        )

    }

}
