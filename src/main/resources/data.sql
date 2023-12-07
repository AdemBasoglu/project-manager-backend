-- USER
insert into user (email, first_name, last_name, password)
values ('maxim@intec.be', 'Maxim', 'Baraliuc', '$2a$10$.o9Gz5ngMzZB23iHgQtPn.mNngs7Z/vhSi.j6Z1hQk7.k9PANWFBW');


insert into user (email, first_name, last_name, password)
values ('ilias@intec.be', 'Ilias', 'Marzak', '$2a$10$.o9Gz5ngMzZB23iHgQtPn.mNngs7Z/vhSi.j6Z1hQk7.k9PANWFBW');

insert into user (email, first_name, last_name, password)
values ('adem@intec.be', 'Adem', 'Basoglu', '1q2w3e4r');

insert into user (email, first_name, last_name, password)
values ('marwane@intec.be', 'Marwane', 'Lhoussni', '1q2w3e4r');

-- PROJECT
insert into project (name) -- 1
values ('Project ONE');

insert into project (name) -- 2
values ('Project TWO');

insert into project (name) -- 3
values ('Project THREE');

-- BOARD
insert into board (project_id, name) -- 1
values (3, 'TODO');

insert into board (project_id, name) -- 2
values (3, 'WIP');

insert into board (project_id, name) -- 3
values (2, 'DONE');

-- TASK
insert into task (created_date, board_id, description, name, label)
values ('2023-12-01', 1, 'Description missing...', 'Create database', 'DEFAULT');

insert into task (created_date, board_id, description, name, label)
values ('2023-12-06', 1, 'Description missing...', 'Initialize project', 'RED');

insert into task (created_date, board_id, description, name, label)
values ('2023-12-03', 2, 'Description missing...', 'Create services', 'DEFAULT');

insert into task (created_date, board_id, description, name, label)
values ('2023-12-01', 2, 'Description missing...', 'Analise business logic', 'GREEN');

insert into task (created_date, board_id, description, name, label)
values ('2023-12-03', 2, 'Description missing...', 'Choose frontend tech', 'PURPLE');

insert into task (created_date, board_id, description, name, label)
values ('2023-12-05', 3, 'Description missing...', 'Assemble dev team', 'ORANGE');

-- USER_TASKS
insert into user_tasks (tasks_id, users_email)
values (1, 'maxim@intec.be');

insert into user_tasks (tasks_id, users_email)
values (1, 'adem@intec.be');

insert into user_tasks (tasks_id, users_email)
values (3, 'maxim@intec.be');

insert into user_tasks (tasks_id, users_email)
values (4, 'ilias@intec.be');

insert into user_tasks (tasks_id, users_email)
values (5, 'marwane@intec.be');

-- USER_PROJECTS
insert into user_projects (projects_id, users_email)
values (1, 'maxim@intec.be');

insert into user_projects (projects_id, users_email)
values (2, 'maxim@intec.be');

insert into user_projects (projects_id, users_email)
values (3, 'maxim@intec.be');


insert into user_projects (projects_id, users_email)
values (1, 'ilias@intec.be');



