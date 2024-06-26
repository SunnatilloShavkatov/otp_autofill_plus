package uz.shs.otp_autofill_plus

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status

@Suppress("DEPRECATION")
class SmsUserConsentReceiver : BroadcastReceiver() {

    lateinit var smsBroadcastReceiverListener: SmsUserConsentBroadcastReceiverListener

    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent?.action == SmsRetriever.SMS_RETRIEVED_ACTION) {

            val extras = intent.extras
            val smsRetrieverStatus = extras?.get(SmsRetriever.EXTRA_STATUS) as Status

            when (smsRetrieverStatus.statusCode) {
                CommonStatusCodes.SUCCESS -> {
                    extras.getParcelable<Intent>(SmsRetriever.EXTRA_CONSENT_INTENT)?.also {
                        smsBroadcastReceiverListener.onSuccess(it)
                    }
                }

                CommonStatusCodes.TIMEOUT -> {
                    try {
                        smsBroadcastReceiverListener.onFailure()
                    } catch (e: Exception) {
                        ///DO NOTHING, end listening
                    }
                }
            }
        }
    }

    interface SmsUserConsentBroadcastReceiverListener {
        fun onSuccess(intent: Intent?)
        fun onFailure()
    }
}