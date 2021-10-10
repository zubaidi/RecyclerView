package com.example.belajarrecyclerviewdua

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var inputNis: EditText
    private lateinit var inputNamaSiswa: EditText
    private lateinit var rbBekerja: RadioButton
    private lateinit var rbTBekerja: RadioButton
    private lateinit var rbWirausaha: RadioButton
    private lateinit var btnSimpan: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdater: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputNis = findViewById(R.id.txtInputNIS)
        inputNamaSiswa = findViewById(R.id.txtInputNama)
        rbBekerja = findViewById(R.id.rbBekerja)
        rbTBekerja = findViewById(R.id.rbTBekerja)
        rbWirausaha = findViewById(R.id.rbWirausaha)
        btnSimpan = findViewById(R.id.btnTambah)
        recyclerView = findViewById(R.id.listDataSiswa)
        val data = mutableListOf<SiswaData>()

        viewManager = LinearLayoutManager(this)
        recyclerAdater = SiswaAdapter(data)
        recyclerView.adapter = recyclerAdater
        recyclerView.layoutManager = viewManager

        btnSimpan.setOnClickListener {
            val nis = inputNis.text.toString()
            val nama = inputNamaSiswa.text.toString()
            var status = ""
            if(rbBekerja.isChecked){
                status = "Bekerja"
            }else if(rbWirausaha.isChecked){
                status = "Wirausaha"
            }else{
                status = "Tidak Bekerja"
            }
            val siswa = SiswaData(nis, nama, status)
            data.add(siswa)
            recyclerAdater.notifyDataSetChanged()
            inputNis.setText("")
            inputNamaSiswa.setText("")
            val rGroup = findViewById<RadioGroup>(R.id.radioGroup)
            rGroup.clearCheck()
        }

    }
}