DROP TABLE IF EXISTS tlb_currencies;

CREATE TABLE tlb_currencies (
	id BIGINT AUTO_INCREMENT  PRIMARY KEY,
	name VARCHAR(250) NOT NULL,
	abbreviation VARCHAR(5) NOT NULL,
	value_exchange DOUBLE NOT NULL
);