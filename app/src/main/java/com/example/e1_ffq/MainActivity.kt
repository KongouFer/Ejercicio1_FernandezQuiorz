package com.example.e1_ffq

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.e1_ffq.databinding.ActivityMainBinding
import kotlin.random.Random
import java.text.DecimalFormat

val a_pagar = Random.nextInt(100,5001)
var length: Int = 0

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            val decimalFormat = DecimalFormat("###,###,###.00")
            Pago.text = getString(R.string.amount_placeholder, decimalFormat.format(a_pagar))
            binding.ivClose.setOnClickListener{finish()
            }
            binding.Pagar.setOnClickListener{
                if(binding.datoNombre.text.isEmpty() || binding.datosCorreo.text.isEmpty() || binding.datoCvv.text.isEmpty() || binding.datoFecha.text.isEmpty() || binding.datosTarjeta.text.isEmpty()){
                    Toast.makeText(this@MainActivity, resources.getString(R.string.Toast_ingresar), Toast.LENGTH_SHORT).show()
                    binding.datoNombre.error = resources.getString(R.string.txt_required)
                    binding.datoNombre.requestFocus()
                    binding.datosCorreo.error = resources.getString(R.string.txt_required)
                    binding.datosCorreo.requestFocus()
                    binding.datoCvv.error = resources.getString(R.string.txt_required)
                    binding.datoCvv.requestFocus()
                    binding.datoFecha.error = resources.getString(R.string.txt_required)
                    binding.datoFecha.requestFocus()
                    binding.datosTarjeta.error = resources.getString(R.string.txt_required)
                    binding.datosTarjeta.requestFocus()
                }else{
                    val intent = Intent(this@MainActivity, Operation::class.java)
                    startActivity(intent)
                }
            }
            binding.Paypal.setOnClickListener {
                Toast.makeText(this@MainActivity,resources.getString(R.string.Toast_falta_datos), Toast.LENGTH_SHORT).show()
            }
            binding.Wallet.setOnClickListener{
                Toast.makeText(this@MainActivity, resources.getString(R.string.Toast_falta_datos), Toast.LENGTH_SHORT).show()
            }
            binding.Credito.setOnClickListener{
                Toast.makeText(this@MainActivity, resources.getString(R.string.Toast_llenar), Toast.LENGTH_SHORT).show()
            }
            ArrayAdapter.createFromResource(this@MainActivity,R.array.tipotarjeta,android.R.layout.simple_spinner_item).also{
                adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                TipoTarjeta.adapter = adapter
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.zeldatok)
        mediaPlayer.start()

    }
    override fun onRestart() {
        super.onRestart()
        mediaPlayer.seekTo(length)
        mediaPlayer.start()
    }
    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
        length=mediaPlayer.currentPosition
    }


}
