create table location
(
    location_id bigserial not null primary key,
    lat         numeric   not null,
    lon         numeric   not null
);
create table users
(
    user_id     bigserial not null primary key,
    location_id bigint references location (location_id)
);
