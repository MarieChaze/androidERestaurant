package fr.isen.chaze.androiderestaurant


import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.chaze.androiderestaurant.databinding.ActivityBleScanBinding

class BleScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBleScanBinding
    private val ENABLE_BLUETOOTH_REQUEST_CODE = 1
    private val ALL_PERMISSION_REQUEST_CODE = 100
    private var scanning = false
    private var adapter: BLEscanAdapter? = null

    private val bluetoothAdapter: BluetoothAdapter? by lazy {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBleScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when {
            bluetoothAdapter?.isEnabled == true ->
                startLeScanWithPermission(true)
            bluetoothAdapter != null ->
                askBluetoothPermission()
            else -> {
                displayBLEUnAvailable()
            }
        }
        binding.scanBleBtn.setOnClickListener {
           startLeScanWithPermission(!scanning)
        }
        binding.scanBleText.setOnClickListener {
            startLeScanWithPermission(!scanning)
        }

        adapter= BLEscanAdapter(arrayListOf()) {
            val intent = Intent ( this, DeviceDetailActivity::class.java)
            intent.putExtra("device", it)
            startActivity(intent)
        }
        binding.scanBleList.layoutManager = LinearLayoutManager(this)

        binding.scanBleList.adapter = adapter

    }

    override fun onStop(){
        super.onStop()
        startLeScanWithPermission(false)
    }

    private fun startLeScanWithPermission(enable: Boolean){
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ){
            startLeScanBLE(enable)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION
                /*Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.BLUETOOTH_SCAN*/
            ), ALL_PERMISSION_REQUEST_CODE)
        }

    }

    @SuppressLint("MissingPermission")
    private fun startLeScanBLE(enable: Boolean) {
        bluetoothAdapter?.bluetoothLeScanner?.apply {
            if(enable) {
                scanning = true
                startScan(scanCallback)
            } else {
                scanning = false
                stopScan(scanCallback)
            }
            scanToggle()
        }
    }

    private val scanCallback = object: ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            Log.d("BLEScanActivity", "result: ${result.device.address}, rssi : ${result.rssi}")
            adapter?.apply {
                addResultToBleList(result)
                notifyDataSetChanged()
            }
        }
    }

    private fun displayBLEUnAvailable() {
        binding.scanBleBtn.isVisible = false
        binding.scanBleText.text = getString(R.string.ble_scan_device_error)
        binding.progressBar.isVisible = false
    }

    private fun askBluetoothPermission(){
        val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        if(ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.BLUETOOTH_CONNECT
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startActivityForResult(enableBtIntent, ENABLE_BLUETOOTH_REQUEST_CODE)
        }
    }


    private fun initScanPage() {
        binding = ActivityBleScanBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.scanBleText.text = "Lancer le scan"
        binding.scanBleBtn.setImageResource(R.drawable.ic_play)
        binding.progressBar.visibility = View.INVISIBLE
        binding.progressBar.isIndeterminate = true

        scanToggle()
    }

    private fun scanToggle() {
        if (!scanning) {
            binding.scanBleText.text = "Lancer le scan"
            binding.scanBleBtn.setImageResource(R.drawable.ic_play)
            binding.progressBar.visibility = View.INVISIBLE
        } else {
            binding.scanBleText.text = "Scan en cours..."
            binding.scanBleBtn.setImageResource(R.drawable.ic_pause)
            binding.progressBar.visibility = View.VISIBLE
        }

    }
}
