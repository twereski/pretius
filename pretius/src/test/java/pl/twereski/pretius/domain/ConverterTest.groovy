package pl.twereski.pretius.domain

import pl.twereski.pretius.domain.dto.ExchangeDto
import pl.twereski.pretius.persistance.RateRepositoryInMemory
import spock.lang.Specification

class ConverterTest extends Specification {
    def "Convert"() {
        given: "Table with courses"
        def repository = new RateRepositoryInMemory()
        def rateIn = new Rate("aa", "XXD", "10")
        def rateOut = new Rate("bb", "ZZZ", "20")
        def rateList =  [rateIn, rateOut]
        repository.saveAll(rateList)
        and:"user request dto"
        def dto = new ExchangeDto(100.00, "XXD", "ZZZ")
        and: "converter"
        def converter = new Converter(repository)
        when: "convert"
        def result = converter.convert(dto)
        then: "should return out currency"
        result.getCode() == "ZZZ"
        and: "amount should be 100 * 10 / 20 = 50"
        result.getAmount() == 50.00
    }
}
