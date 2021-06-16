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
import android.widget.TextView;

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
    public static final String BASE_CRYPTO = "BASE_CRYPTO";
    public static final String TARGET_FIAT = "TARGET_FIAT";
    public static final String TARGET_CRYPTO = "TARGET_CRYPTO";

    /**
     * Conversion type implies crypto/crypto or crypto/fiat
     *
     * True --> crypto/crypto
     * False -> crypto/fiat
     */
    public static final String CONVERSION_TYPE = "CONVERSION_TYPE"; // true is crypto/crypto, false is crypto/fiat
    public static final boolean DEFAULT_CONVERSION_TYPE = false;

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
        fiat_crypto_switch.setOnCheckedChangeListener((compoundButton, b) -> {
            sharedPreferences
                    .edit()
                    .putBoolean(CONVERSION_TYPE, b)
                    .apply();

            updateSupportiveEndpoints();
        });

        fiat_crypto_switch.setChecked(sharedPreferences.getBoolean(CONVERSION_TYPE, DEFAULT_CONVERSION_TYPE));

        final TextView targetCurrency = findViewById(R.id.tv_choose_target_currency);
        if (sharedPreferences.getBoolean(CONVERSION_TYPE, DEFAULT_CONVERSION_TYPE))
            targetCurrency.setText(R.string.choose_target_cryptocurrency);
        else
            targetCurrency.setText(R.string.choose_fiat_currency);

        final Spinner cryptoSpinner = findViewById(R.id.spinner_choose_crypto);
        final Spinner fiatSpinner = findViewById(R.id.spinner_choose_fiat);

        final List<CharSequence> cryptos = new ArrayList<>();
        for (CryptoCurrency crypto : CryptoCurrency.values()) {
            cryptos.add(getString(crypto.getAbbreviatedName()));
        }

        final List<CharSequence> fiats = new ArrayList<>();
        for (FiatCurrency fiat : FiatCurrency.values()) {
            fiats.add(getString(fiat.getAbbreviatedName()));
        }

        final Context thisContext = this;

        final ArrayAdapter<CharSequence> cryptoSpinnerAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_list_item, R.id.spinner_item, cryptos);
        cryptoSpinner.setAdapter(cryptoSpinnerAdapter);
        cryptoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(BASE_CRYPTO, CryptoCurrency.getCryptocurrencyFromAbbreviatedName(adapterView.getItemAtPosition(i).toString(), thisContext).getAbbreviatedName());
                editor.apply();

                updateSupportiveEndpoints();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(BASE_CRYPTO, CryptoCurrency.DEFAULT_CRYPTO.getAbbreviatedName());
                editor.apply();

                updateSupportiveEndpoints();
            }
        });

        final ArrayAdapter<CharSequence> fiatSpinnerAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_list_item, R.id.spinner_item, fiats);
        fiatSpinner.setAdapter(fiatSpinnerAdapter);
        fiatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(TARGET_FIAT, FiatCurrency.getFiatCurrencyFromAbbreviatedName(adapterView.getItemAtPosition(i).toString(), thisContext).getAbbreviatedName());
                editor.apply();

                updateSupportiveEndpoints();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(TARGET_FIAT, FiatCurrency.DEFAULT_FIAT.getAbbreviatedName());
                editor.apply();

                updateSupportiveEndpoints();
            }
        });

        final String defaultCrypto = getString(sharedPreferences.getInt(BASE_CRYPTO, CryptoCurrency.DEFAULT_CRYPTO.getAbbreviatedName()));
        final String defaultFiat = getString(sharedPreferences.getInt(TARGET_FIAT, FiatCurrency.DEFAULT_FIAT.getAbbreviatedName()));

        final int defaultCryptoPosition = cryptoSpinnerAdapter.getPosition(defaultCrypto);
        final int defaultFiatPosition = fiatSpinnerAdapter.getPosition(defaultFiat);

        Log.i(TAG, "Crypto Position: " + defaultCryptoPosition);
        Log.i(TAG, "Fiat Position: " + defaultFiatPosition);

        cryptoSpinner.setSelection(defaultCryptoPosition);
        fiatSpinner.setSelection(defaultFiatPosition);

        updateSupportiveEndpoints();
    }

    public CryptoCurrency getCurrentCryptoCurrency() {
        final CryptoCurrency temp =
                CryptoCurrency.getCryptocurrencyFromAbbreviatedName(
                        sharedPreferences.getInt(BASE_CRYPTO,
                                CryptoCurrency.DEFAULT_CRYPTO.getAbbreviatedName()));
        return (temp == null) ? CryptoCurrency.DEFAULT_CRYPTO : temp;
    }

    public FiatCurrency getCurrentFiatCurrency() {
        final FiatCurrency temp =
                FiatCurrency.getFiatCurrencyFromAbbreviatedName(
                        sharedPreferences.getInt(TARGET_FIAT,
                                FiatCurrency.DEFAULT_FIAT.getAbbreviatedName()));
        return (temp == null) ? FiatCurrency.DEFAULT_FIAT : temp;
    }

    private void updateSupportiveEndpoints() {
        final RecyclerView supportingRecyclerView = findViewById(R.id.supporting_recyclerview);
        this.supportedWebsites.clear();

        final boolean conversionType = this.sharedPreferences.getBoolean(CONVERSION_TYPE, DEFAULT_CONVERSION_TYPE);

        if (conversionType) {
            ((TextView) findViewById(R.id.tv_choose_target_currency))
                    .setText(R.string.choose_target_cryptocurrency);

            for (AbstractAPICall call : this.allWebsites) {
                if (call.supportsCryptoToCrypto() &&
                        call.canUseCryptocurrency(getCurrentCryptoCurrency())
                    /* && TODO: Can support the second currency */)
                    this.supportedWebsites.add(call);
            }
        } else {
            ((TextView) findViewById(R.id.tv_choose_target_currency))
                    .setText(R.string.choose_fiat_currency);

            for (AbstractAPICall call : this.allWebsites) {
                if (call.supportsFiatToCrypto() &&
                        call.canUseCryptocurrency(getCurrentCryptoCurrency()) &&
                        call.canUseFiatCurrency(getCurrentFiatCurrency()))
                    this.supportedWebsites.add(call);
            }
        }

        final CompatibleEndpointAdapter adapter = new CompatibleEndpointAdapter(this.supportedWebsites.size(), this);
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