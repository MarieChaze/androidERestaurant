package fr.isen.chaze.androiderestaurant

import android.annotation.SuppressLint
import android.bluetooth.le.ScanResult
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


internal class BLEscanAdapter (val bleList: ArrayList<ScanResult>, val clickListener: (ScanResult) -> Unit) : RecyclerView.Adapter<BLEscanAdapter.BleScanViewHolder>() {

        inner class BleScanViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var rssi: TextView = view.findViewById(R.id.bleRssi)
            var name: TextView = view.findViewById(R.id.bleName)
            var address: TextView = view.findViewById(R.id.bleAddress)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BleScanViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.device,
                    parent,
                    false
                )
            return BleScanViewHolder(itemView)
        }

        @SuppressLint("MissingPermission")
        override fun onBindViewHolder(holder: BleScanViewHolder, position: Int) {
            val result = bleList[position]
            holder.rssi.text = result.rssi.toString()
            holder.address.text = result.device.address
            holder.name.text = result.device.name

            holder.itemView.setOnClickListener {
                clickListener(result) }

        }

        fun addResultToBleList(scanResult: ScanResult){
                 bleList.add(scanResult)

         }


        override fun getItemCount(): Int {
            return bleList.size
        }
}