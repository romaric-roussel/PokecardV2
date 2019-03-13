package com.example.lpiem.pokecard
import com.bumptech.glide.Glide
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.ImageView


class ViewDialog {

    var dialog: Dialog? = null
    var activity:Context? = null
    //..we need the context else we can not create the dialog so get context in constructor

    fun ViewDialog(activity: Context) {
        this.activity = activity
    }
    fun showDialog() {

        dialog = Dialog(activity)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        //...set cancelable false so that it's never get hidden
        dialog?.setCancelable(false)
        //...that's the layout i told you will inflate later
        dialog?.setContentView(R.layout.loading_pokemon)

        //...initialize the imageView form infalted layout
        val gifImageView : ImageView = dialog!!.findViewById(R.id.custom_loading_imageView)


        //...now load that gif which we put inside the drawble folder here with the help of Glide

        Glide.with(activity)
                .load(R.drawable.pikachu)
                .into(gifImageView)

        //...finaly show it
        dialog?.show()
    }

    //..also create a method which will hide the dialog when some work is done
    fun hideDialog() {
        dialog?.dismiss()
    }
}