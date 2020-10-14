package com.example.myfriendapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.my_freinds_fragment.*

class MyFreinds_fragments : Fragment () {

    private var myfreindsDAO: MyFreindsDAO? = null
    private var db: AppDatabase? = null
    private var listTeman : ArrayList<MyFreinds>? = null

    companion object{
        fun newInstance() : MyFreinds_fragments {
            return MyFreinds_fragments()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_freinds_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initlocalDB()
        initView()
    }

    private fun initView() {
        addfreindsbtn.setOnClickListener{ (activity as MainActivity).showMyFreindsAdd_fragments()}
        ambildataTeman()
    }

    private fun ambildataTeman() {
        listTeman = ArrayList()
        myfreindsDAO?.ambilSemuaTeman()?.observe(this, Observer { r ->
            listTeman = r as ArrayList<MyFreinds>?
            when {
                listTeman?.size == 0 -> Toast.makeText(context, "Belum ada data teman !", Toast.LENGTH_SHORT).show()
                else -> {
                    tampilTeman()
                }
            }
        })
    }

    private fun initlocalDB(){
        db = AppDatabase.getAppDataBase(activity!!)
        myfreindsDAO = db?.myFreindsDAO()
    }



    private fun tampilTeman(){
        list_freind.layoutManager = LinearLayoutManager(activity)
        list_freind.adapter = MyFriendsAdapter(activity!!, listTeman!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}