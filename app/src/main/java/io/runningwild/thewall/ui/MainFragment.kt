package io.runningwild.thewall.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import io.runningwild.thewall.R
import io.runningwild.thewall.ui.adapter.StayListAdapter

class MainFragment : Fragment() {

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(
            R.layout.fragment_main, container,
            false)
        val recyclerView = view.findViewById(R.id.recycler_view_stay_list) as RecyclerView
        recyclerView.adapter = StayListAdapter(context!!)
        return view
    }
}