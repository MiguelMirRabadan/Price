

INSERT INTO product (product_id, description)
VALUES (1,'primer producto'),
        (2,'SEGUNDO'),
        (35455, 'Inditex product');

INSERT INTO price (id,brand_Id,start_date,end_date,price_list,product_Id,priority,price,curr)
VALUES (1,1,'2020-06-14T00:00:00','2020-12-31T23:59:59',1,35455,0,35.50,'EUR'),
        (2,1,'2020-06-14T15:00:00','2020-06-14T18:30:00',2,35455,1,25.45,'EUR'),
        (3,1,'2020-06-15T00:00:00','2020-06-15T11:00:00',3,35455,1,30.50,'EUR'),
        (4,1,'2020-06-15T16:00:00','2020-12-31T23:59:59', 4 ,35455,1,38.95,'EUR');
