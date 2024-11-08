package com.example.aplicativopizzaria

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY

class Payment : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //color StatusBar
        window.statusBarColor = Color.parseColor("E0E0E0")

        val name = intent.extras!!.getString("name")
        val amount = intent.extras!!.getInt("amount")
        val total = intent.extras!!.getDouble("total")
        val saucesAndDrinks = intent.extras!!.getString("saucesAndDrinks")
        val decimalFormat = DecimalFormat.getCurrencyInstance()

        binding.txtTotal.text = "$name \n Amount: $amount \n Sauces and Drinks: $saucesAndDrinks \n Total: ${decimalFormat.format(total)}"

        binding.btPay.setOnclickListener {
            if (binding.btCreditCard.isChecked) {
                val intent = Intent(this, ThankYouScreen::class.java)
                startActivity(intent)
                Toast.makeText(this, "Card Payment", Toast.LENGTH_SHORT).show()
            }else if (binding.btPix.IsChecked){

                binding.editPix.visibility = View.VISIBLE
                val pix = binding.editPix.text.toString()

                if (pix.isNotEmpty()){
                    val intent = Intent(this, ThankYouScreen::class.java)
                    intent.putExtra("UserPix", pix)
                    startActivity(intent)
                    Toast.makeText(this, "Payment with Pix", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this, "Fill in the pix field", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}