package com.kat4x.alyxnews.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kat4x.alyxnews.R
import com.kat4x.alyxnews.TopSpacingDecoration
import com.kat4x.alyxnews.models.innerUse.ItemNews
import kotlinx.android.synthetic.main.news_fragment.*

class NewsFragment : Fragment(),
    NewsListAdapter.Interaction {

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsListAdapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            viewModel =
                ViewModelProvider(it).get(NewsViewModel::class.java)
        } ?: throw Exception("Invalid activity")

        subscribeObserver()
        initUi()
    }

    private fun initUi() {
        viewModel.getNews()
        srl.setOnRefreshListener {
            viewModel.getNews()
            viewModel.isRefresh.value = true
        }

        news_recycler.apply {
            layoutManager = LinearLayoutManager(context)
            val topSpacingDec = TopSpacingDecoration(60)
            addItemDecoration(topSpacingDec)
            newsListAdapter = NewsListAdapter(this@NewsFragment)
            adapter = newsListAdapter
        }
    }

    private fun subscribeObserver() {
        viewModel.newsList.observe(viewLifecycleOwner, Observer { news ->
            newsListAdapter.submitList(news)
        })
        viewModel.isRefresh.observe(viewLifecycleOwner, Observer {
            srl.isRefreshing = it
        })
    }

    override fun onItemSelected(position: Int, item: ItemNews) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private val TAG = NewsFragment::class.java.simpleName
    }
}
