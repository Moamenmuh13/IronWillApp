package com.example.ironwillapp.data

import com.example.ironwillapp.models.History

class HistoryData {
    fun historyData(): MutableList<History> {
        return mutableListOf(
            History("7 Day", "2 jun 2021 05:30", "9 jun 2021 07:30", "No reason"),
            History("5 Day", "4 sep 2021 05:30", "9 sep 2021 07:30", "No reason"),
            History("4 Day", "2 aug 2021 05:30", "6 aug 2021 07:30", "No reason"),
            History("8 Day", "2 oct 2021 05:30", "10 oct 2021 07:30", "No reason"),
            History("3 Day", "28 aug 2021 05:30", "31 aug 2021 07:30", "No reason"),
        )

    }

}
