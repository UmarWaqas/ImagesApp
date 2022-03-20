package com.example.imagesapp.adapters

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
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
import com.example.imagesapp.models.Hits
import com.example.imagesapp.views.custom.DialogUtils
import java.lang.Exception
import java.util.*

/**
 * Created by Umar on 21-Feb-22.
 */
class ImagesAdapter(mContext: Context, mParents: List<Hits>, layout:Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    private var mParents: List<Hits>

    private val context: Context
    private val layoutId: Int

    private fun multiViewHolder(parent: ViewGroup, viewType: Int=0): RecyclerView.ViewHolder? {
         when (viewType) {
            R.layout.item_a -> {
                val contactView: View = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_a, parent, false)
                return ViewHolder(contactView)
            }


        }
        return null

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        //LayoutInflater inflater = LayoutInflater.from(getContext());
        return multiViewHolder(parent,viewType)!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try {
            bindMultiViews(holder, position)
        } catch (e: Exception) {
            e.printStackTrace()
           // Utilities.showMessage(context, e.localizedMessage)
        }
    }



    override fun getItemCount(): Int {
        /*if (layoutId==R.layout.item_f)
            return 11
        else*/
        return mParents.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return layoutId
    }

    private fun bindMultiViews(holder: RecyclerView.ViewHolder?, position: Int, type: Int =0) {
      //  Collections.shuffle(this.mParents)
        val model: Hits = mParents.get(position)
        val viewHolder = holder as ViewHolder?
        var url:String? =model.previewURL
        viewHolder?.setIsRecyclable(false)

        viewHolder?.ivImage?.let {

                /*Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .transform(RoundedCorners(15))
                    .placeholder(R.drawable.placeholder).into(viewHolder.ivImage)*/

            Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(CenterCrop(), RoundedCorners(15))
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
                    }
                })
                .into(object: SimpleTarget<Drawable>(){
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        viewHolder.ivImage.setImageDrawable(resource)
                    }
                })


            viewHolder?.ivImage?.setOnClickListener {
                DialogUtils.showConfirmationDialog(context as Activity,model.largeImageURL)
            }


            }


    } //end of method bindMultiViews....



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var  ivImage: ImageView

        init {
                var imageId=R.id.ivImage
            try {
                imageId?.let {

                    ivImage = itemView.findViewById(R.id.ivImage)
                }

            } catch (e: Exception) {
            }
        }
    }

    init {
        this.mParents = mParents
        context = mContext
        layoutId=layout

    }
} //end of class FixturesInnerAdapter....
