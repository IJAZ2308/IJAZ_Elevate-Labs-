-- ============================================
-- Task 3: SQL Basics – Filtering, Sorting, Aggregations
-- Dataset: Superstore Sales
-- Database: MySQL / PostgreSQL
-- ============================================

-- 1. View sample records
SELECT * FROM superstore LIMIT 10;

-- 2. Count total records (CSV verification)
SELECT COUNT(*) AS total_rows FROM superstore;

-- 3. Filtering: Technology category sales
SELECT *
FROM superstore
WHERE Category = 'Technology';

-- 4. Sorting: Top selling products by Sales
SELECT Product_Name, Sales
FROM superstore
ORDER BY Sales DESC
LIMIT 10;

-- 5. Aggregation: Total Sales & Profit by Category
SELECT
    Category,
    SUM(Sales) AS Total_Sales,
    SUM(Profit) AS Total_Profit
FROM superstore
GROUP BY Category;

-- 6. Aggregation: Average Profit by Region
SELECT
    Region,
    AVG(Profit) AS Avg_Profit
FROM superstore
GROUP BY Region;

-- 7. HAVING clause: Categories with Sales > 100000
SELECT
    Category,
    SUM(Sales) AS Total_Sales
FROM superstore
GROUP BY Category
HAVING SUM(Sales) > 100000;

-- 8. BETWEEN: Monthly Sales Report (example: Jan 2017)
SELECT
    Order_Date,
    Sales
FROM superstore
WHERE Order_Date BETWEEN '2017-01-01' AND '2017-01-31';

-- 9. LIKE: Customer name pattern search
SELECT *
FROM superstore
WHERE Customer_Name LIKE '%Smith%';

-- 10. Top 5 Customers by Total Spend
SELECT
    Customer_Name,
    SUM(Sales) AS Total_Spent
FROM superstore
GROUP BY Customer_Name
ORDER BY Total_Spent DESC
LIMIT 5;

-- 11. NULL Handling Example
SELECT
    AVG(Profit) AS Avg_Profit_Without_NULL
FROM superstore;

-- 12. Export-ready summary (used for CSV)
SELECT
    Region,
    SUM(Sales) AS Total_Sales,
    SUM(Profit) AS Total_Profit
FROM superstore
GROUP BY Region;
