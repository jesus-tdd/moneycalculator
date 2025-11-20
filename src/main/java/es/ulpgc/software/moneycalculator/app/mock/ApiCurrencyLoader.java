package es.ulpgc.software.moneycalculator.app.mock;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import es.ulpgc.software.moneycalculator.architecture.io.CurrencyLoader;
import es.ulpgc.software.moneycalculator.architecture.model.Currency;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ApiCurrencyLoader implements CurrencyLoader {
    private final Api api;

    public ApiCurrencyLoader(Api api) {
        this.api = api;
    }

    @Override
    public List<Currency> loadAll() {
        try {
            return readCurrencies();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Currency> readCurrencies() throws IOException {
        try (InputStream is = openInputStream(openConnection())) {
            return readCurrenciesWith(jsonIn(is));
        }
    }

    private List<Currency> readCurrenciesWith(String json) {
        return readCurrenciesWith(jsonObjectIn(json));
    }

    private List<Currency> readCurrenciesWith(JsonObject jsonObject) {
        return readCurrenciesWith(jsonObject.get("supported_codes").getAsJsonArray());
    }

    private List<Currency> readCurrenciesWith(JsonArray jsonArray) {
        ArrayList<Currency> currencies = new ArrayList<>();
        for (JsonElement element : jsonArray) {
            currencies.add(readCurrencyWith(element.getAsJsonArray()));
        }
        return currencies;
    }

    private Currency readCurrencyWith(JsonArray tuple) {
        return  new Currency(
                tuple.get(1).getAsString(),
                tuple.get(0).getAsString()
        );
    }

    private JsonObject jsonObjectIn(String json) {
        return new Gson().fromJson(json, JsonObject.class);
    }

    private String jsonIn(InputStream is) throws IOException {
        return new String(is.readAllBytes());
    }

    private InputStream openInputStream(URLConnection connection) throws IOException {
        return connection.getInputStream();
    }

    private URLConnection openConnection() throws IOException {
        return new URL(api.url() + "codes").openConnection();
    }
}
