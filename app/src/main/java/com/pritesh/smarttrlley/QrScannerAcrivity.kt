package com.pritesh.smarttrlley

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator

class QrScannerAcrivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_scanner_acrivity)

        val intentIntegrator = IntentIntegrator(this)
        intentIntegrator.setOrientationLocked(false)
        intentIntegrator.setPrompt("QR Scanner")
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        intentIntegrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)

        if (intentResult != null){
            val content = intentResult.contents
            if (content != null){
                openProduct(content)
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun openProduct(productId: String) {
        Toast.makeText(this, productId, Toast.LENGTH_SHORT).show()
    }

}