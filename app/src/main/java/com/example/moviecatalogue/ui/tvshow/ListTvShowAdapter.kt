package com.example.moviecatalogue.ui.tvshow

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogue.data.Content
import com.example.moviecatalogue.databinding.ItemContentBinding

class ListTvShowAdapter :
    RecyclerView.Adapter<ListTvShowAdapter.ListViewHolder>() {

    private val listContents = ArrayList<Content>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setData(contents: List<Content>?) {
        if (contents == null) return
        this.listContents.clear()
        this.listContents.addAll(contents)
    }

    inner class ListViewHolder(private val binding: ItemContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Content) {
            with(binding) {
                tvTitle.text = data.title
                tvDescription.text = data.description
                tvReleaseDate.text = data.releaseDate

                Glide.with(itemView.context)
                    .load(Uri.parse("file:///android_asset/tv_shows/${data.imagePath}"))
                    .into(imgPoster)

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(data, false) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listContents[position])
    }

    override fun getItemCount(): Int = listContents.size

    override fun getItemId(position: Int) = position.toLong()

    interface OnItemClickCallback {
        fun onItemClicked(data: Content, isAttend: Boolean)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}