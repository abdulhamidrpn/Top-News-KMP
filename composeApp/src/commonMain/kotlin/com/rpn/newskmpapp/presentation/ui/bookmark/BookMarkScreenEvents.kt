package com.rpn.newskmpapp.presentation.ui.bookmark

sealed class BookMarkScreenEvents {
    data object LoadCurrentlyReading : BookMarkScreenEvents()
    data object LoadAlreadyRead : BookMarkScreenEvents()
    data object LoadHeadlines : BookMarkScreenEvents()
}

