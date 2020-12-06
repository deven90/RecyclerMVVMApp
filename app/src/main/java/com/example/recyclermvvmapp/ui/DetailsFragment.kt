package com.example.recyclermvvmapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclermvvmapp.R
import com.example.recyclermvvmapp.utility.EqualSpacingItemDecoration
import com.example.recyclermvvmapp.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

/**
 * Details Fragment to show details of the place
 * It will show list of details about place
 * and will show error message if facing issue in getting response from web api.
 */
class DetailsFragment : Fragment() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var detailsAdapter: DetailsAdapter
    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsAdapter = DetailsAdapter()

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.getResponse()?.observe(this,
            { response ->
                rootView.swipeRefreshLayout.isRefreshing = false
                if (response != null) {
                    val itr = response.rows.listIterator()
                    while (itr.hasNext()) {
                        val row = itr.next()
                        if (row.title.isNullOrEmpty() && row.description.isNullOrEmpty() && row.imageHref.isNullOrEmpty()) {
                            itr.remove()
                        }
                    }
                    detailsAdapter.setResults(response.rows)
                    showRecycler()
                }
            })

        viewModel.getIsRefreshing().observe(this, Observer { isRefreshing ->
            rootView.swipeRefreshLayout.isRefreshing = isRefreshing
        })

        viewModel.getFailureData()?.observe(this, Observer {
            rootView.swipeRefreshLayout.isRefreshing = false
            val msg = if (it.message.isNullOrEmpty()) "Something went wrong!!" else it.message
            showError(msg!!)
        })

        viewModel.getIsRefreshing().observe(this, {
            rootView.swipeRefreshLayout.isRefreshing = it
        })
    }

    /**
     * Hides RecyclerView and shows error text
     */
    fun showError(errorString: String) {
        rootView.tvErrorMessage.text = errorString
        rootView.tvErrorMessage.visibility = View.VISIBLE
        rootView.recyclerItems.visibility = View.GONE
    }

    /**
     * Shows RecyclerView and hides error text
     */
    fun showRecycler() {
        rootView.tvErrorMessage.visibility = View.GONE
        rootView.recyclerItems.visibility = View.VISIBLE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_list, container, false)

        rootView.recyclerItems.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(EqualSpacingItemDecoration(16, EqualSpacingItemDecoration.VERTICAL))
            adapter = detailsAdapter
        }

        rootView.swipeRefreshLayout.setOnRefreshListener { viewModel.getItems() }
        return rootView
    }

}