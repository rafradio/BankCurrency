CREATE KEYSPACE IF NOT EXISTS bank_currencies WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};
USE bank_currencies;
CREATE TABLE IF NOT EXISTS bank_currencies.conversion(
   id uuid PRIMARY KEY,
   rate decimal,
   rateonpreviousclose decimal,
   madeat timestamp,
   symbol varchar
);