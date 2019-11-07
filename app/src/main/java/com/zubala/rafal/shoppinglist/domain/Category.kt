package com.zubala.rafal.shoppinglist.domain

data class Category(val id: Long, val name: String)

fun List<Category>.asNames(): List<String> {
    return map { it.name }
}