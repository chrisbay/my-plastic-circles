insert into Disc_Manufacturer (id, name) values (hibernate_sequence.nextval, 'Discraft');
insert into Disc_Manufacturer (id, name) values (hibernate_sequence.nextval, 'Gateway');
insert into Disc_Manufacturer (id, name) values (hibernate_sequence.nextval, 'EV-7');
insert into Disc_Manufacturer (id, name) values (hibernate_sequence.nextval, 'Mint');

insert into Disc (id, model, manufacturer_id, speed, glide, turn, fade, is_favorite) values (hibernate_sequence.nextval, 'Avenger SS', 1, 10, 5, -3, 1, true);
insert into Disc (id, model, manufacturer_id, speed, glide, turn, fade, is_favorite) values (hibernate_sequence.nextval, 'Wasp', 1, 5, 3, 0, 2, true);
insert into Disc (id, model, manufacturer_id, speed, glide, turn, fade, is_favorite) values (hibernate_sequence.nextval, 'Zone', 1, 4, 3, 0, 3, false);
insert into Disc (id, model, manufacturer_id, speed, glide, turn, fade, is_favorite) values (hibernate_sequence.nextval, 'Buzzz', 1, 5, 4, -1, 1, true);
insert into Disc (id, model, manufacturer_id, speed, glide, turn, fade, is_favorite, notes) values (hibernate_sequence.nextval, 'Wizard', 2, 2, 3, 0, 2, false, 'Lunar plastic');
insert into Disc (id, model, manufacturer_id, speed, glide, turn, fade, is_favorite) values (hibernate_sequence.nextval, 'Mobius', 3, 2, 4, -1, 0, true);