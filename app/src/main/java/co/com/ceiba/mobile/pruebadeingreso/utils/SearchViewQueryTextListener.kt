package co.com.ceiba.mobile.pruebadeingreso.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import co.com.ceiba.mobile.pruebadeingreso.utils.SearchViewQueryTextCallback

fun EditText.addTextChangedListener(callback: SearchViewQueryTextCallback) {

    addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(query: Editable?) {
        }

        override fun beforeTextChanged(query: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(query: CharSequence, start: Int, before: Int, count: Int) {
            callback.onTextChanged(query)
        }
    })
}