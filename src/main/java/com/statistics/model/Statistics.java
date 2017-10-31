package com.statistics.model;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;

public class Statistics {

    private Double sum = 0D;

    private Double avg = 0D;

    private Double max = 0D;

    private Double min = 0D;

    private Long count = 0L	;

    public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public Double getAvg() {
		return avg;
	}

	public void setAvg(Double avg) {
		this.avg = avg;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Statistics() {
    }

    public Statistics(Map<Double, Transaction> transactions60Seconds) {
        final List<Double> amountsLastMinute = transactions60Seconds.values().stream().map(Transaction::getAmount).collect(toList());
        final Long count = amountsLastMinute.stream().count();
        this.setCount(count);
        if (count > 0) {
            this.setSum(amountsLastMinute.stream().mapToDouble(Double::doubleValue).sum());
            this.setAvg(amountsLastMinute.stream().mapToDouble(Double::doubleValue).average().getAsDouble());
            this.setMax(amountsLastMinute.stream().max(Double::compareTo).get());
            this.setMin(amountsLastMinute.stream().min(Double::compareTo).get());
        }
    }
}