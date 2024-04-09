package com.example.e1_ffq

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.e1_ffq.databinding.ActivityOperacionBinding
import java.text.DecimalFormat
import kotlin.random.Random

class Operation : AppCompatActivity() {
    private lateinit var binding: ActivityOperacionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOperacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {

            val random1 = Random.nextInt(0,4)
            val decimalFormat = DecimalFormat("###,###,###.00")
            var random2 = Random.nextInt(a_pagar, 10000)
            if(random1<3){
                TextoOperacion.text=resources.getString(R.string.pago_realizado)
                Motivo.text=resources.getString(R.string.vacio)

                datoSaldo.text=getString(R.string.amount_placeholder, decimalFormat.format(random2))
                datoProducto.text=getString(R.string.amount_placeholder, decimalFormat.format(a_pagar))
                iResultado.setImageResource(R.drawable.feliz)
                datoFinal.text=getString(R.string.amount_placeholder, decimalFormat.format(random2-a_pagar))
            }else{
                random2 = Random.nextInt(0,a_pagar)
                TextoOperacion.text = resources.getString(R.string.pago_rechazado)
                datoSaldo.text=getString(R.string.amount_placeholder, decimalFormat.format(random2))
                datoProducto.text=getString(R.string.amount_placeholder, decimalFormat.format(a_pagar))
                textFinal.text=resources.getString(R.string.vacio)
                datoFinal.text=resources.getString(R.string.vacio)
                iResultado.setImageResource(R.drawable.triste)
            }

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }

}