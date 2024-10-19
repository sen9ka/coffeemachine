WITH added_drinks AS (
    INSERT INTO drinks (name, is_standard)
        VALUES ('Espresso', true),
               ('Americano', true),
               ('Cappuccino', true)
        RETURNING id, name),
     added_ingredients AS (
         INSERT INTO ingredients (name, quantity, unit)
             VALUES ('Water', 1000, 'ml'),
                    ('Milk', 1000, 'ml'),
                    ('Coffee', 1000, 'ml')
             RETURNING id, name, quantity),
     recipe AS (SELECT t1.id                                                             AS drinkId,
                       t2.id                                                             AS ingredientId,
                       CASE
                           WHEN t1.name = 'Espresso' AND t2.name = 'Coffee' THEN 7
                           WHEN t1.name = 'Espresso' AND t2.name = 'Water' THEN 30
                           WHEN t1.name = 'Americano' AND t2.name = 'Кофе' THEN 30
                           WHEN t1.name = 'Americano' AND t2.name = 'Water' THEN 150
                           WHEN t1.name = 'Cappuccino' AND t2.name = 'Water' THEN 30
                           WHEN t1.name = 'Cappuccino' AND t2.name = 'Coffee' THEN 30
                           WHEN t1.name = 'Cappuccino' AND t2.name = 'Milk' THEN 100 END AS quantity
                FROM added_drinks t1,
                     added_ingredients t2
                WHERE t2.quantity IS NOT NULL)
INSERT
INTO drinks_ingredients (drink_id, ingredient_id, quantity)
SELECT drinkId, ingredientId, quantity
FROM recipe
WHERE quantity IS NOT NULL;

