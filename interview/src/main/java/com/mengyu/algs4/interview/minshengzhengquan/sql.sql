select count(*) as 项目数量, deptName as 部门名字
from (
    select dept.name as deptName, prj.name as prjName
    from department as dept
    left join project as prj
    on dept.id = prj.dept_id
    group by dept.name, prj.name
) as tmp
group by deptName;


select dept.name as deptName, prj.name as prjName
    from department as dept
    left join project as prj
    on dept.id = prj.dept_id
    group by dept.name, prj.name


    A B
    A B
    A C
    A D

