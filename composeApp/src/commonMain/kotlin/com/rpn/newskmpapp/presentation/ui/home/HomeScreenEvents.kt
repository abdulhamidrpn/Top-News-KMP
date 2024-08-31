package com.rpn.newskmpapp.presentation.ui.home

sealed class HomeScreenEvents {
    data object LoadCurrentlyReading : HomeScreenEvents()
    data object LoadAlreadyRead : HomeScreenEvents()
    data object LoadHeadlines : HomeScreenEvents()
}

