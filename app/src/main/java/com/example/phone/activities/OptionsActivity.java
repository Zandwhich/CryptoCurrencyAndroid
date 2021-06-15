package com.example.phone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.phone.R;
import com.example.phone.activities.recyclerview.CompatibleEndpointAdapter;
import com.example.phone.utility.currencies.CryptoCurrency;
import com.example.phone.utility.currencies.FiatCurrency;
import com.example.phone.utility.network.endpoints.AbstractAPICall;
import com.example.phone.utility.network.endpoints.CoinBase.CoinBaseBuy;
import com.example.phone.utility.network.endpoints.CoinBase.CoinBaseSell;
import com.example.phone.utility.network.endpoints.CoinBase.CoinBaseSpot;
import com.example.phone.utility.network.endpoints.CoinCap;
import com.example.phone.utility.network.endpoints.CryptoCompare;

import java.util.ArrayList;
import java.util.List;

final public class OptionsActivity extends AppCompatActivity implements CompatibleEndpointAdapter.ParentActivity {

    private static final String TAG = "OptionsActivity";

    public static final String SHARED_PREFERENCES = "OPTIONS_PREFERENCES";
    public static final String CRYPTO_SELECTED = "CRYPTO_SELECTED";
    public static final String FIAT_SELECTED = "FIAT_SELECTED";
    public static final String CONVERSION_TYPE = "CONVERSION_TYPE"; // true is crypto/crypto, false is crypto/fiat

    // TODO: Figure out how to do this websites logic without having to copy the code over in multiple places
    private ArrayList<AbstractAPICall> allWebsites;
    private ArrayList<AbstractAPICall> supportedWebsites;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        this.sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);

        this.supportedWebsites = new ArrayList<>();
        this.allWebsites = new ArrayList<>();
        this.allWebsites.add(new CoinBaseBuy());
        this.allWebsites.add(new CoinBaseSell());
        this.allWebsites.add(new CoinBaseSpot());
        this.allWebsites.add(new CryptoCompare());
        this.allWebsites.add(new CoinCap());

        // TODO: This is where you left off
        SwitchCompat fiat_crypto_switch = findViewById(R.id.crypto_fiat_switch);
        fiat_crypto_switch.setOnCheckedChangeListener((compoundButton, b) -> sharedPreferences
                .edit()
                .putBoolean(CONVERSION_TYPE, b)
                .apply());

        Spinner cryptoSpinner = findViewById(R.id.spinner_choose_crypto);
        Spinner fiatSpinner = findViewById(R.id.spinner_choose_fiat);

        List<CharSequence> cryptos = new ArrayList<>();
        for (CryptoCurrency crypto : CryptoCurrency.values()) {
            cryptos.add(getString(crypto.getAbbreviatedName()));
        }

        List<CharSequence> fiats = new ArrayList<>();
        for (FiatCurrency fiat : FiatCurrency.values()) {
            fiats.add(getString(fiat.getAbbreviatedName()));
        }

        final Context thisContext = this;

        ArrayAdapter<CharSequence> cryptoSpinnerAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_list_item, R.id.spinner_item, cryptos);
        cryptoSpinner.setAdapter(cryptoSpinnerAdapter);
        cryptoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(CRYPTO_SELECTED, CryptoCurrency.getCryptocurrencyFromAbbreviatedName(adapterView.getItemAtPosition(i).toString(), thisContext).getAbbreviatedName());
                editor.apply();

                updateSupportiveEndpoints();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(CRYPTO_SELECTED, CryptoCurrency.DEFAULT_CRYPTO.getAbbreviatedName());
                editor.apply();

                updateSupportiveEndpoints();
            }
        });

        ArrayAdapter<CharSequence> fiatSpinnerAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_list_item, R.id.spinner_item, fiats);
        fiatSpinner.setAdapter(fiatSpinnerAdapter);
        fiatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(FIAT_SELECTED, FiatCurrency.getFiatCurrencyFromAbbreviatedName(adapterView.getItemAtPosition(i).toString(), thisContext).getAbbreviatedName());
                editor.apply();

                updateSupportiveEndpoints();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(FIAT_SELECTED, FiatCurrency.DEFAULT_FIAT.getAbbreviatedName());
                editor.apply();

                updateSupportiveEndpoints();
            }
        });

        String defaultCrypto = getString(sharedPreferences.getInt(CRYPTO_SELECTED, CryptoCurrency.DEFAULT_CRYPTO.getAbbreviatedName()));
        String defaultFiat = getString(sharedPreferences.getInt(FIAT_SELECTED, FiatCurrency.DEFAULT_FIAT.getAbbreviatedName()));

        int defaultCryptoPosition = cryptoSpinnerAdapter.getPosition(defaultCrypto);
        int defaultFiatPosition = fiatSpinnerAdapter.getPosition(defaultFiat);

        Log.i(TAG, "Crypto Position: " + defaultCryptoPosition);
        Log.i(TAG, "Fiat Position: " + defaultFiatPosition);

        cryptoSpinner.setSelection(defaultCryptoPosition);
        fiatSpinner.setSelection(defaultFiatPosition);

        updateSupportiveEndpoints();
    }

    public CryptoCurrency getCurrentCryptoCurrency() {
        CryptoCurrency temp =
                CryptoCurrency.getCryptocurrencyFromAbbreviatedName(
                        sharedPreferences.getInt(CRYPTO_SELECTED,
                                CryptoCurrency.DEFAULT_CRYPTO.getAbbreviatedName()));
        return (temp == null) ? CryptoCurrency.DEFAULT_CRYPTO : temp;
    }

    public FiatCurrency getCurrentFiatCurrency() {
        FiatCurrency temp =
                FiatCurrency.getFiatCurrencyFromAbbreviatedName(
                        sharedPreferences.getInt(FIAT_SELECTED,
                                FiatCurrency.DEFAULT_FIAT.getAbbreviatedName()));
        return (temp == null) ? FiatCurrency.DEFAULT_FIAT : temp;
    }

    private void updateSupportiveEndpoints() {
        RecyclerView supportingRecyclerView = findViewById(R.id.supporting_recyclerview);
        this.supportedWebsites.clear();

        for (AbstractAPICall call : this.allWebsites) {
            if (call.canUseCryptocurrency(getCurrentCryptoCurrency()) &&
                    call.canUseFiatCurrency(getCurrentFiatCurrency()))
                this.supportedWebsites.add(call);
        }

        CompatibleEndpointAdapter adapter = new CompatibleEndpointAdapter(this.supportedWebsites.size(), this);
        supportingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        supportingRecyclerView.setHasFixedSize(true);
        supportingRecyclerView.setAdapter(adapter);
        supportingRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public String getEndpointName(int orderInList) {
        return this.supportedWebsites.get(orderInList).getName();
    }
}