package com.example.bipru.utils.watcher


import android.text.Editable
import android.text.TextWatcher

class SimpleTextWatcher(private val onTextChanged: (s: String) -> Unit) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    override fun afterTextChanged(s: Editable?) = Unit
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChanged.invoke(s.toString())
    }
}