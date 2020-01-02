INSERT INTO `customers`(status, email, first_name, last_name, updated_time)
select 'ACTIVE', 'test0001@test.com', 'test0001', 'lastName', now()
union all select 'ACTIVE', 'test0002@test.com', 'test0002', 'lastName', now()
union all select 'INACTIVE', 'test0003@test.com', 'test0003', 'lastName', now();