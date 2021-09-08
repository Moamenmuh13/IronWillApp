package com.example.ironwillapp.data

import com.example.ironwillapp.models.Ranks

class RanksData {
    fun ranksData(): MutableList<Ranks> {
        return mutableListOf(
            Ranks("Scout", "Reach 1 Day"),
            Ranks("Private", "Reach 3 Day"),
            Ranks("Corporal", "Reach 5 Day"),
            Ranks("Sergeant", "Reach 7 Day"),
            Ranks("Master Sergeant", "Reach 10 Day"),
            Ranks("Knight", "Reach 14 Day"),
            Ranks("Knight-Lieutenant", "Reach 21 Day"),
            Ranks("Knight-Captain", "Reach 30 Day"),
            Ranks("Knight-Champion", "Reach 60 Day"),
            Ranks("Champion of the Light", "Reach 90 Day"),
            Ranks("Commander", "Reach 120 Day"),
            Ranks("Conqueror", "Reach 150 Day"),
            Ranks("Marshal", "Reach 180 Day"),
            Ranks("Field Marshal", "Reach 240 Day"),
            Ranks("Grand Marshal", "Reach 300 Day"),
            Ranks("High Overlord", "Reach 365 Day"),
            Ranks("The Immortal", "Reach 500 Day"),
        )

    }

}
