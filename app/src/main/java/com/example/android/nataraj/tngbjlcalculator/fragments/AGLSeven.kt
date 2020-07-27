package com.example.android.nataraj.tngbjlcalculator.fragments

/**
 * created by nataraj on 29-june-2020
 * */

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.android.nataraj.tngbjlcalculator.R
import com.example.android.nataraj.tngbjlcalculator.ResultBottomSheetDialog
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.fragment_agl_seven.*
import java.util.concurrent.TimeUnit

class AGLSeven : Fragment() {

    private var loanAmount = 0L
    private var days = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_agl_seven, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loanAmountView.doOnTextChanged { text, _, _, _ ->
            loanAmount = text.toString().toLong()
        }

        btnDatePicker.setOnClickListener {
            MaterialDatePicker.Builder.dateRangePicker().build().apply {
                addOnPositiveButtonClickListener {
                    setDate(it.first!!, it.second!!)
                }
            }.show(childFragmentManager, "range_date_picker")
        }

        btnCalculate.setOnClickListener {
            ResultBottomSheetDialog.getInstance(loanAmount, days, 7L)
                .show(childFragmentManager, "result_sheet")
        }
    }

    private fun setDate(from: Long, to: Long) {
        days = TimeUnit.DAYS.convert(to.minus(from), TimeUnit.MILLISECONDS)
        loanDisbursedDate.setText(DateFormat.format("dd/MM/yyyy", from))
        loanClosureDate.setText(DateFormat.format("dd/MM/yyyy", to))
    }
}