-- Insert para la funcionalidad de signos vitales y consulta de perfil
INSERT INTO menu(id_menu, nombre, icono, url) VALUES (9, 'Signos vitales', 'accessible', '/signos');
INSERT INTO menu(id_menu, nombre, icono, url) VALUES (10, 'Perfil', 'account_box', '/perfil');
INSERT INTO menu_rol (id_menu, id_rol) VALUES (9, 1);
INSERT INTO menu_rol (id_menu, id_rol) VALUES (10, 1);
INSERT INTO menu_rol (id_menu, id_rol) VALUES (10, 2);
INSERT INTO menu_rol (id_menu, id_rol) VALUES (10, 3);