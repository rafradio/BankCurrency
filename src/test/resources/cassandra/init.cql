CREATE KEYSPACE IF NOT EXISTS bank_currencies WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};
USE bank_currencies;