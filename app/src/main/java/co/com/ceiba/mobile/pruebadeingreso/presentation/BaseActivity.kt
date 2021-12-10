package co.com.ceiba.mobile.pruebadeingreso.presentation

import android.app.AlertDialog
import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import co.com.ceiba.mobile.pruebadeingreso.R
import com.google.android.material.snackbar.Snackbar
import dmax.dialog.SpotsDialog

open class BaseActivity : AppCompatActivity() {

    private lateinit var dialog: AlertDialog

    fun Context.progressBar(isVisibility: Boolean) {

        if (isVisibility) {
            dialog = SpotsDialog.Builder().setContext(this)
                .setMessage(getString(R.string.message_dialog))
                .setCancelable(false)
                .build()
                .apply {
                    show()
                }


        } else if (dialog.isShowing) dialog.dismiss()

    }

    fun showSnackbar(content: View) {
        Snackbar.make(content, SERVER_ERROR, Snackbar.LENGTH_SHORT).show()
    }

    companion object{
        const val SERVER_ERROR = "SERVER ERROR"
        const val USER_KEY = "user key"
    }

}