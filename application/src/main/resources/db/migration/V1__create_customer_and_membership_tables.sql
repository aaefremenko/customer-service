CREATE TABLE IF NOT EXISTS "memberships" (
	"id"						bigserial PRIMARY KEY,
	"type"						varchar(16) NOT NULL,
	"start_date"				date NOT NULL,
	"end_date"					date NOT NULL
);

CREATE TABLE IF NOT EXISTS "customers" (
    "id"                      	bigserial PRIMARY KEY,
    "username"               	varchar(32) NOT NULL UNIQUE,
    "first_name"           		varchar(32) NOT NULL,
    "last_name"              	varchar(64) NOT NULL,
    "registration_date"			date NOT NULL,
    "last_modified_date"		timestamp NOT NULL,
    "status"					varchar(16) NOT NULL,
    "address"					varchar(128),
    "phone_number"				varchar(16) NOT NULL,
    "email"						varchar(32) NOT NULL,
    "membership_id"				bigint REFERENCES memberships (id)
);
