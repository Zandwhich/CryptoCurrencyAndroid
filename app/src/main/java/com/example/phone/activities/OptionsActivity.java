package com.example.phone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.phone.R;
import com.example.phone.utility.currencies.CryptoCurrency;
import com.example.phone.utility.currencies.FiatCurrency;

import java.util.ArrayList;
import java.util.List;

public class OptionsActivity extends AppCompatActivity {

    private static final String TAG = "OptionsActivity";

    public static final String SHARED_PREFERENCES = "OPTIONS_PREFERENCES";
    public static final String CRYPTO_SELECTED = "CRYPTO_SELECTED";
    public static final String FIAT_SELECTED = "FIAT_SELECTED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        final SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);

        Spinner cryptoSpinner = findViewById(R.id.spinner_choose_crypto);
        Spinner fiatSpinner = findViewById(R.id.spinner_choose_fiat);

        List<CharSequence> cryptos = new ArrayList<>();
        for (CryptoCurrency crypto : CryptoCurrency.values()) {
            cryptos.add(crypto.getAbbreviatedName());
        }

        List<CharSequence> fiats = new ArrayList<>();
        for (FiatCurrency fiat : FiatCurrency.values()) {
            fiats.add(fiat.getAbbreviatedName());
        }

        ArrayAdapter<CharSequence> cryptoSpinnerAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_list_item, R.id.spinner_item, cryptos);
        cryptoSpinner.setAdapter(cryptoSpinnerAdapter);
        cryptoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(CRYPTO_SELECTED, adapterView.getItemAtPosition(i).toString());
                editor.apply();

                Log.i(TAG, "Item at position " + i + ": " + adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(CRYPTO_SELECTED, CryptoCurrency.DEFAULT_CRYPTO.getAbbreviatedName());
                editor.apply();
            }
        });

        ArrayAdapter<CharSequence> fiatSpinnerAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_list_item, R.id.spinner_item, fiats);
        fiatSpinner.setAdapter(fiatSpinnerAdapter);
        fiatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(FIAT_SELECTED, adapterView.getItemAtPosition(i).toString());
                editor.apply();

                Log.i(TAG, "Item at position " + i + ": " + adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(FIAT_SELECTED, FiatCurrency.DEFAULT_FIAT.getAbbreviatedName());
                editor.apply();
            }
        });

        String defaultCrypto = sharedPreferences.getString(CRYPTO_SELECTED, CryptoCurrency.DEFAULT_CRYPTO.getAbbreviatedName());
        String defaultFiat = sharedPreferences.getString(FIAT_SELECTED, FiatCurrency.DEFAULT_FIAT.getAbbreviatedName());

        int defaultCryptoPosition = cryptoSpinnerAdapter.getPosition(defaultCrypto);
        int defaultFiatPosition = fiatSpinnerAdapter.getPosition(defaultFiat);

        Log.i(TAG, "Crypto Position: " + defaultCryptoPosition);
        Log.i(TAG, "Fiat Position: " + defaultFiatPosition);

        cryptoSpinner.setSelection(defaultCryptoPosition);
        fiatSpinner.setSelection(defaultFiatPosition);
    }


}