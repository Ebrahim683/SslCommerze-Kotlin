package com.example.sslcommerzekotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCCustomerInfoInitializer
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCProductInitializer
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCProductInitializer.ProductProfile.TravelVertical
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCShipmentInfoInitializer
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCShipmentInfoInitializer.ShipmentDetails
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization
import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfoModel
import com.sslwireless.sslcommerzlibrary.model.util.SSLCCurrencyType
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType
import com.sslwireless.sslcommerzlibrary.view.singleton.IntegrateSSLCommerz
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCTransactionResponseListener


class MainActivity : AppCompatActivity(), SSLCTransactionResponseListener {
    lateinit var tv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv = findViewById(R.id.tv)

        var amount = intent.getStringExtra("amount")
        val sslCommerzInitialization = SSLCommerzInitialization(
            "googl611cf29fa3b46",
            "googl611cf29fa3b46@ssl",
            amount!!.toDouble(),
            SSLCCurrencyType.BDT,
            "123456789098765",
            "yourProductType",
            SSLCSdkType.TESTBOX
        )


        var sslCCustomerInfoInitializer:SSLCCustomerInfoInitializer = SSLCCustomerInfoInitializer(
            "CustomerName",
            "customerEmail",
            "customerAddress",
            "",
            "1341",
            "Bangladesh",
            "1234567890",
        )

        var sslCProductInitializer: SSLCProductInitializer = SSLCProductInitializer(
            "productName",
            "productCategory",
            TravelVertical(
                "productProfile",
                "hoursTillDeparture",
                "flightType",
                "pnr",
                "journeyFromTo"
            )
        )

        val shipmentInfoInitializer = SSLCShipmentInfoInitializer(
            "Courier",
            2, ShipmentDetails(
                "AA", "Address 1",
                "Dhaka", "1000", "BD"
            )
        )

        IntegrateSSLCommerz
            .getInstance(this)
            .addSSLCommerzInitialization(sslCommerzInitialization)
            .addCustomerInfoInitializer(sslCCustomerInfoInitializer)
            .addProductInitializer(sslCProductInitializer)
            .buildApiCall(this)


    }

    @SuppressLint("SetTextI18n")
    override fun transactionSuccess(s: SSLCTransactionInfoModel?) {
        tv.text = "Message1 : ${s?.apiConnect + s?.status.toString()}"
    }

    @SuppressLint("SetTextI18n")
    override fun transactionFail(s: String?) {
        tv.text = "Message2 : $s"
    }
    @SuppressLint("SetTextI18n")
    override fun merchantValidationError(s: String?) {
        tv.text = "Message3 : $s"
    }
}

