package com.kat4x.alyxnews.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kat4x.alyxnews.R
import com.kat4x.alyxnews.TopSpacingDecoration
import com.kat4x.alyxnews.models.innerUse.ItemNews
import kotlinx.android.synthetic.main.news_fragment.*

class NewsFragment : Fragment(),
    NewsListAdapter.Interaction {

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsListAdapter: NewsListAdapter
    private lateinit var navController: NavController

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
//            viewModel.getNews()
            viewModel.getTopNews()
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
        viewModel.link.value = item.url
        findNavController().navigate(R.id.action_nav_news_to_linkFragment, null)
    }

    companion object {
        private val TAG = NewsFragment::class.java.simpleName
    }
}
