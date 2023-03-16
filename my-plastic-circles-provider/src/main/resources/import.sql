insert into Disc_Manufacturer (id, name) values (disc_manufacturer_seq.nextval, 'Discraft');
insert into Disc_Manufacturer (id, name) values (disc_manufacturer_seq.nextval, 'Gateway');
insert into Disc_Manufacturer (id, name) values (disc_manufacturer_seq.nextval, 'EV-7');
insert into Disc_Manufacturer (id, name) values (disc_manufacturer_seq.nextval, 'Mint');

insert into Disc (id, model, manufacturer_id, speed, glide, turn, fade, is_favorite, notes) values (disc_seq.nextval, 'Avenger SS', 1, 10, 5, -3, 1, true, 'Z-line swirl plastic');
insert into Disc (id, model, manufacturer_id, speed, glide, turn, fade, is_favorite) values (disc_seq.nextval, 'Wasp', 1, 5, 3, 0, 2, true);
insert into Disc (id, model, manufacturer_id, speed, glide, turn, fade, is_favorite) values (disc_seq.nextval, 'Zone', 1, 4, 3, 0, 3, false);
insert into Disc (id, model, manufacturer_id, speed, glide, turn, fade, is_favorite) values (disc_seq.nextval, 'Buzzz', 1, 5, 4, -1, 1, true);
insert into Disc (id, model, manufacturer_id, speed, glide, turn, fade, is_favorite, notes) values (disc_seq.nextval, 'Wizard', 51, 2, 3, 0, 2, false, 'Lunar plastic');
insert into Disc (id, model, manufacturer_id, speed, glide, turn, fade, is_favorite) values (disc_seq.nextval, 'Mobius', 101, 2, 4, -1, 0, true);