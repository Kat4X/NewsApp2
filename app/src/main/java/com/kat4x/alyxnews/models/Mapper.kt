package com.kat4x.alyxnews.models

interface Mapper<I, O> {
    fun map(input: I): O
}