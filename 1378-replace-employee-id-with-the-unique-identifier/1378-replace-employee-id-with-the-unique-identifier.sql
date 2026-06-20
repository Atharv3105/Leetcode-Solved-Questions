# Write your MySQL query statement below
Select EmployeeUNI.unique_id , Employees.name from Employees LEFT JOIN EmployeeUNI on Employees.id = EmployeeUNI.id;