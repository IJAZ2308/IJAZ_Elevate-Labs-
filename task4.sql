/* ======================================================
   TASK 4: SQL INTERMEDIATE – JOINS
   Database: Chinook
   Author: <Your Name>
   ====================================================== */

-- 1. INNER JOIN: Orders with Customer Details
SELECT
    c.CustomerId,
    c.FirstName,
    c.LastName,
    c.Country,
    i.InvoiceId,
    i.InvoiceDate,
    i.Total
FROM customers c
INNER JOIN invoices i
    ON c.CustomerId = i.CustomerId
ORDER BY i.InvoiceDate;

--------------------------------------------------------

-- 2. LEFT JOIN: Customers who NEVER placed any orders
SELECT
    c.CustomerId,
    c.FirstName,
    c.LastName,
    c.Country
FROM customers c
LEFT JOIN invoices i
    ON c.CustomerId = i.CustomerId
WHERE i.InvoiceId IS NULL;

--------------------------------------------------------

-- 3. Revenue per Product (Track)
SELECT
    t.TrackId,
    t.Name AS TrackName,
    SUM(ii.UnitPrice * ii.Quantity) AS TotalRevenue
FROM invoice_items ii
INNER JOIN tracks t
    ON ii.TrackId = t.TrackId
GROUP BY t.TrackId, t.Name
ORDER BY TotalRevenue DESC;

--------------------------------------------------------

-- 4. Category-wise Revenue (Genre-wise)
SELECT
    g.Name AS Genre,
    SUM(ii.UnitPrice * ii.Quantity) AS GenreRevenue
FROM invoice_items ii
INNER JOIN tracks t
    ON ii.TrackId = t.TrackId
INNER JOIN genres g
    ON t.GenreId = g.GenreId
GROUP BY g.Name
ORDER BY GenreRevenue DESC;

--------------------------------------------------------

-- 5. Business Question:
-- Sales in a specific region between dates
SELECT
    c.Country,
    SUM(i.Total) AS TotalSales
FROM customers c
INNER JOIN invoices i
    ON c.CustomerId = i.CustomerId
WHERE c.Country = 'USA'
  AND i.InvoiceDate BETWEEN '2012-01-01' AND '2013-12-31'
GROUP BY c.Country;
