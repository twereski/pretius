package pl.twereski.pretius.domain;

public interface RateRepository {

    Rate getByCode(String code);
}
