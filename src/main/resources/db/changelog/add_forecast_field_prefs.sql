create or replace function jsonb_merge_recurse(orig jsonb, delta jsonb)
    returns jsonb
    language sql as
$$
select jsonb_object_agg(
               coalesce(keyOrig, keyDelta),
               case
                   when valOrig isnull then valDelta
                   when valDelta isnull then valOrig
                   when (jsonb_typeof(valOrig) <> 'object' or jsonb_typeof(valDelta) <> 'object') then valDelta
                   else jsonb_merge_recurse(valOrig, valDelta)
                   end
           )
from jsonb_each(orig) e1(keyOrig, valOrig)
         full join jsonb_each(delta) e2(keyDelta, valDelta) on keyOrig = keyDelta
$$;

create or replace function jsonb_merge_recurse_all(rows jsonb[])
    returns text language plpgsql as $$
declare row_result jsonb;
        row jsonb;
        len int := array_length(rows, 1);
begin

    if len = 0
    then return null;
    end if;
    if len = 1
    then return rows[0];
    end if;

    row_result = rows[0];
    FOREACH row IN ARRAY rows[1:]
        LOOP
            row_result := jsonb_merge_recurse(row_result, row);
        END LOOP;
    return row_result::text;
end;
$$;

create table forecast_field_user_preferences
(
    id          bigserial primary key,
    source      text not null,
    field       text not null,
    search_path text not null
);

create table user_field_preferences
(
    user_id bigint not null,
    pref_id bigint not null,
    constraint user_id_pref_id_pk PRIMARY KEY (user_id, pref_id)
);

insert into forecast_field_user_preferences(source, field, search_path)
VALUES ('OPEN_WEATHER', 'TEMPERATURE', '{"current": {"temp": "java.lang.Double"}}'),
       ('OPEN_WEATHER', 'WIND_SPEED', '{"current": {"wind_speed": "java.lang.Double"}}'),
       ('OPEN_WEATHER', 'PRESSURE', '{"current": {"pressure": "java.lang.Integer"}}'),
       ('OPEN_WEATHER', 'HUMIDITY', '{"current": {"humidity": "java.lang.Integer"}}');