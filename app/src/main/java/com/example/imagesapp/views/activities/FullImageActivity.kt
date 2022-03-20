package com.example.imagesapp.views.activities

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.example.imagesapp.R
import com.example.imagesapp.views.custom.DialogUtils
import com.example.imagesapp.views.custom.ScaleImageView
import com.github.chrisbanes.photoview.PhotoView

class FullImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_image)

        val imageview = findViewById<PhotoView>(R.id.ivFullImage)
        intent.extras?.let {
            val url = it.getString("url","")

            if(!url.equals("")){
                DialogUtils.ShowProgress(this,"Loading Image....")
                Glide.with(this).load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .transform(CenterCrop(), RoundedCorners(12))
                    .listener(object : RequestListener<Drawable?> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any,
                            target: Target<Drawable?>,
                            isFirstResource: Boolean
                        ): Boolean {
                            DialogUtils.HideDialog()
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any,
                            target: Target<Drawable?>,
                            dataSource: DataSource,
                            isFirstResource: Boolean
                        ): Boolean {
                            DialogUtils.HideDialog()
                            return false
                            // holder.ivFrontImage.setImageDrawable(resource);
                        }
                    })
                    .into(object: SimpleTarget<Drawable>(){
                        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                            imageview.setImageDrawable(resource)
                        }
                    })
            }

        }
    }
}