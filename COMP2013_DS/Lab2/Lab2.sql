# Q1
SELECT * FROM customers;

# Q2
SELECT customers.customerName FROM customers WHERE city = 'Frankfurt';

# Q3
SELECT customers.customerName, customers.phone, customers.city FROM customers WHERE country = 'Germany';

# Q4
SELECT productlines.productLine FROM productlines;

# Q5
SELECT DISTINCT offices.city FROM offices;

# Q6
SELECT DISTINCT employees.jobTitle FROM employees;

# Q7
SELECT lastName, firstName, city FROM employees AS e, offices as o WHERE e.jobTitle =
                                         'President' AND o.officeCode = e.officeCode;

# Q8
SELECT orderNumber, comments FROM orders WHERE comments IS NOT NULL;

# Q9
SELECT customerName, orderNumber FROM orders AS o, customers AS c WHERE o.customerNumber = c.customerNumber;

# Q10
SELECT customerName FROM customers WHERE customerNumber NOT IN (
    SELECT customerName FROM customers AS c, orders AS o WHERE c.customerNumber = o.customerNumber
    );

# Q11
SELECT customerName FROM customers WHERE customerName LIKE '%Co' OR customerName LIKE '%Co.';

# Q12
SELECT e1.lastName, e1.firstName FROM employees AS e1, employees AS e2 WHERE e1.reportsTo = e2.employeeNumber
                                                                         AND e2.lastName = 'Bondur'
                                                                         AND e2.firstName = 'Gerard';

# Q13
INSERT INTO productlines(productLine, textDescription) VALUES ('Computers', 'We	now selL models of classic computers too!');
INSERT INTO productlines (productLine, textDescription) VALUES ('Helicopters', 'All kinds of choppers.');

# Q14
UPDATE employees SET officeCode = (
    SELECT officeCode FROM offices WHERE city = 'Paris'
    ) WHERE lastName = 'Vanauf' AND firstName = 'George';