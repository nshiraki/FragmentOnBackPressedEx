package com.example.fragmentonbackpressedex

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.blank_fragment.*

/**
 * onBackPressedDispatcherを利用したFragmentのバックキー制御
 *
 */
class BlankFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.blank_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.get(ARGS_NAME)?.let { textView.text = it.toString() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d(LOG_TAG, "handleOnBackPressed(): name=${textView.text}")
                    closeFragment()
                }
            })
    }

    private fun closeFragment() = requireActivity().supportFragmentManager.beginTransaction()
        .remove(this).commit()

    companion object {
        private val LOG_TAG = BlankFragment::class.java.simpleName
        private const val ARGS_NAME = "ARGS_NAME"

        fun newInstance(name: String) = BlankFragment().also { f ->
            f.arguments = Bundle().also {
                it.putString(ARGS_NAME, name)
            }
        }
    }
}

