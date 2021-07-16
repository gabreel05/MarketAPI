package br.com.gabriel.mapper.response;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;

import br.com.gabriel.model.Demand;
import lombok.Getter;

@Getter
public class DemandResponse {

	public DemandResponse(Demand demand) {
		this.id = demand.getId();
		Stream<Double> streamProducts = demand.getProducts().stream().map(product -> product.getUnitPrice());
		Double prices = 0.0;
		Double totalPrices = streamProducts.reduce(prices, Double::sum);
		this.total = convertDoubleToCurrency(totalPrices);
		this.products = ProductResponse.toProductList(demand.getProducts());
		this.creationTime = formatLocalDateTime(LocalDateTime.now());
	}

	private Long id;
	private String total;
	private String creationTime;
	private List<ProductResponse> products;
	
	public static Page<DemandResponse> toDemand(Page<Demand> demands) {
		return demands.map(DemandResponse::new);
	}

	private String formatLocalDateTime(LocalDateTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		return formatter.format(time);
	}

	private String convertDoubleToCurrency(Double value) {
		Locale locale = new Locale("pt", "BR");

		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);

		return formatter.format(value);
	}

}
