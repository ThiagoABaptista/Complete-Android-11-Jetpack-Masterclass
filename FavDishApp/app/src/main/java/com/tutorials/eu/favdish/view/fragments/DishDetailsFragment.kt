package com.tutorials.eu.favdish.view.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.tutorials.eu.favdish.R
import com.tutorials.eu.favdish.databinding.FragmentDishDetailsBinding
import java.io.IOException
import java.util.*

class DishDetailsFragment : Fragment() {

    // TODO Step 7: Create a ViewBinding variable.
    // START
    private var mBinding: FragmentDishDetailsBinding? = null
    // END

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // TODO Step 8: Initialize the mBinding variable.
        // START
        mBinding = FragmentDishDetailsBinding.inflate(inflater, container, false)
        return mBinding!!.root
        // END
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: DishDetailsFragmentArgs by navArgs()
        // TODO Step 10: Remove the log and populate the data to UI.
        // START
        args.let {
            try {
                // Load the dish image in the ImageView.
                Glide.with(requireActivity())
                    .load(it.dishDetails.image)
                    .centerCrop()
                    .listener(object: RequestListener<Drawable>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Log.e("TAG","ERROR Loading the image",e)
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            resource.let {
                                Palette.from(resource!!.toBitmap()).generate(){
                                        palette -> {
                                            val intColor = palette?.vibrantSwatch?.rgb ?: 0
                                            mBinding!!.rlDishDetailMain.setBackgroundColor(intColor)
                                        }
                                }
                            }
                            return false

                        }

                    })
                    .into(mBinding!!.ivDishImage)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            mBinding!!.tvTitle.text = it.dishDetails.title
            mBinding!!.tvType.text =
                it.dishDetails.type.capitalize(Locale.ROOT) // Used to make first letter capital
            mBinding!!.tvCategory.text = it.dishDetails.category
            mBinding!!.tvIngredients.text = it.dishDetails.ingredients
            mBinding!!.tvCookingDirection.text = it.dishDetails.directionToCook
            mBinding!!.tvCookingTime.text =
                resources.getString(R.string.lbl_estimate_cooking_time, it.dishDetails.cookingTime)
        }
        // END
    }

    // TODO Step 9: Override the onDestroy function to make the mBinding null that is avoid the memory leaks. This we have not done before because the AllDishesFragment because when in it the onDestroy function is called the app is killed. But this is the good practice to do it.
    // START
    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
    // END
}