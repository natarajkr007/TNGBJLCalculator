package com.example.android.nataraj.tngbjlcalculator

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_result.*
import kotlin.math.ceil
import kotlin.math.floor


/**
 * Created by nataraj on 29/6/20.
 */

class ResultBottomSheetDialog : BottomSheetDialogFragment() {

    private var loanAmount: Long = 0L
    private var diffDays: Long = 0L
    private var roi: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().apply {
            loanAmount = getLong(LOAN_AMOUNT)
            diffDays = getLong(DIFF_DAYS)
            roi = getLong(ROI)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottomsheet_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isSubventionEligible = diffDays in 180L..365L
        val subventionAmount = floor(loanAmount * diffDays * 0.03)
        var loanClosureAmount = ceil(loanAmount * diffDays * 0.07)

        if (isSubventionEligible) {
            loanClosureAmount -= subventionAmount
        } else {
            subventionAmountContainer.isVisible = false
        }

        etSubventionAmount.setText(subventionAmount.toString())
        etLoanClosureAmount.setText(loanClosureAmount.toString())

        status.apply {
            background = ResourcesCompat.getDrawable(resources, R.drawable.bg_status, null)?.apply {
                colorFilter = PorterDuffColorFilter(
                    ResourcesCompat.getColor(
                        resources,
                        if (isSubventionEligible) R.color.eligible else R.color.not_eligible,
                        null
                    ),
                    PorterDuff.Mode.SRC_ATOP
                )
            }

            setText(if (isSubventionEligible) R.string.eligible_name else R.string.not_eligible_name)
        }
    }

    companion object {

        private const val LOAN_AMOUNT = "loan_amount"
        private const val DIFF_DAYS = "diff_days"
        private const val ROI = "roi"

        fun getInstance(loanAmount: Long, diffDays: Long, roi: Long): ResultBottomSheetDialog {
            return ResultBottomSheetDialog().apply {
                arguments = Bundle().apply {
                    putLong(LOAN_AMOUNT, loanAmount)
                    putLong(DIFF_DAYS, diffDays)
                    putLong(ROI, roi)
                }
            }
        }
    }
}