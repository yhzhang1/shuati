# https://www.youtube.com/watch?v=FPelTXeSCo0
SELECT tb2.Name as Department, tb1.Name as Employee, tb1.Salary as Salary
FROM Employee as tb1
LEFT JOIN Department as tb2
On tb1.DepartmentID = tb2.Id
WHERE (tb2.Id, tb1.Salary) IN (SELECT DepartmentId, max(Salary)
                                            FROM Employee
                                            GROUP BY DepartmentId);