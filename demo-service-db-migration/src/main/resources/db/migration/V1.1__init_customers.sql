INSERT INTO `customers`(status, email, first_name, last_name, updated_time)
select 'ACTIVE', 'test0004@test.com', 'test0004', 'lastName', now()
union all select 'ACTIVE', 'test0005@test.com', 'test0005', 'lastName', now()
union all select 'INACTIVE', 'test0006@test.com', 'test0006', 'lastName', now();