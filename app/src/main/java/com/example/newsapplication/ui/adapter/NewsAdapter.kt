package com.example.newsapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.newsapplication.R
import com.example.newsapplication.databinding.LayoutItemArticleBinding
import com.example.newsapplication.ui.model.Article
import com.facebook.drawee.view.SimpleDraweeView

class NewsAdapter : Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val binding: LayoutItemArticleBinding) :
        ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_item_article,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.also { view ->
            view.findViewById<SimpleDraweeView>(R.id.ivArticleImage).setImageURI(article.urlToImage)
            view.findViewById<TextView>(R.id.tvSource).text = article.source.name
            view.findViewById<TextView>(R.id.tvTitle).text = article.title
            view.findViewById<TextView>(R.id.tvDescription).text = article.description
            view.findViewById<TextView>(R.id.tvPublishedAt).text = article.publishedAt
            view.setOnClickListener {
                this.onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        this.onItemClickListener = listener
    }
}