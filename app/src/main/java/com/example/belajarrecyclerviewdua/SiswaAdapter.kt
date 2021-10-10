package com.example.belajarrecyclerviewdua

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SiswaAdapter(private val dataSet: MutableList<SiswaData>):RecyclerView.Adapter<SiswaAdapter.SiswaHolder>() {
    class SiswaHolder(view: View):RecyclerView.ViewHolder(view) {
        val nis = view.findViewById<TextView>(R.id.txtNIS)
        val nama = view.findViewById<TextView>(R.id.txtNamaSiswa)
        val status = view.findViewById<TextView>(R.id.txtStatus)
        val btnHapus = view.findViewById<Button>(R.id.btnDelete)
        val btnEdit = view.findViewById<Button>(R.id.btnEdit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiswaHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.data_siswa_adapter, parent, false
        )
        return SiswaHolder(view)
    }

    override fun onBindViewHolder(holder: SiswaHolder, position: Int) {
        holder.nis.text = dataSet[position].nisSiswa
        holder.nama.text = dataSet[position].namaSiswa
        holder.status.text = dataSet[position].statusSiswa

        // Menghapus data pada recycler view
        holder.btnHapus.setOnClickListener {
            dataSet.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, dataSet.size)
        }

        // Mengedit data pada recycler view
        holder.btnEdit.setOnClickListener {
            val context = holder.itemView.context
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.edit_data, null)
            val prevTextNis = dataSet[position].nisSiswa
            val prevTextNama = dataSet[position].namaSiswa
            val prevTextStatus = dataSet[position].statusSiswa
            val editNis = view.findViewById<TextView>(R.id.txtEditNIS)
            val editNama = view.findViewById<TextView>(R.id.txtEditNama)
            val editStatusBekerja = view.findViewById<RadioButton>(R.id.rbEditBekerja)
            val editStatusTBekerja = view.findViewById<RadioButton>(R.id.rbEditTBekerja)
            val editStatusWirausaha = view.findViewById<RadioButton>(R.id.rbEditWirausaha)
            editNis.text = prevTextNis
            editNama.text = prevTextNama
            if(prevTextStatus == "Bekerja"){
                editStatusBekerja.isSelected
            }else if(prevTextStatus == "Wirausaha"){
                editStatusWirausaha.isSelected
            }else{
                editStatusTBekerja.isSelected
            }

            AlertDialog.Builder(context)
                .setTitle("Edit Data Siswa")
                .setView(view)
                .setPositiveButton("Update", DialogInterface.OnClickListener {
                    dialogInterface, id ->
                    dataSet[position].nisSiswa = editNis.text.toString()
                    dataSet[position].namaSiswa = editNama.text.toString()
                    if(editStatusBekerja.isChecked){
                        dataSet[position].statusSiswa = "Bekerja"
                    }else if(editStatusWirausaha.isChecked){
                        dataSet[position].statusSiswa = "Wirausaha"
                    }else{
                        dataSet[position].statusSiswa = "Tidak Bekerja"
                    }
                    notifyDataSetChanged()
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, id ->  })
                .create().show()
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}