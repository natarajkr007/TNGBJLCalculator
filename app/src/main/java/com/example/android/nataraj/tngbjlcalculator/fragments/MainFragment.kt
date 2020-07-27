package com.example.android.nataraj.tngbjlcalculator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.nataraj.tngbjlcalculator.R
import kotlinx.android.synthetic.main.fragment_main.*


/**
 * Created by nataraj on 26/6/20.
 */

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAGLSeven.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_AGLSeven)
        }
    }
}