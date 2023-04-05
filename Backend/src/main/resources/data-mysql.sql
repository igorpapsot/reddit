insert into user (user_id ,username, password, email, avatar, registration_date, is_banned, role) values ('1', 'Marko', '12345', 'marko@gmail.com', 'avatarFilm',  '2020-3-3', false, 'USER');

insert into flair values(1, "cars");
insert into flair values(2, "animals");
insert into flair values(3, "outdoors");

insert into community values(1, "2022-1-2", "Serbia community", false, "R/Serbia", null);
insert into community values(2, "2022-2-2", "Cars community", true, "R/Cars", "asd");
insert into community values(3, "2022-3-2", "Coding community", false, "R/Coding", null);

insert into post values(1, "2022-5-5", "image-path", "New text about cars", "Suzuki new car", 1, 1, 1);
insert into post values(2, "2022-2-5", "image-path1", "New text about animals", "Penguins", 2, 1,1);
insert into post values(3, "2022-2-3", "image-path2", "New text about outdoors activity", "Hiking", 3, 1, 1);