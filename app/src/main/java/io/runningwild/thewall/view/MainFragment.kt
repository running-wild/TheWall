package io.runningwild.thewall.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import io.runningwild.thewall.R
import io.runningwild.thewall.view.adapter.StayListAdapter

class MainFragment : Fragment() {

    companion object {
        val TAG = MainFragment::class.java.simpleName

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}