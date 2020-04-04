package com.eburg_soft.contactsapp.model.source.database.entity

object Education {
    private var start: String = ""
    private var end: String = ""

    fun getStart(): String = start

    fun getEnd(): String = end

    fun setStart(start: String) {
        this.start = start
    }

    fun setEnd(end: String) {
        this.end = end
    }
}