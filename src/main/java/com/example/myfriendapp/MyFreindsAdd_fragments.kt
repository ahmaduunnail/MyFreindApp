package com.example.myfriendapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.add_freinds_fragment.*
import kotlinx.android.synthetic.main.my_freinds_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyFreindsAdd_fragments : Fragment() {
    private var alamatInput: String = ""
    private var telpInput: String = ""
    private var genderInput: String = ""
    private var namaInput: String = ""
    private var emailInput: String = ""
    private var db: AppDatabase? = null
    private var myfreindsDAO: MyFreindsDAO? = null

    companion object {
        fun newInstance(): MyFreindsAdd_fragments {
            return MyFreindsAdd_fragments()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_freinds_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initlocalDB()
        initView()
    }

    private fun initlocalDB() {
        db = AppDatabase.getAppDataBase(activity!!)
        myfreindsDAO = db?.myFreindsDAO()
    }

    private fun initView() {
        btnsave.setOnClickListener { validasiInput() }
        setDataSpinnerGender()
    }

    private fun setDataSpinnerGender() {
        val adapter = ArrayAdapter.createFromResource(
            activity!!,
            R.array.gender_list,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        edtgender.adapter = adapter
    }

    private fun validasiInput() {
        namaInput = edtname.text.toString()
        emailInput = edtemail.text.toString()
        telpInput = edtphone.text.toString()
        alamatInput = edtaddress.text.toString()
        genderInput = edtgender.selectedItem.toString()

        when {
            namaInput.isEmpty() -> edtname.error = "Nama tidak boleh kosong !"
            genderInput.equals("Pilih Kelamin") -> Toast.makeText(
                context,
                "Kelamin hrus dipilih",
                Toast.LENGTH_SHORT
            ).show()
            telpInput.isEmpty() -> edtphone.error = "No. telepon tidak boleh kosong !"
            emailInput.isEmpty() -> edtemail.error = "Email tidak boleh kosong !"
            alamatInput.isEmpty() -> edtaddress.error = "Alamat tidak boleh kosong !"
            else -> {
                val teman = MyFreinds(
                    name = namaInput,
                    gender = genderInput,
                    email = emailInput,
                    address = alamatInput,
                    phone = telpInput
                )
                tambahDataTeman(teman)
            }
        }
    }

    private fun tambahDataTeman(teman: MyFreinds): Job {
        return GlobalScope.launch {
            myfreindsDAO?.tambahTeman(teman)
            (activity as MainActivity).showMyFreinds_fragments()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}