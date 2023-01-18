# https://leetcode.com/problems/recyclable-and-low-fat-products/discuss/1062936/SQL-1-liner-solution-(This-is-a-FB-DE-interview-question)
# Write your MySQL query statement below
select product_id from products where low_fats = 'Y' and recyclable = 'Y'