CREATE OR REPLACE FUNCTION update_history() RETURNS TRIGGER AS
$BODY$
BEGIN
   IF NEW.house IS DISTINCT FROM OLD.house THEN
   INSERT INTO "HouseHistory"(house_id,person_id, date, type) VALUES (NEW.house,OLD.person,NOW(),owner);
   END IF;
   IF NEW.home IS DISTINCT FROM OLD.home THEN
   INSERT INTO "HouseHistory"(house_id,person_id, date, type) VALUES (OLD.house,NEW.person,NOW(),tenant);
   END IF;
   RETURN new;
END;
$BODY$
language plpgsql;

CREATE TRIGGER history BEFORE UPDATE ON m2m_house_person
    FOR EACH ROW EXECUTE FUNCTION update_history();

CREATE TRIGGER history2 BEFORE UPDATE ON "Person"
    FOR EACH ROW EXECUTE FUNCTION update_history();