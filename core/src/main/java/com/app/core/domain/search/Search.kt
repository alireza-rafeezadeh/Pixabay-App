package com.app.core.domain.search

data class SearchResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)

data class Hit(
    val collections: Int?,
    val comments: Int,
    val downloads: Int,
    val id: Int,
    val imageHeight: Int,
    val largeImageURL: String,
    val likes: Int,
    val previewURL: String,
    val tags: String,
    val user: String,
    val views: Int,
)