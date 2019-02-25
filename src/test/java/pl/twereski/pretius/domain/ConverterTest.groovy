package pl.twereski.pretius.domain

import pl.twereski.pretius.app.exception.ExchangeException
import pl.twereski.pretius.domain.dto.ExchangeDto
import pl.twereski.pretius.persistance.RateRepositoryInMemory
import spock.lang.Specification

class ConverterTest extends Specification {
    def "Convert between foreign currencies"() {
        given: "Table with courses"
        def repository = new RateRepositoryInMemory()
        def rateIn = new Rate("XXD", "10")
        def rateOut = new Rate("ZZZ", "20")
        def rateList =  [rateIn, rateOut]
        repository.saveAll(rateList)
        and:"user request dto"
        def dto = new ExchangeDto()
        dto.setAmount(100.00)
        dto.setInCurrency("XXD")
        dto.setOutCurrency("ZZZ")
        and: "converter"
        def converter = new Converter(repository)
        when: "convert"
        def result = converter.convert(dto)
        then: "should return out currency"
        result.getCode() == "ZZZ"
        and: "amount should be 100 * 10 / 20 = 50"
        result.getAmount() == 50.00
    }

    def "Convert non existing currency"() {
        given: "Table with courses"
        def repository = new RateRepositoryInMemory()
        def rateIn = new Rate("XXD", "10")
        repository.saveAll([rateIn])
        and: "user request dto"
        def dto = new ExchangeDto()
        dto.setAmount(50.00)
        dto.setInCurrency("XXD")
        dto.setOutCurrency("DDD")
        and: "converter"
        def converter = new Converter(repository)
        when: "convert"
        converter.convert(dto)
        then: "should thrown exception"
        thrown(ExchangeException)

    }
}
