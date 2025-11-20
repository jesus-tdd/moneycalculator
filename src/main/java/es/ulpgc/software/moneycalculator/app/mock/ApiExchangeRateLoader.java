package es.ulpgc.software.moneycalculator.app.mock;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import es.ulpgc.software.moneycalculator.architecture.io.ExchangeRateLoader;
import es.ulpgc.software.moneycalculator.architecture.model.Currency;
import es.ulpgc.software.moneycalculator.architecture.model.ExchangeRate;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;

public class ApiExchangeRateLoader implements ExchangeRateLoader {
    private final Api api;

    public ApiExchangeRateLoader(Api api) {
        this.api = api;
    }

    @Override
    public ExchangeRate load(Currency from, Currency to) {
        try {
            return new ExchangeRate(
                    LocalDate.now(),
                    from,
                    to,
                    readConversionRate(new URL(api.url() + "pair/" + from.code() + "/" + to.code()))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private double readConversionRate(URL url) throws IOException {
        return readConversionRate(url.openConnection());
    }

    private double readConversionRate(URLConnection connection) throws IOException {
        try (InputStream is = connection.getInputStream()) {
            return readConversionRate(new String(new BufferedInputStream(is).readAllBytes()));
        }
    }

    private double readConversionRate(String json) {
        return readConversionRate(new Gson().fromJson(json, JsonObject.class));
    }

    private double readConversionRate(JsonObject jsonObject) {
        return jsonObject.get("conversion_rate").getAsDouble();
    }
}
