package com.sampleproject

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

class BindingAdapters {

    companion object {
        @BindingAdapter("entries", "layout")
        @JvmStatic
        fun <T> setEntries(
            viewGroup: ViewGroup,
            entries: List<T>?, layoutId: Int,
        ) {
            viewGroup.removeAllViews()
            if (entries != null) {
                val inflater = viewGroup.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                for (i in entries.indices) {
                    val entry = entries[i]
                    val binding = DataBindingUtil
                        .inflate<ViewDataBinding>(inflater, layoutId, viewGroup, true)
                    binding.setVariable(BR.data, entry)
                }
            }
        }

        @BindingAdapter("entries", "layout","listener")
        @JvmStatic
        fun <T> setEntries(
            viewGroup: ViewGroup,
            entries: List<T>?, layoutId: Int,
            listener: UserClickListener
        ) {
            viewGroup.removeAllViews()
            if (entries != null) {
                val inflater = viewGroup.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                for (i in entries.indices) {
                    val entry = entries[i]
                    val binding = DataBindingUtil
                        .inflate<ViewDataBinding>(inflater, layoutId, viewGroup, true)
                    binding.setVariable(BR.data, entry)
                    binding.setVariable(BR.listener, listener)
                }
            }
        }
    }
}