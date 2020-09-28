package com.example.madlevel3task2

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.os.persistableBundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_portal.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class PortalFragment : Fragment(){

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals,{ item: Portal -> itemClicked(item)})

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews(){
        rvPortals.layoutManager = GridLayoutManager(activity, 2)
        rvPortals.adapter = portalAdapter
        observeAddPortalResult()
    }


    private fun observeAddPortalResult(){
        var portalText : String
        setFragmentResultListener(REQ_PORTAL_KEY){key, bundle ->
            bundle.getString(BUNDLE_PORTAL_KEY)?.let {
                portalText = it
                bundle.getString(BUNDLE_URL_KEY)?.let {
                    val portal = Portal(portalText, it)
                    portals.add(portal)
                    portalAdapter.notifyDataSetChanged()
                }
            }

            }
        }
    }


    private fun itemClicked(item : Portal) {
        Log.println(Log.INFO,item.portalText, item.portalUrl)
    }

